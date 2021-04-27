package View_modify;

import java.awt.*;
import java.awt.event.*;

public class CardLayoutTest {

 /**
  * 카드레이아웃 이용

    --> 여러장의 카드(패널)를 만들어 원하는 카드를 가장 윗쪽에 올린다는 개념으로 이해하면 된다.

    --> 한개의 Frame에 여러개의 Panel 을 만들어 활용해야 한다.
  */
 public static void main(String[] args) {
  // TODO Auto-generated method stub
  CardLay css = new CardLay();
  css.setVisible(true);
  css.setSize(500, 500);
  
  css.addWindowListener(new WindowAdapter(){
   public void windowClosing (WindowEvent  e) {
    System.exit(1);
   }
  });
 }

}
class CardLay extends Frame implements ActionListener{
 Button b1,b2,b3;
 Panel p,p1,p2,p3;
 CardLayout card; //카드레이아웃 사용을 위해
 CardLay(){
     super("카드레이아웃");
     p = new Panel();
     p1 = new Panel();  p1.setBackground(Color.blue);
  
     TextField tf = new TextField();
  
     p2 = new Panel();  p2.setBackground(Color.red);
     p3 = new Panel();  p1.setBackground(Color.black);
     b1 = new Button("변경1");  b1.addActionListener(this);
     b2 = new Button("변경2");  b2.addActionListener(this);
     b3 = new Button("변경3");  b3.addActionListener(this);
   //버튼을 클릭했을 때 카드가 바뀌도록 처리

    p1.add(b1);
    p2.add(b2);
    p3.add(b3);
    p1.add(tf);
  
  card = new CardLayout(0,0);   // 0,0은 윈도우 창과 레이아웃 사이의 공백 사이즈를 의미함.
  setLayout(card);
  p1.setLayout(new FlowLayout(FlowLayout.CENTER));
  p2.setLayout(new FlowLayout(FlowLayout.CENTER));
  p3.setLayout(new FlowLayout(FlowLayout.CENTER));
  
  add("a1",p1);
  add("a2",p2);
  add("a3",p3);  // Panel을 Frame에 삽입할때 a1,a2,a3 라는 임의의 이름을 지정해주어야 한다.
 }
 @Override
 public void actionPerformed(ActionEvent e) {
  String str = e.getActionCommand();
  if(str.equals("변경1")){
   card.show(this,"a3"); //이와같은 방법으로 다른 카드(Panel)로의 변환이 가능하다.
  }
  else if(str.equals("변경2")){
   card.show(this,"a1");
  }
  else if(str.equals("변경3")){
   card.show(this,"a2");
  }
 }
}

