package server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;




public class login2 extends JFrame {
	//서버단--------------
	//////1-1-4. 나의 소켓을 넣을 통 생성	
	Socket mySocket = null;
	//////1-1-5. 나의 말을 전달한 inputstream	
	ObjectInputStream ois  = null;
	//////1-1-6. 다른 사람의 말을 받을 outputstream	
	ObjectOutputStream oos = null;		
	//////1-1-7. Server의 ip와 port를 가져옴.
	String ip = "localhost";
	int port = 3002;
	/////////////////////////////
	TalkManager2 tm = null;
	TalkManagerThread2 tct = null;
	public void connect_process() {
		try {
			//////3-2.서버에 접속과 동시에  클라이언트의 소켓 생성
			//통신은 지연될 수 있다.-wait - try...catch해야함. 연결 ㄱㄱ TalkServer대로 작동 시작 ㄱㄱ
			mySocket = new Socket(ip,port);
			//////3-3.내가 입력한 정보를 서버에게 보내는 통로
			oos = new ObjectOutputStream
					(mySocket.getOutputStream());			
			//////3-4. 서버로부터 정보를 받는 통로
			ois = new ObjectInputStream
						(mySocket.getInputStream());
			//////TalkServerThread안의 2-4인 이부분으로  String msg = (String)ois.readObject();부분으로 이 값이 넘어간다.
			//Protocol.ACCESS만 넣으면 숫자이기 때문에  +""을 붙여서 문자 처리 될 수 있게 해줌.
			oos.writeObject(Protocol.ACCESS+"");
			//////3-10. 나의 스래드가 담긴 주소번지를 담아서 TalkClientThread에 넣어줌 ㄱㄱ
			tct = new TalkManagerThread2(this);
			//////3-11. TalkClientThread의 run호출!
			tct.start();//TalkClientThread의 run호출됨.-콜백함수
		} catch (Exception e) {
			//////3-12. 엘러를 알려줌
			System.out.println(e.toString());//에러 힌트문 출력.
		}		
	}	
	
	public void loginSuccess(int loginTrueFalse,String loginMsg) {
		System.out.println(loginMsg);
		//////성공했을때만 루프가 돌아가면 됨.
		if(loginTrueFalse==1) {
			this.setVisible(false);
			TalkManager2 tm = new TalkManager2(this);
		}
	}
	public void signupSuccess(String signupResult) {
			System.out.println(signupResult);
		
	}
	LoginNmanger lnc = null;
	SignUp signup = new SignUp();
	//////////////////////////////////////////
	
	
	
	
	JTextField logView_EmpNo_TextField;
	JTextField logView_EmpPw_TextField;
	JPanel	   logView_Panel ;
 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		login2 window = new login2();
	}

	/**
	 * Create the application.
	 */
	public login2() {
		initialize();
		//서버한테 들어옴을 전달함.///////////////////
		connect_process();
		///////////////////////////////////////
	}

	/**
	 * Initialize the contents of the frame.
	 */
	JButton logView_Login_Button = new JButton("LOGIN");
	JButton logView_Join_Button = new JButton("Join");
	public void initialize() {
	
		logView_Panel  = new JPanel();
		logView_Panel.setSize(346, 473);
		logView_Panel.setLocation(0, 0);
		logView_Panel.setLayout(null);
		
		Label logView_EmpNo_Label = new Label("EmployeeNO");
		logView_EmpNo_Label.setFont(new Font("굴림", Font.BOLD, 14));
		logView_EmpNo_Label.setBounds(44, 253, 130, 30);
		logView_Panel.add(logView_EmpNo_Label);
		
		Label logView_EmpPw_Label = new Label("Password");
		logView_EmpPw_Label.setFont(new Font("Arial Black", Font.BOLD, 14)); 
		logView_EmpPw_Label.setBounds(47, 302, 120, 25);
		logView_Panel.add(logView_EmpPw_Label);
		
		logView_EmpNo_TextField = new JTextField();
		logView_EmpNo_TextField.setBounds(181, 259, 106, 25);
		logView_Panel.add(logView_EmpNo_TextField);
		logView_EmpNo_TextField.setColumns(10);
		
		logView_EmpPw_TextField = new JTextField();
		logView_EmpPw_TextField.setBounds(181, 302, 106, 25);
		logView_Panel.add(logView_EmpPw_TextField);
		logView_EmpPw_TextField.setColumns(10);
		
		//JButton logView_Login_Button = new JButton("LOGIN");
		logView_Login_Button.setFont(new Font("Arial Black", Font.BOLD, 11));
		logView_Login_Button.setBounds(194, 388, 108, 30);
		getContentPane().add(logView_Login_Button);
		
		//JButton logView_Join_Button = new JButton("Join");
		logView_Join_Button.setFont(new Font("Arial Black", Font.BOLD, 11));
		logView_Join_Button.setBounds(43, 388, 108, 30);
		getContentPane().add(logView_Join_Button);
		
		getContentPane().add(logView_Panel);
		this.setResizable(false);
		this.getContentPane().setBackground(new Color(255, 245, 238));
		this.setBounds(0, 0, 350, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setDefaultLookAndFeelDecorated(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
		lnc= new LoginNmanger(this);
		logView_Login_Button.addActionListener(lnc);
		logView_Join_Button.addActionListener(lnc);
		signup.btnNewButton.addActionListener(lnc);
		
	}

}
