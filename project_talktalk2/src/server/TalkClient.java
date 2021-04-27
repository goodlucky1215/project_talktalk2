package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.vo.RoomVO;

import View_modify.ChatDialog_View;
import View_modify.Login_view;
import View_modify.MenuList_View;


public class TalkClient extends JFrame implements ActionListener{
	Login_view login_view = null;
	public MenuList_View menuListView;
	//////1-1-4. 나의 소켓을 넣을 통 생성	
	Socket mySocket = null;
	//////1-1-5. 나의 말을 전달한 inputstream	
	ObjectInputStream ois  = null;
	//////1-1-6. 다른 사람의 말을 받을 outputstream	
    public ObjectOutputStream oos = null;		
	//////1-1-7. Server의 ip와 port를 가져옴.
	String ip = "localhost";
	int port = 3002;
	TalkClientThread tct = null;
	public List<ChatDialog_View> clientRoom = new ArrayList<>();
	public void connect_process() {
		try {
			//////3-2.서버에 접속과 동시에  클라이언트의 소켓 생성
			//통신은 지연될 수 있다.-wait - try...catch해야함. 연결 ㄱㄱ TalkServer대로 작동 시작 ㄱㄱ
			mySocket = new Socket(ip,port);
			//////3-3.내가 입력한 정보를 서버에게 보내는 통로
			oos = new ObjectOutputStream
					(mySocket.getOutputStream());			
			//////3-4. 서버로부터 정보를 받는 통로
			ois = new ObjectInputStream
					(mySocket.getInputStream());
			//////TalkServerThread안의 2-4인 이부분으로  String msg = (String)ois.readObject();부분으로 이 값이 넘어간다.
			//Protocol.ACCESS만 넣으면 숫자이기 때문에  +""을 붙여서 문자 처리 될 수 있게 해줌. - 연결됐음을 서버에게 알림
			oos.writeObject(Protocol.ACCESS+"");
			//////3-10. 나의 스래드가 담긴 주소번지를 담아서 TalkClientThread에 넣어줌 ㄱㄱ
			tct = new TalkClientThread(this);
			tct.start();
		} catch (Exception e) {
			//////3-12. 엘러를 알려줌
			System.out.println(e.toString());//에러 힌트문 출력.
		}		
	}	
	
	public TalkClient(Login_view login_view) {
		this.login_view = login_view;
		//클라이언트와 서버 연결
		connect_process();
		//화면 먼저 켜지고
		//initdisplay();
		//서버로부터 정보들 다 가져오기
		//start_process();
	}
	
	public void start_process() {
		try {
			//사원 목록, 방 목록, 내가 안들어간 방목록 3가지 다 가져오기
			oos.writeObject(Protocol.MEMLIST+"");
			oos.writeObject(Protocol.ROOMLIST+"");
			oos.writeObject(Protocol.GROUPLIST+"");
		} catch (Exception e) {
			//////3-12. 엘러를 알려줌
			System.out.println(e.toString());//에러 힌트문 출력.
		}		
	}	

	JPanel  jp_second_south = new JPanel();
	JButton jbtn_roomcreate1 = new JButton("단톡생성");
	JButton jbtn_roomcreate2 = new JButton("갠톡생성");
	JButton jbtn_roomin1 = new JButton("내가 있던 방 입장");
	JButton jbtn_roomin2 = new JButton("새로운 그룹 방 입장");

	JButton jbtn_myinfo = new JButton("개인정보조회");
	JButton jbtn_state = new JButton("상태설정");
	JButton jbtn_out = new JButton("로그아웃");
	
	JButton jbtn_roomdel= new JButton("방 삭제");
	
