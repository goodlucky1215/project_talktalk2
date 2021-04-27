package View_modify;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import server.Protocol;

public class Config_Panel_Ctrl implements ActionListener {
	Config_Base_Panel config_Base_Panel; // 주입되는 패널 클래스 선언
	MenuList_View menuList_View;//주입되는 패널 클래스 선언
	Config_Panel_Ctrl(Config_Base_Panel config_Base_Panel){ // 생성자 파라미터 뷰
		this.config_Base_Panel = config_Base_Panel; // 선언문과 동기화
	}

	@Override
	public void actionPerformed(ActionEvent cae) {// 액션 리스너
		Object obj = cae.getSource(); // 느껴지는 액션 담아
		if (config_Base_Panel.userInfo_jbtn==obj) {// 사용자 정보 감지되면
			System.out.println("사용자 정보");
			try {
				config_Base_Panel.tc.oos.writeObject(Protocol.MY_INFO+"");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			config_Base_Panel.cscp.setVisible(true);
		}
		else if (config_Base_Panel.changeStatus_jbtn==obj) { // 상태 바꾸기창 버튼 느껴진다면
			System.out.println("사용자 상태변경");
			config_Base_Panel.cup.setVisible(true); // 인포패널 쨘
		}
		else if (config_Base_Panel.cup.userInfo_Update_jbtn==obj) { // 상태 바꾸기창
			System.out.println(config_Base_Panel.cup.userInfo_Status_comboBox.getSelectedItem().toString());
			//값을 받아서 넘겨 넣어주겠지
			try {
				config_Base_Panel.tc.oos.writeObject(Protocol.MY_STATE
							   +Protocol.seperator+config_Base_Panel.cup.userInfo_Status_comboBox.getSelectedItem().toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			JOptionPane.showMessageDialog(null, "상태변경  완료되었습니다.", "상태변경", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (config_Base_Panel.LogOut_jbtn==obj) { //로그아웃 버튼이 온다면
			try {
				config_Base_Panel.tc.oos.writeObject(Protocol.LOGOUT+"");
				//여기에 그 사람 exit() 시켜준다.
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
			System.exit(0); // JVM 꺼진닷
		}
	}
}
