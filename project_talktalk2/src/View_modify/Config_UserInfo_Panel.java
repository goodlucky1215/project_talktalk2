package View_modify;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JToggleButton;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JList;
import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class Config_UserInfo_Panel extends JFrame {
	///Declare
	JLabel  userInfo_status_jbl				; //패널에 붙는 상태표시 타이틀  라벨
	JLabel  userInfo_statusChange_jbl 		; //상태 변경 변경 안내 라벨
	JButton userInfo_Update_jbtn			; //수정 버튼
	JComboBox userInfo_Status_comboBox		; // 상태 나타내는 콤보박스 이하 상태박스
	///End of Declare	///End of Declare	///End of Declare	///End of Declare	///End of Declare
	/**
	 * Create the panel.
	 * @param config_Base_Panel 
	 */
	Config_Base_Panel config_Base_Panel = null;
	public Config_UserInfo_Panel(Config_Base_Panel config_Base_Panel) { // 생성자
		this.config_Base_Panel = config_Base_Panel;
		Initialize(); // 화면 출력 메소드 실행
	}
	///End of Const ///End of Const ///End of Const ///End of Const ///End of Const
	public void Initialize() { //화면 출력 메소드
		
		
		this.setBackground(new Color(255, 255, 255)); // 패널 색상지정
		this.setLayout(null); // 패널 레이아웃 앱솔루트
		this.setBounds(0, 0, 272, 430);   // 패널 위치 지정
		
		
	/// Panel Position Declare And Color/////// Panel Position Declare And Color/////// Panel Position Declare And Color//// 
		this.add(userInfo_status_jbl = new JLabel("상    태"));  // 상태 안내 라벨 생성 및 패널에 부착
//		userInfo_status_jbl.setEnabled(false); // 상태 라벨은 사용 못하게
		userInfo_status_jbl.setHorizontalAlignment(SwingConstants.CENTER); // 유저  상태 라벨 가운데 정렬
		userInfo_status_jbl.setBackground(new Color(255, 160, 122)); // 유저 상태 라벨 배경색 지정
		userInfo_status_jbl.setBounds(43, 189, 52, 15); // 유저 상태 라벨 위치지정
		
		
		this.add(userInfo_statusChange_jbl = new JLabel("상태 변경")); // 상태변경 안내 라벨 생성 및 패널에 부착
		userInfo_statusChange_jbl.setHorizontalAlignment(SwingConstants.CENTER); //유저 상태라벨 가운데 정렬
		userInfo_statusChange_jbl.setBounds(64, 32, 130, 17); // 상태 변경 라벨 위치지정
		userInfo_statusChange_jbl.setFont(new Font("굴림", Font.BOLD, 14)); // 유저 상태 안내 라벨 문구 서식 지정
		
		this.add(userInfo_Update_jbtn = new JButton("수  정")); // 수정 버튼 생성 및 페널이 추가
		userInfo_Update_jbtn.setBackground(new Color(100, 149, 237)); // 수정 버튼 배경 색지정
		userInfo_Update_jbtn.setBounds(167, 348, 75, 23); // 유저 생성 버튼 위치지정
		
		this.add(userInfo_Status_comboBox = new JComboBox()); // 상태박스 새성 및 페널에 부착
		userInfo_status_jbl.setLabelFor(userInfo_Status_comboBox); // 상태박스 이름라벨 추가
		userInfo_Status_comboBox.setBackground(new Color(100, 149, 237)); // 상태라벨 배경색 지정
		userInfo_Status_comboBox.setModel // 상태라벨 데이타 모델 샘플 추가
		(new DefaultComboBoxModel
				(new String[] {"근무중", "자리비움", "다른 용무 중", "외근", "휴가", "반차", "깨지는 중"})
				);
		userInfo_Status_comboBox.setBounds(102, 185, 130, 23); // 상태박스 위치지정
		userInfo_Update_jbtn.addActionListener(config_Base_Panel.cpc);
		this.setVisible(false);
	/// End of Set Panel	 
	}
////// End of Initialize////// End of Initialize////// End of Initialize////// End of Initialize
}///End of This Class///End of This Class///End of This Class///End of This Class///End of This Class