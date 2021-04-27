package View_modify;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import server.Protocol;
import server.TalkClient;



public class ChatRoomList_Ctrl implements ActionListener, MouseListener {
	ChatRoomList_Panel cp = null;

	public ChatRoomList_Ctrl(ChatRoomList_Panel cp) {
		this.cp = cp;
	}
	
	@Override
	public void mouseClicked(MouseEvent me) {
		 if (me.getClickCount() == 2) {
			 //몇번째 row인지 번호찾기
			 int index = cp.jtb.getSelectedRow();
			 System.out.println(index);
			 //방번호가져오기
			 int    room_num  = Integer.parseInt(cp.dtm.getValueAt(index, 0).toString());
			 String room_name = cp.dtm.getValueAt(index, 1).toString();
			 //방이 켜져있는지 안켜져있는지
			 int room_state = 0;
			 for(int i=0;i<cp.tc.clientRoom.size();i++) {
				 if(cp.tc.clientRoom.get(i).room_num==room_num) {
					 JOptionPane.showMessageDialog(null, "채팅창이 이미 켜져있습니다!", "채팅창", JOptionPane.INFORMATION_MESSAGE);
					 room_state = 1;
					 break;
				 }
			 }
			 //방이 꺼져있다면 켜주기
			 if(room_state==0) {
				 try {
					 cp.tc.oos.writeObject(Protocol.MYROOM_IN
									   +Protocol.seperator+room_num // room_no를 넣어둬야됨.
									   +Protocol.seperator+room_name	   
							 			);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			 }
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	

////// End of initialize////// End of initialize////// End of initialize////// End of initialize////// End of initialize
} 
///End of This Class0 ///End of This Class ///End of This Class
