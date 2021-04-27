package View_modify;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Composite;
import javax.swing.DefaultComboBoxModel;

//class SignUp_View_Panel extends JPanel { // Inner Class for JPanel's Grade // 그라데이션을 위한 이너 클래스
//
//   public void paint(Graphics g) {  /// 그라데이션 그래픽 메소드
//        Graphics2D g2 = (Graphics2D) g; // 그래픽 g2 생성
//        
//        GradientPaint gp = new GradientPaint(0,0, new Color(000, 000, 051), 
//                                   250,250, new Color(30, 144, 255) );  // 지점에서 무슨색으로 어떤 지점에 무슨색으로 변한다.
//        
//        
//        Composite composite = g2.getComposite(); //콤포짓트 출력 생성자
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //렌더링 안알리아스 켜주기
//        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f)); // 투명도 0.7
//
//        
//        g2.setPaint(gp);  // 페인트 적용
//        g2.fill(new Rectangle2D.Double(0,0,400,420)); //칠해지는 크기 지정
//
//   }/// End of paint method///End of paint method///End of paint method///End of
//      /// paint method///End of paint method
//
//}/// End of Inner Class SignUp_View_Paint///End of Inner Class
//   /// SignUp_View_Paint///End of Inner Class SignUp_View_Paint

public class SignUp_View extends JDialog {
   // Var
   JPanel          SignUp_View_jpl                       ; //회원가입 베이스 패널
   JLabel          signUpView_Level_jbl                   ; //회원가입 직책 안내 라벨
   JLabel          signUpView_Pw_jbl                       ; //회원가입 패스워드 안내 라벨
   JLabel          signUpView_Deptname_jbl                   ; //회원가입 부서 이름 안내 라벨
   JLabel          signUpView_MemberName_jbl                 ; // 회원가입 사원이름 안내 라벨
   JLabel          signUpView_MemeberNo_jlb                ; // 회원가입 사원번호 안내 라벨
   JButton       signUpView_Submit_jbtn                    ; // 제출 버튼 
   JButton       signUpView_Cancel_jbtn                    ; // 취소버튼
   JComboBox       signUpView_DeptComboBox                   ; // 부서 콤보 박스
   JComboBox       signUpView_EmpLevelComboBox              ; // 직급 콤보박스
   JTextField       signUpView_MemberName_Name               ; // 사원 이름 입력창
   JPasswordField       signUpView_Pw_jtf                        ; // 패스워드 입력창
   JTextField       signUpView_MemeberNo_jtf                 ; // 사원번호 입력창
//////////End of Var ///////////////End of Var ///////////////End of Var ///////////////End of Var ///////////////End of Var /////   
   
//   /**
//    * Launch the application.
//    */
//   public static void main(String[] args) {
//      SignUp_View window = new SignUp_View();
//   } //////////End of Launch the application.//////////End of Launch the application.//////////End of Launch the application.//////////End of Launch the application.

   /**
    * Create the application.
    */
   public SignUp_View() { //생성자 
      initialize(); // 화면 출력 메소드 출력
   }// End of Method"initialize"/
   ////End of Const/
   
