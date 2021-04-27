package View_modify;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import server.TalkClient;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import java.awt.Font;


public class ChatDialog_View extends JDialog {
	///Declare                        
	JMenu chatDialogMenu			  ; //메뉴버튼
	JMenuBar chatDialogMenuBar 		  ; // 메뉴바
	JPanel chatDialog_panel		 	  ;// 베이스패널
	JLabel timeCheck_jlb			  ;//시간표시라벨
	JButton send_jbtn				  ; //전송버튼
	JButton closs_jbtn				  ;//닫기버튼
	JButton Emoji_jbtn				  ;//이모티콘버튼
	JButton SendFile_jbtn			  ;//파일전송버튼
	JMenuItem chatInvite_MenuItem 	  ;//초대메뉴
	JMenuItem chatOut_MenuItem 		  ; //방나가기 메뉴
	public JTextArea showChat_jta	  ; // 채팅출력창
	JTextField InputChat_jtf          ;//채팅입력창
	public int    room_num  = 0;
	public String room_name = null;
	public TalkClient tc = null;
	
	  public DefaultTableModel   dtm;
	 String              data[][]    = new String[0][2];
	   String             cols[]      = {"이름","사원번호"}     ; // 컬럼에 헤더 값
	   JTable            jtm ;
	///End of Declare
	
	/**
	 * Create the application.
	 */
	public static void main(String[] args) { // 메인 문구
		new ChatDialog_View().initialize();
	}
	public ChatDialog_View() { //생성자
		initialize(); // 화면 출력 메소드 실행
	}
	///End of Const///End of Const///End of Const///End of Const///End of Const///End of Const
	
