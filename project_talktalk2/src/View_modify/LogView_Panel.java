package View_modify;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class LogView_Panel extends JPanel {  // This Class for set image to LoginView
	///Declare
	String imgPath = "src/imgs/";   //Imgpath		//이미지 경로
	ImageIcon ig 		= new ImageIcon(imgPath+"login.png"); // 이미지 경로와 이미지 파일
	///End of Delcare
	public void paintComponent(Graphics g) {//  배경화면 넣기 위한 페인트 컴포넌트 메소드
		g.drawImage(ig.getImage(), 0, 0, null);
		setOpaque(false);
		super.paintComponent(g);
	}
	///End of Method paintComponent	///End of Method paintComponent	///End of Method paintComponent	///End of Method paintComponent
	
}/// End of This Class/// End of This Class/// End of This Class/// End of This Class/// End of This Class
