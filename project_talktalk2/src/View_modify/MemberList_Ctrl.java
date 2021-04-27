package View_modify;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import server.Protocol;

public class MemberList_Ctrl implements MouseListener, ActionListener{
	MemberList_Panel memberList_Panel; //  멤버리스트 패널 선언 
	public MemberList_Ctrl(MemberList_Panel memberList_Panel){// 생성자
		this.memberList_Panel= memberList_Panel;  // 멤버리스트 패널 주소번지 동기화
	}

	@Override
	public void mouseClicked(MouseEvent e) {
//		//선택된 로우 클릭 getSelectionRows API를 보면 int 타입인걸 알 수 있음. 사원번호를 떙기는거 어떰?
		//int clients[] = memberList_Panel.jtb.getSelectedRows();   //선택된 로우들을 임의 변수로 지정 
		//if(clients.length==0) {
		//	JOptionPane.showMessageDialog(null, "", "채팅 방 생성", JOptionPane.INFORMATION_MESSAGE);
		//}
		//else if (clients.length==1) {											// 한번 클릭하면 객체 클릭 되는지 확인
		//	System.out.println("선택된 로우 출력 : " + aaa);
		//}  // End of 한번클릭 if 문
		/////////////////////
		//
		//else if (clients.length>=2) {							
		//	System.out.println("더블클릭 선택된 로우 출력 : " + aaa);  // 더블클릭한 객체 클릭 된건지 확인
//		//요 아래에 파라미터 값으로 선택된 사람을 받아오게끔 해줘야함.
//		//해당 타입이 VO가 될지 뭐가 될진 정해야함.
		//	ChatDialog_View chatDialog_View = new ChatDialog_View(); // 채팅창 생성
		//	} // End of 더블클릭 감지 if문
		} //마우스 클릭 이벤트 끝///
		

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	} //마우스 넣는 액션

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	} //마우스 배는 액션

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}  // 좌우클릭 마우스클릭 누르기 액션

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}  // 몰라 

	@Override
	public void actionPerformed(ActionEvent ae) { // 액션 퍼폼드
			Object aobj = ae.getSource();
			JFrame f = new JFrame();  
		if (memberList_Panel.search_jbtn==aobj) { //멤버 검색입력창
			String xxx = memberList_Panel.memeberListSearch_jtf_MemberListPanel.getText(); //입력창에 입력된 값 가져오기
		}  
		/// if문 마감
		else if (memberList_Panel.CreateChat_jbtn==aobj) {// 그룹채팅 생성버튼
//			선택된 로우 클릭 getSelectionRows API를 보면 int 타입인걸 알 수 있음. 사원번호를 떙기는거 어떰?
			int clients[] = memberList_Panel.jtb.getSelectedRows();   //선택된 로우들을 임의 변수로 지정 
			if(clients.length==0) {
				JOptionPane.showMessageDialog(null, "방을 선택해주세요", "채팅 방 생성", JOptionPane.INFORMATION_MESSAGE);
			}
			else if (clients.length==1) {			
				System.out.println("한명누름");
				String name=JOptionPane.showInputDialog(f,"방 제목을 입력하세요");    
				System.out.println(memberList_Panel.dtm.getValueAt(clients[0],1));
				System.out.println(memberList_Panel.dtm.getValueAt(clients[0],1));
				if(name.length()==0) {
					JOptionPane.showMessageDialog(null, "방 이름을 만들어주세요!", "방 만들기", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					try {
						memberList_Panel.tc.oos.writeObject(Protocol.ROOM_CREATE
								       +Protocol.seperator+"individual" //개인톡 생성
								       +Protocol.seperator+name //상대방 이름으로 방이름 붙이기
								       );
						//방 사람들 들어있는 목록- 번호 -자기 자신은 넣지 말자 어차피 거기서 처리하면 되니깐
						List<Integer> member_nos = new ArrayList<>();
						member_nos.add(Integer.parseInt((memberList_Panel.dtm.getValueAt(clients[0],1).toString()))); //상대 번호
						memberList_Panel.tc.oos.writeObject(member_nos);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}  // End of 한번클릭 if 문
			///////////////////
			else if (clients.length>=2) {
				String name=JOptionPane.showInputDialog(f,"방 제목을 입력하세요");      
				System.out.println("여러명 누름");
				if(name.length()==0) {
					JOptionPane.showMessageDialog(null, "방 이름을 만들어주세요!", "방 만들기", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					//단톡방 생성
					try {
						memberList_Panel.tc.oos.writeObject(Protocol.ROOM_CREATE
									+Protocol.seperator+"group"//단톡방 생성
									+Protocol.seperator+name //그룹방이름
									);
						//방 사람들 들어있는 목록- 번호
						List<Integer> member_nos = new ArrayList<>();
						for(int i=0;i<clients.length;i++) {
							member_nos.add(Integer.parseInt((memberList_Panel.dtm.getValueAt(clients[i],1).toString())));
						}
						memberList_Panel.tc.oos.writeObject(member_nos);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}
	
}