	/**
	 * Initialize the contents of the jdialog.
	 */
	private void initialize() { // 화면출력 메소드
		ChatDialog_Ctrl cdc= new ChatDialog_Ctrl(this);
		this.add(chatDialog_panel = new JPanel(), BorderLayout.CENTER); //기본 패널 추가 및 부착 및 중앙 위치
		chatDialog_panel.setBackground(Color.PINK); // 패널 색 지정
		chatDialog_panel.setLayout(null); //패널 앱솔루트 레이아웃
		
		chatDialog_panel.add(showChat_jta = new JTextArea());  // 채팅 보여주는 텍스트 에리어 생성 및 부착
		showChat_jta.setBounds(12, 26, 582, 272); // 텍스트 에리어 위치
		showChat_jta.enable(false); //텍스트 에리어에 쓰기 금지
		
		
		chatDialog_panel.add(InputChat_jtf = new JTextField()); // 채팅 입력창 생성 및 부착
		InputChat_jtf.setBounds(12, 340, 560, 25); // 채팅 입력창 위치지정
		InputChat_jtf.setColumns(10);// 입력창 컬럼값 추가
		InputChat_jtf.addActionListener(new ChatDialog_Ctrl(this));
		
		chatDialog_panel.add(timeCheck_jlb = new JLabel("시간표기")); // 시간표시라벨 생성 및 부착
		timeCheck_jlb.setBackground(Color.WHITE); // 라벨 배경색상
		timeCheck_jlb.setHorizontalAlignment(SwingConstants.RIGHT);// 라벨 가운데 정렬
		timeCheck_jlb.setBounds(10, 373, 762, 25); // 라벨 위치및 크기
		
		  jtm = new JTable();
	      jtm.setModel(dtm = new DefaultTableModel(data,cols));
	      jtm.setBounds(606, 31, 158, 272);
	      chatDialog_panel.add(jtm);		
		
		chatDialog_panel.add(send_jbtn = new JButton("전송")); // 전송버튼 생성 및 부착
		send_jbtn.setFont(new Font("굴림", Font.PLAIN, 14)); //버튼 문구 지정
		send_jbtn.setBounds(597, 340, 70, 30); // 버튼 위치 지정
		send_jbtn.setForeground(new Color(255, 255, 255)); // 버튼 글자색 지정
		send_jbtn.setOpaque(true); // 버튼 채우기 
		send_jbtn.setBorderPainted(true); // 버튼 경계선
		send_jbtn.setContentAreaFilled(true);// 버튼 작동시 채우기
		send_jbtn.setBackground(new Color(255, 204, 102));// 버튼 배경색
		send_jbtn.addActionListener(cdc);// 전송버튼 액션
		
		chatDialog_panel.add(closs_jbtn = new JButton("닫기"));// 닫기버튼 생성 및 부착
		closs_jbtn.setFont(new Font("굴림", Font.PLAIN, 14));// 닫기버튼 문구지정
		closs_jbtn.setBounds(691, 340, 70, 30); // 닫기버튼 위치지정
		closs_jbtn.setForeground(new Color(255, 255, 255)); // 닫기버튼 글자색 지정
		closs_jbtn.setOpaque(true);// 버튼 채우기
		closs_jbtn.setBorderPainted(true); // 버튼 경계선
		closs_jbtn.setContentAreaFilled(true); // 버튼 작동시 채우기
		closs_jbtn.setBackground(new Color(255, 204, 102)); //버튼 색상
		closs_jbtn.addActionListener(cdc); // 버튼 액션
		
		
		chatDialog_panel.add(Emoji_jbtn = new JButton("이모티콘")); // 이모티콘버튼 생성 및 부착
		Emoji_jbtn.setBounds(22, 308 , 95, 23); // 버튼 위치 지정
		Emoji_jbtn.setForeground(new Color(255, 255, 255)); // 버튼 글자색상
		Emoji_jbtn.setOpaque(true); //버튼 채우기
		Emoji_jbtn.setBorderPainted(true); // 버튼 경계 그리기
		Emoji_jbtn.setContentAreaFilled(true); // 버튼 작동시 채우기
		Emoji_jbtn.setBackground(new Color(255, 204, 102)); // 버튼 배경색
		Emoji_jbtn.addActionListener(cdc); //버튼 액션

		chatDialog_panel.add(SendFile_jbtn= new JButton("파일전송")); //파일 전송 버튼 생성 및 부착
		SendFile_jbtn.setBounds(130, 308, 95, 23); // 버튼 위치 
		SendFile_jbtn.setForeground(new Color(255, 255, 255)); // 버튼 글자색 지정
		SendFile_jbtn.setOpaque(true); // 버튼 채우기 
		SendFile_jbtn.setBorderPainted(true); // 버튼 경계선
		SendFile_jbtn.setContentAreaFilled(true); //버튼 작동시 채우기
		SendFile_jbtn.setBackground(new Color(255, 204, 102)); // 버튼 배경색 지정
		SendFile_jbtn.addActionListener(cdc); //버튼 액션
		
		this.setJMenuBar(chatDialogMenuBar = new JMenuBar()); //메뉴바 생성 및 부착
		chatDialogMenuBar.add(chatDialogMenu = new JMenu("메뉴")); // 메뉴바에 메뉴 부착 
		
		chatDialogMenu.add(chatInvite_MenuItem = new JMenuItem("초대하기")); // 초대하기 메뉴 생성 및 부착
		chatInvite_MenuItem.addActionListener(new ChatDialog_Ctrl(this)); // 초대하기 메뉴 액션
		
		chatDialogMenu.add(chatOut_MenuItem = new JMenuItem("방 나가기")); // 방나가기 메뉴 생성 및 부착
		chatOut_MenuItem.addActionListener(cdc); // 방나가기 메뉴 액션
		this.addWindowListener(cdc);
		this.setTitle(room_name); // 다이얼로그 타이틀 
		this.setBounds(100, 100, 790, 460); // 타이틀 크기
		this.setVisible(true); // 다이얼로그 출력
	}
}
// End of initialize// End of initialize// End of initialize// End of initialize// End of initialize// End of initialize