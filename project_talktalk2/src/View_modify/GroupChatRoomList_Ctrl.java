package View_modify;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;

import server.Protocol;

public class GroupChatRoomList_Ctrl implements MouseListener{
	GroupChatRoomList_Panel groupChatRoomList_Panel;
	
   public GroupChatRoomList_Ctrl(GroupChatRoomList_Panel groupChatRoomList_Panel) {
	   this.groupChatRoomList_Panel= groupChatRoomList_Panel;
   }
	
@Override
public void mouseClicked(MouseEvent me) {
	System.out.println("SDfsdfsd");
	if (me.getClickCount()==2) {
		System.out.println("SDfsdfsd");
		//몇번째 row인지 번호찾기
		int index = groupChatRoomList_Panel.group_jtb.getSelectedRow();
		System.out.println(index);
		//방번호가져오기
		int    room_num  = Integer.parseInt(groupChatRoomList_Panel.group_dtm.getValueAt(index, 0).toString());
		String room_name = groupChatRoomList_Panel.group_dtm.getValueAt(index, 1).toString();
		 try {
			 groupChatRoomList_Panel.tc.oos.writeObject(Protocol.MYROOM_IN
													   +Protocol.seperator+room_num // room_no를 넣어둬야됨.
													   +Protocol.seperator+room_name	   
											 			);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		 try {
			 groupChatRoomList_Panel.tc.oos.writeObject(Protocol.GROUPROOM_IN
					 +Protocol.seperator+room_num // room_no를 넣어둬야됨.
					 +Protocol.seperator+room_name	   
					 );
		 } catch (IOException e1) {
			 e1.printStackTrace();
		 }
	}
}
@Override
public void mouseEntered(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}
@Override
public void mousePressed(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}
@Override
public void mouseReleased(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}
}