   /**
    * Initialize the contents of the frame.
    */
   private void initialize() { // 화면 출력 메소드

//      SignUp_View_jpl    = new SignUp_View_Panel(); //회원가입창 베이스 패널 생성
      SignUp_View_jpl    = new JPanel(); //회원가입창 베이스 패널 생성
//      SignUp_View_Panel signUp_View_Paint = new SignUp_View_Panel(); // 그라데이션 뿌려주기
//      SignUp_View_jpl   .add(signUp_View_Paint);// "Graphics2D" //panel.setBackground(Color.WHITE);
      SignUp_View_jpl   .setBackground(Color.WHITE);
      SignUp_View_jpl   .setLayout(null); // 패널 레이아웃 앱솔루트 
      getContentPane().add(SignUp_View_jpl   , BorderLayout.CENTER); // 프레임에 베이스 패널 추가
      
      signUpView_Deptname_jbl = new JLabel("Department"); // 회원가입 부서 안내 라벨 부착
      signUpView_Deptname_jbl.setFont(new Font("Dialog", Font.BOLD, 14)); //회원가입 문구 지정
      signUpView_Deptname_jbl.setHorizontalAlignment(SwingConstants.CENTER); // 회원가입 라벨 사운데 정렬
      signUpView_Deptname_jbl.setBounds(32, 196, 130, 15); // 회원가입 부서 안내 라벨 위치 지정
      SignUp_View_jpl   .add(signUpView_Deptname_jbl); // 회원가입  베이스 패널에 부서 이름 안내 라벨 부착

      signUpView_MemberName_jbl = new JLabel("Name"); // 사원 이름 안내 라벨  생성
      signUpView_MemberName_jbl.setFont(new Font("Dialog", Font.BOLD, 14)); // 사원이름 라벨 문구
      signUpView_MemberName_jbl.setHorizontalAlignment(SwingConstants.CENTER); // 사원이름 라벨 가운데 정렬
      signUpView_MemberName_jbl.setBounds(32, 137, 130, 15); // 사원 이름 라벨 위치 지정
      SignUp_View_jpl   .add(signUpView_MemberName_jbl); // 회원가입 베이스패널에  사원 이름 라벨 부착.

      signUpView_MemeberNo_jlb = new JLabel("MemberNumber"); //  사원 번호 라벨 생성
      signUpView_MemeberNo_jlb.setFont(new Font("Dialog", Font.BOLD, 14)); // 사원 번호 라벨 문구 지정
      signUpView_MemeberNo_jlb.setHorizontalAlignment(SwingConstants.CENTER); // 사원 번호 라벨 가운데 정렬
      signUpView_MemeberNo_jlb.setBounds(32, 37, 130, 15); // 사원 번호 라벨 위치 지정
      SignUp_View_jpl   .add(signUpView_MemeberNo_jlb); // 베이스 패널에  사원 번호 라벨 부착

      signUpView_Pw_jbl = new JLabel("PassWord"); // 비밀번호 안내 라벨 생성
      signUpView_Pw_jbl.setFont(new Font("Dialog", Font.BOLD, 14)); // 비밀 번호 안내 라벨 문구 지정
      signUpView_Pw_jbl.setHorizontalAlignment(SwingConstants.CENTER); // 비밀 번호 라벨 가운데 정렬
      signUpView_Pw_jbl.setBounds(32, 85, 130, 15); // 비밀번호 안내 라벨 위치지정
      SignUp_View_jpl   .add(signUpView_Pw_jbl); // 베이스 패널에  비밀번호 안내 라벨 부착
      
      signUpView_Level_jbl = new JLabel("Level"); // 직급 라벨 생성
      signUpView_Level_jbl.setFont(new Font("Dialog", Font.BOLD, 14)); // 직급 라벨 문구 지정
      signUpView_Level_jbl.setHorizontalAlignment(SwingConstants.CENTER); // 직급 라벨 가운데정렬
      signUpView_Level_jbl.setBounds(32, 257, 130, 15);// 직급라벨 위치 지정
      SignUp_View_jpl   .add(signUpView_Level_jbl); // 직급 라벨 베이스 패널에 부착

      signUpView_MemeberNo_jtf = new JTextField(); // 사원번호 입력창 생성
      signUpView_MemeberNo_jtf.setBounds(199, 34, 106, 21); // 사원번호 입력창 위 치지정
      SignUp_View_jpl   .add(signUpView_MemeberNo_jtf); // 회원가입 베이스 패널에 사원번호 입력창 부착 
      signUpView_MemeberNo_jtf.setColumns(10); // 사원번호 입력창 컬럼 값 10 추가

      signUpView_Pw_jtf = new JPasswordField(); // 비밀번호 입력창 생성 
      signUpView_Pw_jtf.setBounds(199, 82, 106, 21); // 비밀번호 입력창 위치 지정
      SignUp_View_jpl   .add(signUpView_Pw_jtf); //회원가입 베이스 패널에  비밀번호 입력창  부착
      signUpView_Pw_jtf.setColumns(10); // 비밀번호 입력창에 컬럼값 10 추가

      signUpView_MemberName_Name = new JTextField(); // 사원이름 입력창 생성 
      signUpView_MemberName_Name.setBounds(199, 134, 106, 21); // 사원이름 입력창  위치 지정
      SignUp_View_jpl   .add(signUpView_MemberName_Name); // 회원가입 베이스 패널에 사원번호 입력창 부착
      signUpView_MemberName_Name.setColumns(10); // 사원번호 컬럼 값 10 추가
 
      signUpView_EmpLevelComboBox = new JComboBox(); // 직급 콤보박스 생성
      signUpView_EmpLevelComboBox.setModel(new DefaultComboBoxModel(new String[] {"선택","사원", "주임", "대리", "과장", "차장", "부장"}));
      signUpView_EmpLevelComboBox.setBounds(199, 253, 106, 23); // 직급 콤보 박스 위치 지정
      SignUp_View_jpl   .add(signUpView_EmpLevelComboBox); //회원가입 베이스 패널에  직급 콤보 박스 부착.

      signUpView_DeptComboBox = new JComboBox(); // 부서 콤보박스 생성
      signUpView_DeptComboBox.setModel(new DefaultComboBoxModel(new String[] {"선택","개발1팀", "개발2팀", "홍보팀", "재무팀", "인사팀", "영업팀"}));
      signUpView_DeptComboBox.setBounds(199, 192, 106, 23); // 부서 콤보박스 위치 지정.
      SignUp_View_jpl   .add(signUpView_DeptComboBox); //  회원가입 베이스 패널에  부서 콤보박스 부착
////////////////////////////////////////////////////////////////////////

      
      signUpView_Submit_jbtn = new JButton("Submit"); //제출버튼 생성 
      signUpView_Submit_jbtn.setFont(new Font("굴림", Font.BOLD, 14)); // 제출버튼 문구 지정
      signUpView_Submit_jbtn.setBounds(65, 333, 95, 23); // 제출버튼 위치지정
      signUpView_Submit_jbtn.setForeground(new Color(240, 255, 240)); // 제출버튼 글자색 
      signUpView_Submit_jbtn.setOpaque(true); // 제출버튼 오파큐 채우기 트루
      signUpView_Submit_jbtn.setBorderPainted(true); // 제출버튼 테두리 
      signUpView_Submit_jbtn.setContentAreaFilled(true); // 클릭시 영역 채우기 
      signUpView_Submit_jbtn.setBackground(new Color(30, 144, 255)); // 버튼 배경색
      SignUp_View_jpl   .add(signUpView_Submit_jbtn); //  회원가입 베이스 패널에  제출버튼 부착

      
      signUpView_Cancel_jbtn = new JButton("Cancel"); // 취소버튼 생성
      signUpView_Cancel_jbtn.setFont(new Font("굴림", Font.BOLD, 14)); // 취소 버튼 문구 지정
      signUpView_Cancel_jbtn.setBounds(225, 333, 95, 23); // 취소버튼 위치지정
      signUpView_Cancel_jbtn.setForeground(new Color(240, 255, 240)); // 취소버튼 글자색 지정
      signUpView_Cancel_jbtn.setOpaque(true); //취소버튼 채우기 트루
      signUpView_Cancel_jbtn.setBorderPainted(true); // 취소버튼 경계 
      signUpView_Cancel_jbtn.setContentAreaFilled(true); // 취소버튼 누를때 트루
      signUpView_Cancel_jbtn.setBackground(new Color(30, 144, 255)); // 취소버튼 배경색 
      SignUp_View_jpl   .add(signUpView_Cancel_jbtn); // 회원가입 베이스 패널에  취소버튼 부착
/////////////////////      ////////////////////////////////////////////////////////
      

      new JDialog();//다이얼로그 객체 생성

      this.setBounds(100, 100, 400, 420); // 다이얼로그 크기 지정
      this.setTitle("아라 상사 메신져 회원가입"); // 다이얼로그 타이틀  세팅 
      this.setVisible(false); // 다이얼로그 출력

   }
}// End of Method this Class// End of Method this Class// End of Method this Class// End of Method this Class// End of Method this Class