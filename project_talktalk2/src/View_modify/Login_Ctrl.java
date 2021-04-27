package View_modify;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import server.Protocol;

public class Login_Ctrl implements ActionListener{  // 로그인 컨트롤러 작동
	Login_view login_view = null; //로그인 뷰 선언
	public Login_Ctrl(Login_view login_view) { // 생성자 파라미터 값으로 해당 정보 받음
		this.login_view = login_view; // 변수에 동기화
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource(); // 반응 되는 이벤트는 객체형으로 모두 받는다.
		if (obj==login_view.logView_Login_Button) { // 만약 로그인 버튼 눌러
			System.out.println("로그인버튼 호출 성공"); // 확인용 출력문구
			// 로그인 받는 부분 
			String login_no = login_view.logView_MemberNo_jtf.getText(); // 입력창에 받은 사원번호 값 받기
			String login_pw = login_view.logView_MemberPw_jtf.getText(); // 비밀번호 입력창에 값 받기
			System.out.println(login_no+" "+login_pw);
			if(login_no.length()==0) {
				JOptionPane.showMessageDialog(null, "아이디를 입력해주세요!", "로그인", JOptionPane.INFORMATION_MESSAGE);
			}
			else if(login_pw.length()<4) {
				JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요!", "로그인", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				try {
					//로그인 회원 번호랑 비밀번호를 서버에게 전송
					login_view.tc.oos.writeObject(Protocol.LOGIN+Protocol.seperator+login_no+Protocol.seperator+login_pw);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}
		}
		else if (obj==login_view.logView_Join_Button) {  //회원가입버튼을 누른다
			System.out.println("회원 가입 버튼 호출 성공"); // 확인용 출력문구
			login_view.suv.setVisible(true);// 회원가입 창 생성.
		}
		else if (login_view.suv.signUpView_Submit_jbtn == obj) {  // 제출버튼 누를시
			///***********************************************//// 회원가입 입력부		
//					직급 콤보에서 선택된거 받아오기
					System.out.println("회원가입 제출버튼");
//					이 뒤로는 제출버튼 눌리고 서버 -> DB 실행문
					String mem_name  = login_view.suv.signUpView_MemberName_Name.getText();
//					 JTEXTFIELD 에서 이름받아오는 거
					String mem_pw    = login_view.suv.signUpView_Pw_jtf.getText();
//					 JTEXTFIELD 에서 패스워드 받아오는거
					String mem_no    = login_view.suv.signUpView_MemeberNo_jtf.getText();
//					JTEXTFIELD 에서 사원번호 받아오는거
					String mem_dept  = login_view.suv.signUpView_DeptComboBox.getSelectedItem().toString();
//					 부서 콤보박스에서 선택된거 받아오기
					String mem_level = login_view.suv.signUpView_EmpLevelComboBox.getSelectedItem().toString();
					if(mem_no.length()==0) {
						JOptionPane.showMessageDialog(null, "사원 번호를 입력해주세요!", "회원가입", JOptionPane.INFORMATION_MESSAGE);
					}
					else if(mem_pw.length()<4) {
						JOptionPane.showMessageDialog(null, "비밀번호를 4자 이상 입력해주세요!", "회원가입", JOptionPane.INFORMATION_MESSAGE);
					}
					else if(mem_name.length()==0) {
						JOptionPane.showMessageDialog(null, "회원 이름을 입력해주세요!", "회원가입", JOptionPane.INFORMATION_MESSAGE);
					}
					else if(mem_dept.equals("선택")) {
						JOptionPane.showMessageDialog(null, "부서를 선택해주세요!", "회원가입", JOptionPane.INFORMATION_MESSAGE);
					}
					else if(mem_level.equals("선택")) {
						JOptionPane.showMessageDialog(null, "직급을 선택해주세요!", "회원가입", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						try {
							//회원가입에 필요한 재료들을 넣어서 서버에게 전송
							login_view.tc.oos.writeObject(Protocol.SIGNUP
									+Protocol.seperator+mem_no
									+Protocol.seperator+mem_pw
									+Protocol.seperator+mem_name
									+Protocol.seperator+mem_dept					
									+Protocol.seperator+mem_level					
									);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
		else if (login_view.suv.signUpView_Cancel_jbtn == obj) { // 취소버튼 누르면
				login_view.suv.setVisible(false);
			}
	}
	
	
}
