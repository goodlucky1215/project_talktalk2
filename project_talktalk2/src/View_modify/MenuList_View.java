package View_modify;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import server.Protocol;
import server.TalkClient;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public class MenuList_View extends JFrame implements WindowListener{
	JTree memberListTree								  ;	//멤버리스트 JTREE
	JPanel bottomTimePanel                                ;	//하단 시간 표시 패널
	JLabel TimeLabel                                      ; //하단 시간 표시 라벨
	JTabbedPane jTabbedPane								  ; //탭 페인 
//	MenuList_Ctrl menuList_Ctrl						  	  ; //메뉴 컨트롤러
//	MemberList_Panel 		memberList_Panel		      ; //멤버리스트 패널
////	Config_Main_Panel 		config_Main_Panel		      ; // 설정 메인창
//	ChatRoomList_Panel 		chatroomList_Panel		      ; // 채팅창 목록 패널
//	GroupChatRoomList_Panel groupChatRoomList_Panel		  ; // 그룹 채팅창 목록 패널
	
	
	///////End of Var///////End of Var///////End of Var///////End of Var///////End of Var///////End of Var///////End of Var//
	
	

	/**
	 * Create the application.
	 * @param TalkClient 
	 */
	TalkClient tc = null;
	public MenuList_View(TalkClient tc) { //생성자
		initialize(); // 화면 출력메소드 실행
		this.tc = tc;
		this.tc.menuListView = this;
		put_tc();
	}
	private void put_tc() {
		mp.tc  = this.tc;
		cp.tc  = this.tc;
		gp.tc  = this.tc;
		cbp.tc = this.tc;
		this.tc.start_process();
	}
	///End of Const//////	///End of Const//////	///End of Const//////	///End of Const//////	///End of Const//////	///End of Const//////
	/**
	 * Initialize the contents of the this.
	 */
	
	public MemberList_Panel   		mp = new MemberList_Panel();
	public ChatRoomList_Panel 		cp = new ChatRoomList_Panel();
	public GroupChatRoomList_Panel  gp = new GroupChatRoomList_Panel();
	public Config_Base_Panel       cbp = new Config_Base_Panel();
	
	private void initialize() { // 화면 출력 메소드

		String imgPath = "src/imgs/"; // 이미지 패스 경로
		///////////////////////////////////
		
		jTabbedPane = new JTabbedPane(JTabbedPane.LEFT); //TabPane 생성
		jTabbedPane.setBackground(new Color(255, 255, 255)); // 탭페인 배경색 
		jTabbedPane.setBounds(0, 0, 269+65, 431); // 탭페인 위치크기 지정
		
		
		jTabbedPane.addTab("사원목록", mp);//멤버리스트 패널 생성 생성 및 탭페인에 추가
		jTabbedPane.setBackgroundAt(0, new Color(255, 255, 255)); // 0번째 탭의 색상 추가
		
		jTabbedPane.addTab("채팅방",  cp);// 채팅리스트 패널 생성 및 탭페인에 추가
		jTabbedPane.setBackgroundAt(1, new Color(255, 255, 255)); // 1번 째 탭의 색상 추가
		
		jTabbedPane.addTab("그룹채팅", gp); //그룹 채팅 리스트 패널 생성 및 탭페인 에 추가
		jTabbedPane.setBackgroundAt(2, new Color(255, 255, 255)); // 2번 탭의 색상 추가
		
		jTabbedPane.addTab("설정창", cbp);// 설정 메인 창 생성 및 탭페인에 추가
		jTabbedPane.setBackgroundAt(3, new Color(255, 255, 255)); // 3번 탭의 색상추가
		this.add(jTabbedPane); // 프레임에 텝페인 부착
		
		
		
		this.add(bottomTimePanel= new JPanel()); // 프레임에 하단패널 부착 시간표시 할 하단 패널 생성
		bottomTimePanel.setBackground(Color.WHITE); // 하단 패널 배경색 
		bottomTimePanel.setBounds(0, 430, 334, 33); // 하단 패널 위치 크기 지정
		bottomTimePanel.setLayout(null); // 하단 패널 레이아웃 앱솔루트
		
		
		bottomTimePanel.add(TimeLabel = new JLabel("시간")); //  시간 표시 라벨 생성과 동시에 하단 패널에 부착
		TimeLabel.setFont(new Font("굴림", Font.BOLD, 12)); // 하단 시간 표시 패널 문구 지정
		TimeLabel.setHorizontalAlignment(SwingConstants.RIGHT); // 하단 시간 표시 라벨 우측 정렬
		TimeLabel.setBounds(60, 10, 200, 15);  // 하단 표시 시간 라벨 위치 크기 지정
		
		this.setResizable(false); // 프레임 사이즈 조절 ㄴㄴ
		this.setBounds(100, 100, 350, 500 ); // 프레임 크기 및 위 치지정
		this.addWindowListener(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); // 닫히면 jvm연결 끊기 -----------------
		this.setLayout(null); // 프레임 레이아웃 앱솔루트 
		this.getContentPane().setBackground(Color.WHITE); //프레임 배경색 지정
		this.setTitle("Talk!Talk!"); // 프레임 타이틀 세팅
		this.setVisible(true);// 프레임 출력
	}////End of initialize////End of initialize////End of initialize////End of initialize////End of initialize
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		JOptionPane.showMessageDialog(null, "자동 로그아웃 되었습니다.","로그아웃", JOptionPane.INFORMATION_MESSAGE);
		try {
			tc.oos.writeObject(Protocol.LOGOUT+"");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}////End of this Class   /// End of this Class////}////End of this Class   /// End of this Class////}////End of this Class   /// End of this Class//// 
