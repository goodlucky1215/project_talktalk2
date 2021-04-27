package server;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dao.ChattingListDao;
import dao.ListDao;
import dao.LoginNSignUpDao;
import dao.RoomNConversationDao;
import dao.SettingDao;




public class TalkServer extends JFrame implements Runnable {
	///////1-1.서버의 소켓을 선언
	ServerSocket server = null;
	///////1-2.들어오는 클라이언트의 소켓을 선언
	Socket       client = null;
	///////1-3.텍스트들이 보일 메모지라고 생각하면 됨.
	JTextArea 	jta_log = new JTextArea();
	///////1-4. 속지를 JScrollPane 위에 붙여줌-메모지를 붙이는 속지
	JScrollPane jsp_log = new JScrollPane(jta_log
			                             ,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
			                             ,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//일종의 속지임
	///////1-5. 각각 사람들의 서버 스래드가 동작할 TalkServerThread 사람이 들어올때마다 얘는 하나씩 생성된다!
	TalkServerThread tst = null;
	///////1-6. 사람들의 스래드를 담아둘 list 선언
	List<TalkServerThread> globalList = null;
	///////1-7. 방들을 담아둘 list 선언
	List<Room> roomList = null;
	//////관리자가 이미 들어와 있다면 1, 아니라면 0
	int manager = 0;
	TalkServerThread managertst = null;
	LoginNSignUpDao lnsDao  = new LoginNSignUpDao();
	ChattingListDao clDao   = new ChattingListDao();
	ListDao			listDao = new ListDao();
	SettingDao	    stDao   = new SettingDao();
	RoomNConversationDao rNcDao = new RoomNConversationDao();
	public TalkServer() {
		///////1-2.방의 목록을 벡터로  인스턴스화 한다
		//List는 인터페이스, Vector는 List를 구현하는 구현체 클래스임.
		roomList = new Vector<Room>();
		///////1-3.들어오는 사람들의 스래드를 벡터로 인스턴스화 한다.
		globalList = new Vector<TalkServerThread>();
		
		
		//////////////////////////////테스트하려고 만듬
		Room room = new Room();
		room.setRoom_num(1);
		room.setTitle("영업부 그룹방");
		roomList.add(room);
	}
	public void initDisplay() {
		///////2-1. 창 엑스 누르면 자동으로 완전히 전부 다 꺼짐
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				try {
					server.close();
					client.close();
					System.exit(0);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		///////2-1. 1-4만든 속지를 JFrame위 중앙에 붙여줌.
		this.add("Center",jsp_log);
		///////2-2. 서버 창 이름
		this.setTitle("서버 로그 출력....");
		///////2-3. 서버 창 크기
		this.setSize(500, 400);
		///////2-4. 서버 창 보이게
		this.setVisible(true);
	}//////////////////end of initDisplay
	
	public static void main(String[] args) {
		//////1. 가장 먼저 실행 - 생성자 호출 ㄱㄱ
		TalkServer ts = new TalkServer();
		//////2. 서버 화면이 나타남.
		ts.initDisplay();
		//////3. TalkServer에다가 서버 스레드를 할당. 
		new Thread(ts).start();//내[TalkServer]안에 있는 run메소드 호출됨.

	}

	@Override
	public void run() {

		boolean isStop = false;
		try {
			//////3-1. 서버의 소켓을 담아줌.
			server = new ServerSocket(3002);
			//////3-2. 여기서 이제 클라이언트가 들어오는 것을 서버가 기다림.
			while(!isStop) {
				//////TalkClientVe2의 3-2가 작동되면서,
				/////3-3. 서버가 클라이언트를 받고 클라이언트의 소켓을 받음.
				client = server.accept();//은수소켓저장(은수컴에 포트번호 할당)
				//////3-4. 클라이언트의 포트 번호 확인해보기
				jta_log.append("[새로운 소켓 접속]"+client.toString()+"\n");
				//스레드 생성하기
				//파라미터로 TalkServer를 넘기는 건 소켓은 TalkServer에 선언했는데
				//사용은 TalkServerThread에서도 사용가능해야 하니깐......
				//////3-5. TalkServerThread를 인스턴스화 해주면서 TalkServer의 주소값을 넘김. ㄱㄱ
				tst = new TalkServerThread(this);
				//////3-6. 클라이언트의 run메소드를 자동(JVM)으로 호출함. 
				//////--->여기까지 가면 한 클라이언트를 이제 떠나보내고 다시 while문인간 3-2쪽에서 계속 다른 서버가 올때까지 또 기다리고 있는다.
				tst.start();//run메소드를 자동(JVM)으로 호출함. - 콜백메소드
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}/////////////////end of run

}

