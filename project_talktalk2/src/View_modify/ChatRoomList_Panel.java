package View_modify;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import server.TalkClient;

import javax.swing.UIManager;
import javax.swing.JScrollBar;
import java.awt.Component;

public class ChatRoomList_Panel extends JPanel {         
	///Declare
	JLabel 				jbl_chatListTitle 			     ; // 패널에 붙는 타이틀 라벨
	String 				cols[]		= {"번호","제목"} ; // 컬럼 헤더 정보
	String 		    	data[][] 	= new String[0][2]	 ; // 테이블 크기
//////실제 사용은  위에 변수로	/// 

	JTable			  	jtb 			                 ; // 테이블 
	JButton 			jbtn_chatSearch 			     ; // 채팅 검색 버튼
	JTextField 			jtf_chatListSearch		         ; // 검색 입력창
	JScrollPane 		scrollPane 		                 ; // 테이블이 붙을 스크롤 페인
	public DefaultTableModel 	dtm                              ; // 테이블 모델 데이타셋
	////End of Declare 	////End of Declare 	////End of Declare 	////End of Declare 	////End of Declare 
	TalkClient tc = null;
	/**
	 * Create the application.
	 */
	public ChatRoomList_Panel() { //생성자
		initialize(); //화면 출력 메소드 실행
	}
	////// End of Const////// End of Const////// End of Const////// End of Const
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() { //화면출력 메소드
/////////////추가패널 부분
		String imgPath = "src/imgs/"; // 이미지경로
		
		this.add(jbl_chatListTitle = new JLabel("ChatRoom LIST★")); // 타이틀 라벨 생성 및 패널에 부착
		jbl_chatListTitle.setFont(new Font("굴림", Font.BOLD, 12)); // 라벨 문구 서식 지정 
		jbl_chatListTitle.setHorizontalAlignment(SwingConstants.CENTER); // 라벨 가운데 정렬
		jbl_chatListTitle.setBounds(60, 57, 160, 20); // 라벨 위치지정
	
		this.add(jtf_chatListSearch = new JTextField()); // 검색 입력창 생성 및 패널에 부착
		jtf_chatListSearch.setBounds(50, 22, 134, 20); // 검색창 위치지정
		jtf_chatListSearch.setBorder(new TitledBorder(new LineBorder(Color.black,1))); // 검색창 경계선 지정
		jtf_chatListSearch.setColumns(10); // 검색창 컬럼 값 추가
		
		this.add(jbtn_chatSearch= new JButton( // 검색 버튼 추가 및 생성
				new ImageIcon(imgPath+"search.png")));
		jbtn_chatSearch.setOpaque(false); // 버튼 채우기 비활성
		jbtn_chatSearch.setBorderPainted(false); // 버튼 경계없음
		jbtn_chatSearch.setContentAreaFilled(true); //버튼 작동시 채우기 있음
		jbtn_chatSearch.setBounds(205, 17, 30, 30); // 버튼위치 지정
		jbtn_chatSearch.setBackground(Color.WHITE); //버튼 배경색 지정

		this.add(scrollPane = new JScrollPane()); // 스크롤페인 생성 및 부착
		scrollPane.setBackground(Color.WHITE); // 스크롤 페인 배경색 지정
		scrollPane.setBounds(3, 80, 248, 343); // 스크롤 페인 위치지정
		scrollPane.setViewportView // 스크롤 페인에 테이블 붙이고 데이타셋 주입
		(
		 jtb = new JTable
		(dtm = new DefaultTableModel(data,cols){ //테이블 내에서 수정 금지
			public boolean isCellEditable(int row, int col) { return false; }
			})
		);
		jtb.setBorder(UIManager.getBorder("Tree.editorBorder")); // 테이블 경계 스타일 지정
		jtb.setShowVerticalLines(false); // 테이블 내부 수직선 없음
		
		///****여기야 여기 ****////////****여기야 여기 ****////////****여기야 여기 ****/////
		jtb.addMouseListener(new ChatRoomList_Ctrl(this));
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();// // DefaultTableCellHeaderRenderer 생성 (가운데 정렬을 위한)생성 (가운데 정렬을 위한)
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);// DefaultTableCellHeaderRenderer의 정렬을 가운데 정렬로 지정
		TableColumnModel tcmSchedule = jtb.getColumnModel();// 정렬할 테이블의 ColumnModel을 가져옴
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {// 반복문을 이용하여 테이블을 가운데 정렬로 지정
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
			}
		///****여기야 여기 ****////////****여기야 여기 ****/////
		JTableHeader jth = new JTableHeader();
		JTableHeader jth_sal = jtb.getTableHeader();
		jth.setReorderingAllowed(false);
		
//////// 채팅 리스트 패널 ////  //// 마지막에 기준 프레임 지워서 치는 것///////
		jtb.getColumnModel().getColumn(0).setMinWidth(0);
		jtb.getColumnModel().getColumn(0).setMaxWidth(0);
		this.setBackground(Color.WHITE); // 패널 배경색 지정 
		this.setBounds(0, 0, 272, 430); //패널 위치지정 
		this.setLayout(null); //패널 레이아웃 앱솔루트
	}
////// End of initialize////// End of initialize////// End of initialize////// End of initialize////// End of initialize
} 
///End of This Class0 ///End of This Class ///End of This Class
