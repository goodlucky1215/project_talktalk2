package View_modify;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import server.TalkClient;

public class Config_Base_Panel extends JPanel {
	JButton 	userInfo_jbtn 		     ; // 사용자 정보 버튼
	JButton 	changeStatus_jbtn 	     ; // 상태 변경 버튼
	JButton 	LogOut_jbtn 		     ; // 로그아웃 버튼
	JLabel 		jbl_chatListTitle 	     ; // 패널에 붙는 타이틀 라벨
	TalkClient tc = null;
	Config_Panel_Ctrl          cpc  = new Config_Panel_Ctrl(this);
	Config_UserInfo_Panel      cup  = new Config_UserInfo_Panel(this);
    public Config_Status_Change_Panel cscp = new Config_Status_Change_Panel(this);
	public Config_Base_Panel() { // 생성자
		initialize(); // 화면 출력 메소드 실행
	}
	
	private void initialize() { 
		this.setBackground(Color.WHITE);  // 패널 배경색 지정
		this.setBounds(0, 0, 272, 430);   // 패널  위치지정
		this.setLayout(null);             // 패널 레이아웃 앱솔루트
		
		this.add(userInfo_jbtn = new JButton("사용자 정보")); // 사용자 정보 버튼 생성 및 부착
		userInfo_jbtn.setForeground(new Color(240, 255, 240)); // 버튼 글자색 
		userInfo_jbtn.setOpaque(true); // 버튼 채우기 온
		userInfo_jbtn.setBorderPainted(true); // 버튼 경계 생성
		userInfo_jbtn.setContentAreaFilled(true); // 작동시 채우는 액션
		userInfo_jbtn.setBounds(51, 132, 170, 30); // 버튼 위치지정
		userInfo_jbtn.setBackground(new Color(30, 144, 255));// 버튼 배경색 지정
		userInfo_jbtn.addActionListener(cpc);
		
		this.add(changeStatus_jbtn = new JButton("상태 변경")); // 상태 변경  생성 및 부착
		changeStatus_jbtn.setForeground(new Color(240, 255, 240)); //상태 버튼 글자색 지정
		changeStatus_jbtn.setOpaque(true); // 상태 버튼 채우기 
		changeStatus_jbtn.setBorderPainted(true); //상태버튼 경계선 있음
		changeStatus_jbtn.setContentAreaFilled(true); // 상태버튼 작동시 버튼 채움
		changeStatus_jbtn.setBounds(51, 232, 170, 30); // 버튼 위치지정
		changeStatus_jbtn.setBackground(new Color(30, 144, 255)); // 상태버튼 배경색 지정
		changeStatus_jbtn.addActionListener(cpc);
		
		this.add(LogOut_jbtn = new JButton("로그아웃")); // 로그아웃버튼 생성및 부착
		LogOut_jbtn.setForeground(new Color(240, 255, 240)); // 버튼 글자색 지정
		LogOut_jbtn.setOpaque(true); // 버튼 채우기 
		LogOut_jbtn.setBorderPainted(true); // 버튼 경계 그리기
		LogOut_jbtn.setContentAreaFilled(true); // 버튼 작동시 채우가
		LogOut_jbtn.setBounds(51, 332, 170, 30); // 버튼 위치 및 사이즈
		LogOut_jbtn.setBackground(new Color(30, 144, 255)); // 버튼 배경색 지정
		LogOut_jbtn.addActionListener(cpc);
		
		this.add(jbl_chatListTitle = new JLabel("Configuration★")); // 타이틀 라벨 생성 및 부착
		jbl_chatListTitle.setFont(new Font("굴림", Font.BOLD, 12)); // 타이틀 라벨 문구 지정
		jbl_chatListTitle.setHorizontalAlignment(SwingConstants.CENTER); // 라벨 가운데 정렬
		jbl_chatListTitle.setBounds(56, 25, 160, 20); // 라벨 위치
	
	this.setBackground(Color.WHITE);   // 패널 배경색 지정    
	this.setBounds(0, 0, 272, 430);    // 패널  위치지정     
	this.setLayout(null);              // 패널 레이아웃 앱솔루트 

	}
	
}