	JButton jbtn_message = new JButton("메세지전송");
	JButton jbtn_emoticon = new JButton("임티전송");
	void initdisplay() {
		jp_second_south.add(jbtn_roomcreate1);
		jp_second_south.add(jbtn_roomcreate2);
		jp_second_south.add(jbtn_roomin1);
		jp_second_south.add(jbtn_roomin2);
		
		jp_second_south.add(jbtn_myinfo);
		jp_second_south.add(jbtn_state);
		jp_second_south.add(jbtn_out);

		jp_second_south.add(jbtn_roomdel);
		
		jp_second_south.add(jbtn_message);
		jp_second_south.add(jbtn_emoticon);
		this.add(jp_second_south);
		this.setSize(170, 500);
		this.setVisible(true);
		jbtn_roomcreate1.addActionListener(this);//완성
		jbtn_roomcreate2.addActionListener(this);//완성
		jbtn_roomin1.addActionListener(this); //완성
		jbtn_roomin2.addActionListener(this); //완성
		                
		jbtn_myinfo.addActionListener(this);//완성
		jbtn_state.addActionListener(this);//완성
		jbtn_out.addActionListener(this);//아예 나감 - 완성
		                
		jbtn_roomdel.addActionListener(this);
		
		jbtn_message.addActionListener(this);
		jbtn_emoticon.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		//단톡방 생성
		if(obj==jbtn_roomcreate1) {
			try {
				oos.writeObject(Protocol.ROOM_CREATE
							   +Protocol.seperator+"group"//단톡방 생성
							   +Protocol.seperator+"그룹방이름" //그룹방이름
								);
				//방 사람들 들어있는 목록- 번호
				List<Integer> member_nos = new ArrayList<>();
				member_nos.add(1111); //내 번호
				member_nos.add(2010); //해리포터
				member_nos.add(2011); //해리포터
				member_nos.add(2012); //해리포터
				oos.writeObject(member_nos);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		//개인톡방 생성
		else if(obj==jbtn_roomcreate2) {
			try {
				oos.writeObject(Protocol.ROOM_CREATE
						       +Protocol.seperator+"individual" //개인톡 생성
						       +Protocol.seperator+"해리포터" //상대방 이름으로 방이름 붙이기
						       );
				//방 사람들 들어있는 목록- 번호 -자기 자신은 넣지 말자 어차피 거기서 처리하면 되니깐
				List<Integer> member_nos = new ArrayList<>();
				member_nos.add(1111); //내 번호
				member_nos.add(2010); //해리포터
				oos.writeObject(member_nos);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		//내가 있던 방에 입장
		else if(obj==jbtn_roomin1) {
			try {
				oos.writeObject(Protocol.MYROOM_IN
							   +Protocol.seperator+1 //여기서 1은 room_no임!! room_no를 넣어둬야됨.
							   );
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		//새로운 그릅방 입장
		else if(obj==jbtn_roomin2) {
			try {
				oos.writeObject(Protocol.GROUPROOM_IN 
							   +Protocol.seperator+20 //여기에 20은 room_no임!! room_no를 넣어둬야됨.
							   +Protocol.seperator+"들어가고 싶은 방이름가져오기" //방이름도 가져오기 존재하는 방이면 그냥 넣어줘야돼서.
							   );
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		//내 정보 조회
		else if(obj==jbtn_myinfo) {
			try {
				oos.writeObject(Protocol.MY_INFO+"");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		//내 상태변경
		else if(obj==jbtn_state) {
			//값을 받아서 넘겨 넣어주겠지
			try {
				oos.writeObject(Protocol.MY_STATE
							   +Protocol.seperator+"여기에 상태string으로받기");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
		}
		//방 삭제
		else if(obj==jbtn_roomdel) {
			//값을 받아서 넘겨 넣어주겠지
			try {
				oos.writeObject(Protocol.ROOM_DEL
							   +Protocol.seperator+2000 //여기에 아예 삭제하려는 방번호 받아오기
							   +Protocol.seperator+"삭제하려는방이름");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
		}
		//메세지 전송
		else if(obj==jbtn_message) {
			try {
				oos.writeObject(Protocol.MESSAGE
							   +Protocol.seperator+1010 //방번호
							   +Protocol.seperator+2000 //말하는 사람의 mem_num
							   +Protocol.seperator+"도비" //말하는 사람의 mem_name
						       +Protocol.seperator+"안녕 나는 이제 자유의 몸 도비야~~"); //대화내용 넣기
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
		}
		//로그아웃
		else if(obj==jbtn_out) {
			//값을 받아서 넘겨 넣어주겠지
			try {
				oos.writeObject(Protocol.LOGOUT+"");
				//여기에 그 사람 exit() 시켜준다.
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
		}
			
		
	}
}
