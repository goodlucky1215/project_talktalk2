package View_modify;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import server.TalkServer;

import java.awt.Color;
import javax.swing.UIManager;

public class Config_Status_Change_Panel extends JFrame {
	///Declare ///Declare ///Declare ///Declare ///Declare 
	public DefaultTableModel config_Status_dtm                       		 	; // 유저 정보 보여줄 데이타 테이믈 모델
	JTable	 		  showStatustable	                                ; // 유저 정보 보여줄 테이블
	JLabel			  showInfo_jbl								        ; // 패널에 붙일 타이틀 라벨 
	String[] 				cols		= {"구분",null} 	 			; // 테이블 헤더 컬럼
	String 		    	data[][] 	= new String[3][2] 	  				; // 테이블  테이블 크기 초기화
	String data1[][] = {{"이름"},{"부서"},{"직급"}};
	//End ofDeclare 	//End ofDeclare	//End ofDeclare	//End ofDeclare	//End ofDeclare
	
	public static void main(String[] args) {

		new Config_Status_Change_Panel().Initialize();
	}
	/**
	 * Create the panel.
	 */
	public Config_Status_Change_Panel() { //생성자
		Initialize(); //화면c생성 메소드 실행
	}
	Config_Base_Panel config_Base_Panel = null;
	public Config_Status_Change_Panel(Config_Base_Panel config_Base_Panel) { // 생성자
		this.config_Base_Panel = config_Base_Panel;
		Initialize(); // 화면 출력 메소드 실행
	}
	////End of Const////End of Const////End of Const////End of Const
	public void Initialize() {	

		this.add(showInfo_jbl = new JLabel("사용자 정보")); // 패널에 붙는 타이틀 라벨
		showInfo_jbl.setHorizontalAlignment(SwingConstants.CENTER); // 라벨문구 가운데 정렬
		showInfo_jbl.setBounds(64, 32, 130, 17); // 타이틀 라벨 위치 지정
		showInfo_jbl.setFont(new Font("굴림", Font.BOLD, 14)); // 타이틀 라벨 문구 지정
		
		
		showStatustable = new JTable();
		showStatustable.setFont(new Font("굴림", Font.PLAIN, 15)); // 테이블 글자 지정
		showStatustable.setEnabled(false); //테이블 수정 불가
		showStatustable.setBorder(UIManager.getBorder("Tree.editorBorder")); // 테이블 경계 스타일 지정
		showStatustable.setBackground(new Color(255, 250, 240)); // 테이블 배경 색 지정
		showStatustable.setModel( // 테이블에 데이터 셋  주입
		config_Status_dtm = new DefaultTableModel(data1,cols)
		); 	
		////End of setModel		////End of setModel		////End of setModel		////End of setModel
				
		showStatustable.getColumnModel().getColumn(0).setResizable(false);// 테이블 크기 재조정 안됨
 		showStatustable.getColumnModel().getColumn(0).setPreferredWidth(50); // 테이블0번쨰 컬럼 기존 간격 지정
		showStatustable.getColumnModel().getColumn(0).setMinWidth(50); // 테이블 0번 컬럼 미니멈 가로크기
		showStatustable.getColumnModel().getColumn(0).setMaxWidth(100); // 테이블 0번 최대크기
		showStatustable.getColumnModel().getColumn(1).setResizable(false); // 1번 컬럼 사이즈조절안댐
		showStatustable.getColumnModel().getColumn(1).setPreferredWidth(150); // 1번 컬럼  간격조절
		showStatustable.getColumnModel().getColumn(1).setMaxWidth(150); // 1번 컬럼  최대크기 지정 
		showStatustable.setBounds(41, 172, 175, 50); // 테이블 위치지정
		this.add(showStatustable); // 테이블 생성 및 패널이 붙이기 
		
		this.setBackground(new Color(255, 255, 255)); // 패널 배경색 지정
		this.setLayout(null); // 패널 레이아웃 앱솔루트
		this.setBounds(0, 0, 272, 430); //패널 크기 지정
		this.setVisible(false);
		
	}
	///End of Initialize ///End of Initialize///End of Initialize///End of Initialize///End of Initialize
}
/////////////End of This Class/////////////End of This Class /////////////End of This Class /////////////End of This Class  
