package View_modify;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import server.TalkClient;

public class MemberList_Panel extends JPanel{                                
	JLabel listTitle_jbl 				                  ;	 //멤버리스트 타이틀 라벨
	JButton CreateChat_jbtn 				              ;	 // 채팅방 생성 버튼
	JButton search_jbtn 				                  ;  //멤버 찾기 버튼
	JTextField memeberListSearch_jtf_MemberListPanel      ;  //멤버 검색 입력창
	JScrollPane memberListTreeScrollPane		          ;  //트리가  붙을 스크롤 페인
	String 				cols[]		= {"사업부","사원번호","사원이름","상태정보"} ; // 컬럼 헤더 정보
	String 		    	data[][] 	= new String[0][4]	 ; // 테이블 크기
	JTable			  	jtb 			                 ; // 테이블 
	public DefaultTableModel 	dtm                      ; // 테이블 모델 데이타셋
	JScrollPane 		scrollPane 		                 ; // 테이블이 붙을 스크롤 페인
	TalkClient tc = null;
	public MemberList_Panel() { //생성자
		initialize(); // 화면 출력 메소드 실행
	}
	
	private void initialize() {	// 화면출력 메소드
	String imgPath = "src/imgs/";	// 이미지 경로
		
	MemberList_Ctrl mc = new MemberList_Ctrl(this);
	this.add(memeberListSearch_jtf_MemberListPanel = new JTextField()); //검색 입력창 생성 및 패널에 부착
	memeberListSearch_jtf_MemberListPanel.setBounds(50, 22, 134, 20); //검색 입력창 생성 크기 위치지정
	memeberListSearch_jtf_MemberListPanel.setBorder  //검색 입력창 경계선 
	(new TitledBorder(new LineBorder(Color.black,1))); // 타이틀 보더 라인보더로 검은색 두께1
	memeberListSearch_jtf_MemberListPanel.setColumns(10); // 검색 입력창 생성 컬럼 지정
	
	
	this.add(listTitle_jbl = new JLabel("MEMBER LIST★")); // 멤버 리스트 라벨 생성 및 부착
	listTitle_jbl.setFont(new Font("굴림", Font.BOLD, 12)); // 멤버 리스트 라벨 문구지정
	listTitle_jbl.setHorizontalAlignment(SwingConstants.CENTER); // 멤버리스트  라벨 가운데 정렬
	listTitle_jbl.setBounds(40, 57, 160, 20); // 멤버 리스트 라벨  위치 크기 지정
	
	this.add(search_jbtn = new JButton( 
			new ImageIcon(imgPath+"search.png")));  //검색 버튼 이미 부착 해서 생성과 동시에 패널에 부착
	search_jbtn.setOpaque(false); // 검색 버튼 작동시 채우기 안함
	search_jbtn.setBorderPainted(false); // 검색버튼 경계선 없음
	search_jbtn.setContentAreaFilled(true); //검색버튼 내용 채우기
	search_jbtn.setBounds(205, 17, 30, 30); // 검색버튼 위치 크기 지정
	search_jbtn.setBackground(Color.WHITE); // 검색버튼 배경색 지정
	search_jbtn.addActionListener(mc); // 검색버튼  액션리스너
	
	this.add(CreateChat_jbtn = new JButton(
			new ImageIcon(imgPath+"plus.png"))); // 채팅 만들기 버튼 생성 및 부착과 이미지 넗기 그리고 패널에 부착
	CreateChat_jbtn.setOpaque(false); //  채팅생성 버튼 채우기 없음
	CreateChat_jbtn.setBorderPainted(false); // 채팅생성 버튼 테두리 없음
	CreateChat_jbtn.setContentAreaFilled(true); //  채팅생성 버튼 눌릴때 영역 채움
	CreateChat_jbtn.setBounds(205, 53, 30, 30);  // 채팅생성 버튼 위치 크기 지정
	CreateChat_jbtn.setBackground(Color.WHITE); // 채팅생성 버튼 배경색 지정
	CreateChat_jbtn.addActionListener(mc); // 채팅생성버튼 작동 리스너
	this.add(scrollPane = new JScrollPane()); // 스크롤페인 생성 및 부착
	scrollPane.setBackground(Color.WHITE); // 스크롤 페인 배경색 지정
	scrollPane.setBounds(4, 100, 248, 320); // 스크롤 페인 위치지정
	scrollPane.setViewportView // 스크롤 페인에 테이블 붙이고 데이타셋 주입
	(
	 jtb = new JTable
	(dtm = new DefaultTableModel(data,cols))
	);	
	jtb.getCellEditor();
    JTableHeader jth_sal = jtb.getTableHeader();
    jth_sal.setReorderingAllowed(false);
	//DefaultMutableTreeNode node_1 = new DefaultMutableTreeNode("Dept");  
	//JTree tree = new JTree(node_1); // Jtree 생성
	//this.add(memberListTreeScrollPane = new JScrollPane(tree)); // JTree 스크롤페인 을 패널에 부착
	//memberListTreeScrollPane.setBounds(4, 100, 248, 320);  // JTree  스크롤 페인 위치 크기 지정
	//DefaultMutableTreeNode a= new DefaultMutableTreeNode("Wolverine"+"(상태메시지)");
	//DefaultMutableTreeNode b=new DefaultMutableTreeNode("Wolverine"+"(상태메시지)");
	//node_1.add(a);
	//node_1.add(b);
	//
	//	new DefaultMutableTreeNode("memberListTree") {  // 트로 데이터셋 /// 노드 추가 
	//		{
	//			DefaultMutableTreeNode node_1;  // 노드 데이터셋 추가
	//			node_1 = new DefaultMutableTreeNode("Dept");                                    //샘플
	//				node_1.add(new DefaultMutableTreeNode("Wolverine"+"(상태메시지)"));        		//샘플
	//				node_1.add(new DefaultMutableTreeNode("Venom"+"(상태메시지)"));             	//샘플
	//				node_1.add(new DefaultMutableTreeNode("Iron-Man"+"(상태메시지)"));          	//샘플
	//			add(node_1);                                                                    //샘플
	//			node_1 = new DefaultMutableTreeNode("CityWar");                                 //샘플
	//				node_1.add(new DefaultMutableTreeNode("CaptainAmerica"));                   //샘플
	//				node_1.add(new DefaultMutableTreeNode("DoctorStrange"));                    //샘플
	//				node_1.add(new DefaultMutableTreeNode("Thor"));                             //샘플
	//				node_1.add(new DefaultMutableTreeNode("Hawk-eye"));                         //샘플
	//			add(node_1);                                                                    //샘플
	//			node_1 = new DefaultMutableTreeNode("Dept");                                    //샘플
	//			node_1.add(new DefaultMutableTreeNode("Wolverine"));                            //샘플
	//			node_1.add(new DefaultMutableTreeNode("Venom"));                                //샘플
	//			node_1.add(new DefaultMutableTreeNode("Iron-Man"));                             //샘플
	//			add(node_1);
	//
	//			
	//		}
	//	}
	//)); /// 트리 데이터셋  끝
	
	this.setBackground(Color.WHITE); // 멤버리스트 패널 배경색 
	this.setBounds(0, 0, 269, 431); // 멤버리스트 패널 위치 크기 지정
	this.setLayout(null); // 멤버리스트 레이아웃 앱솔루트
	this.setVisible(true); // 멤버리스트 패널 출력
	}
	
}

