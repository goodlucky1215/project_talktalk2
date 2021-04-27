package View_modify;

import java.awt.Color;
import java.awt.Component;
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
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import server.TalkClient;

import javax.swing.UIManager;
import javax.swing.JScrollBar;

public class GroupChatRoomList_Panel extends JPanel {         
	///Declare
	JLabel 				jbl_GroupchatListTitle 			     ; // 패널에 붙는 제목  라벨
	String 				cols[]		= {"번호","제목"} 	 ; // 컬럼에 헤더 값
	String 		    	data[][] 	= new String[0][2]		 ; // 초기 데이터 크기 지정
	JTable			  	group_jtb 			                 ; //그룹 채팅 테이블 
	JButton 			jbtn_GroupChatSearch 			     ; // 그룹채팅 찾기 버튼
	JTextField 			jtf_chatListSearch		         	 ; // 그룹 채팅 찾을 떄 입력창
	JScrollPane 		scrollPane 		                 	 ; // 테이블 상위 스크롤 페인
	public DefaultTableModel 	group_dtm                    ; // 그룹채팅 테이블  데이타셋
	////End of Declare 	////End of Declare 	////End of Declare 	////End of Declare 	////End of Declare 

	/**
	 * Create the application.
	 * @param tc 
	 */
	GroupChatRoomList_Ctrl gcrc = null;
	TalkClient tc = null;
	public GroupChatRoomList_Panel() { // 생성자
		initialize(); // 화면 출력 메소드  실행
	}////// End of Const////// End of Const////// End of Const////// End of Const
/// End of Const/// End of Const/// End of Const/// End of Const/// End of Const
	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() { // 화면 출력 메소드 
/////////////추가패널 부분
		String imgPath = "src/imgs/"; //이미지 경로 
//		this = new JPanel();
		
		this.add(jbl_GroupchatListTitle = new JLabel("GoupChatRoom LIST★")); // 그룹채팅 타이틀 라벨 생성 및 부착
		jbl_GroupchatListTitle.setFont(new Font("굴림", Font.BOLD, 12));// 그룹채팅 타이틀 라벨 문구 서식지정
		jbl_GroupchatListTitle.setHorizontalAlignment(SwingConstants.CENTER); // 그룹 채팅라벨 가운데 정렬
		jbl_GroupchatListTitle.setBounds(60, 57, 160, 20); // 그룹 채팅 라벨 위치지정
	
		this.add(jtf_chatListSearch = new JTextField()); // 검색 입력창 생성 및 프레임에 부착
		jtf_chatListSearch.setBounds(50, 22, 134, 20); // 검색 입력창 크기 지정
		jtf_chatListSearch.setBorder(new TitledBorder(new LineBorder(Color.black,1))); // 검색 입력창 경계값 지정
		jtf_chatListSearch.setColumns(10); // 그룹 입력창 컬럼값 지정
		
		this.add(jbtn_GroupChatSearch = new JButton( // 그룹 채팅 검색 버튼 문구 추가하여 생성 후 패널에 부착
				new ImageIcon(imgPath+"search.png"))); // 버튼 이미지 경로 + 파일 이름
		jbtn_GroupChatSearch.setOpaque(false);  // 검색 버튼 채우기 없음
		jbtn_GroupChatSearch.setBorderPainted(false); // 검색 버튼 경계선 없음
		jbtn_GroupChatSearch.setContentAreaFilled(true); // 버튼 눌릴때 반응하는 채워짐 있음
		jbtn_GroupChatSearch.setBounds(205, 17, 30, 30); // 검색버튼 위치 지정
		jbtn_GroupChatSearch.setBackground(Color.WHITE); // 검색 버튼 색상 지정

		this.add(scrollPane = new JScrollPane()); // 스크롤페인 생성 및 패널에 부착
		scrollPane.setBounds(3, 80, 248, 343); // 스크롤페인 위치 지정
		group_dtm = new DefaultTableModel(data,cols){ //테이블 내에서 수정 금지
			public boolean isCellEditable(int row, int col) { return false; }
			}; //  데이터셋에 컬럼 크기와데이터 정보 주입
		group_jtb = new JTable(group_dtm);
		scrollPane.setViewportView(group_jtb); // 스크롤 페인에 테이블 부착
		group_jtb.setBorder(UIManager.getBorder("Tree.editorBorder")); // 테이블 경계선스타일 지정
		group_jtb.setShowVerticalLines(false); // 테이블 내부 수직라인 안보이기
		
		group_jtb.addMouseListener(new GroupChatRoomList_Ctrl(this)); // 액션리스너
		DefaultTableCellRenderer tScheduleCellRenderer = new DefaultTableCellRenderer();// // DefaultTableCellHeaderRenderer 생성 (가운데 정렬을 위한)생성 (가운데 정렬을 위한)
		tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);// DefaultTableCellHeaderRenderer의 정렬을 가운데 정렬로 지정
		TableColumnModel tcmSchedule = group_jtb.getColumnModel();// 정렬할 테이블의 ColumnModel을 가져옴
		for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {// 반복문을 이용하여 테이블을 가운데 정렬로 지정
			tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
			}
		
		JTableHeader jth = new JTableHeader();
		JTableHeader jth_sal = group_jtb.getTableHeader();
		jth.setReorderingAllowed(false);	
		group_jtb.getColumnModel().getColumn(0).setMinWidth(0);
		group_jtb.getColumnModel().getColumn(0).setMaxWidth(0);
		this.setBackground(Color.WHITE); // 패널 배경색 지정
		this.setBounds(0, 0, 272, 430); // 패널 크기 지정
		this.setLayout(null); // 패널 레이아웃 앱솔루트
	}
////// End of initialize////// End of initialize////// End of initialize////// End of initialize////// End of initialize
} ///End of This Class0 ///End of This Class ///End of This Class
