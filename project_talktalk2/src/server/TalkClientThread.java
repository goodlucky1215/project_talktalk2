package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import com.vo.ConversationVO;

import View_modify.ChatDialog_View;

public class TalkClientThread extends Thread{
	TalkClient tc = null;	
	login2 login2 = null;
	///이건 클라이언트
	public TalkClientThread(TalkClient tc) {
		this.tc = tc;
	}
	@Override
	public void run() {
		////// ois로 메세지를 전달 받으면 담을 통
		String msg = null;
		List<String> list = null;
		List<Map<Object,String>> listMap = null;
		List<Map<Object,String>> converlistMap = null;
		Map<Object,String> map = null;
		List<ConversationVO> csvList = null; //대화 내용 담을 통
		boolean isStop = false;
		////// 클라이언트는 여기서 기다리다가, 이제 값이 들어올때마다 들어간다.(화면이 꺼지는 그 순간까지~!)
		while(!isStop) {
			//////TalkServerThread를 통해서 값을 받음.
			try {
				msg = (String)tc.ois.readObject();
				System.out.println(msg);
				////// 값을 쪼갤 StringTokenizer를 선언함.
				StringTokenizer st = null;
				////// 프로토콜을 선언
				int protocol = 0;
				if(msg!=null) {
					////// Protocol.seperator기준으로 문자를 쪼갠다.
					st = new StringTokenizer(msg,Protocol.seperator);
					////// 전부 String으로 되어 있으므로 처음 받아오는 프로토콜 부분은 숫자로 바꿔 준다음에 넣어준다. 
					protocol = Integer.parseInt(st.nextToken());
				}
				switch(protocol) {
					////// 맞는 프로토콜에 따라서 넣어준다.  
					//로그인 프로토콜
					case Protocol.LOGIN :{
						int    loginTrueFalse = Integer.parseInt(st.nextToken());
						String loginMsg       = st.nextToken();
						tc.login_view.loginSuccess(loginTrueFalse,loginMsg);
					}break;
					////// 회원가입 프로토콜  
					case Protocol.SIGNUP :{
						int    signTrueFalse = Integer.parseInt(st.nextToken());
						String signMsg       = st.nextToken();
						tc.login_view.signupSuccess(signTrueFalse,signMsg);
					}break;
					///// 나를 제외한 사원들 정보 보여주기
					case Protocol.MEMLIST :{
						listMap= (List)tc.ois.readObject();
						for(Map map1:listMap) {
							Vector onerow = new Vector();
							onerow.add(map1.get("p_dept_name"));
							onerow.add(map1.get("p_no"));
							onerow.add(map1.get("p_mem_name"));
							onerow.add(map1.get("p_mem_state"));
							tc.menuListView.mp.dtm.addRow(onerow);
						}
						System.out.println("------사원 목록------");
					}break;
					///// 내가 속한 방 리스트 다 받아서 뷰에다가 보여주기
					case Protocol.ROOMLIST :{
						//****여기서 중요 나중에 방 넣어줄때 key도 숨김처리해서 같이 넣어줘야한다.
						//그래야 나중에 방에 들어갈때 몇 번 방인지를 내가 찾을 수 있기 때문이다.
						listMap= (List)tc.ois.readObject();
						for(Map map1:listMap) {
							Vector onerow = new Vector();
							onerow.add(map1.get("room_no"));
							onerow.add(map1.get("room_name"));
							tc.menuListView.cp.dtm.addRow(onerow);
						}
						System.out.println("------방 리스트-----");
					}break;
					///// 내가 속하지 않은 그룹 방 리스트 다 받아서 뷰에다가 보여주기
					case Protocol.GROUPLIST :{
						//****여기서 중요 나중에 방 넣어줄때 key도 숨김처리해서 같이 넣어줘야한다.
						//그래야 나중에 방에 들어갈때 몇 번 방인지를 내가 찾을 수 있기 때문이다.
						listMap= (List)tc.ois.readObject();
						for(Map map1:listMap) {
							Vector onerow = new Vector();
							onerow.add(map1.get("room_no"));
							onerow.add(map1.get("room_name"));
							tc.menuListView.gp.group_dtm.addRow(onerow);
						}
						System.out.println("-------내가 속하지 않은 그룹방 리스트------");
					}break;
					/////나의 방 목록에 추가하거나 없애기
					case Protocol.ROOM_CREATE :{
						String room_GI       = st.nextToken();
						int    room_GI_num   = Integer.parseInt(st.nextToken()); 
						String room_GI_title = st.nextToken(); 
						//방 목록에 추가해주기
						if("ADD".equals(room_GI)){							
							Vector onerow = new Vector();
							onerow.add(room_GI_num);
							onerow.add(room_GI_title);
							tc.menuListView.cp.dtm.addRow(onerow);
							System.out.println("방 리스트에 생성시켜주기");
						}
						else if("DEL".equals(room_GI)){							
							System.out.println(room_GI_num+" ");
							System.out.println("방 리스트에서 삭제 시켜주기");
							for(int i=0;i<tc.menuListView.cp.dtm.getRowCount();i++) {
								System.out.println(tc.menuListView.cp.dtm.getValueAt(i, 0).toString());
								
								if(Integer.parseInt(tc.menuListView.cp.dtm.getValueAt(i, 0).toString())==room_GI_num) {
									tc.menuListView.cp.dtm.removeRow(i);
									break;
								}
							}
						}

					}break;
					/////그룹방 목록에 추가하거나 없애기
					case Protocol.GROUP_LIST :{
						String room_GI       = st.nextToken();
						int    room_GI_num   = Integer.parseInt(st.nextToken()); 
						String room_GI_title = null; 
						while(st.hasMoreTokens()) {
							room_GI_title = st.nextToken();
						}
						//만약 그룹이라면 그 그룹방에 초대받지 못한 사람은 없는 방에 생성해주기
						if("ADD".equals(room_GI)) {
							Vector onerow = new Vector();
							onerow.add(room_GI_num);
							onerow.add(room_GI_title);
							tc.menuListView.gp.group_dtm.addRow(onerow);
							System.out.println("그룹방 리스트에 생성시켜주기");				
						}
						else if("DEL".equals(room_GI)){
							for(int i=0;i<tc.menuListView.gp.group_dtm.getRowCount();i++) {
								if(Integer.parseInt(tc.menuListView.gp.group_dtm.getValueAt(i, 0).toString())==room_GI_num) {
									System.out.println("dfs");
									tc.menuListView.gp.group_dtm.removeRow(i);
									break;
								}
							}
							System.out.println("그룹방에서 삭제해주기");
						}
						
					}break;
					///// 방에 입장하기
					case Protocol.MYROOM_IN :{
						//이걸 해서 채팅방 창 다 켜줘야한다.
						int room_GI_num =  Integer.parseInt(st.nextToken()); 
						String room_GI_title = st.nextToken();
						int room_state= 0;
						while(st.hasMoreTokens()) {
							st.nextToken();
							for(int i=0;i<tc.clientRoom.size();i++) {
								 if(tc.clientRoom.get(i).room_num==room_GI_num) {
									 JOptionPane.showMessageDialog(null, "채팅창이 이미 켜져있습니다!", "채팅창", JOptionPane.INFORMATION_MESSAGE);
									 room_state = 1;
									 break;
								 }
							 }
						}
						listMap       = (List)tc.ois.readObject();
						converlistMap = (List)tc.ois.readObject();
						if(room_state==1) break; //이미 방이 켜져있다.
						//여기에 나중에 방 창 켜지는 것
						ChatDialog_View cdView = new ChatDialog_View();
						cdView.room_num  = room_GI_num;
						cdView.room_name = room_GI_title;
						cdView.setTitle(room_GI_title);
						cdView.tc = tc;
						tc.clientRoom.add(cdView);
						///방 안 사람들 뷰에 보이도록
						System.out.println("------------방 안 사람들-------------");
						for(Map member:listMap) {
							System.out.println(member.get("p_mem_name")+" "+member.get("p_no"));
							Vector onerow = new Vector();
							onerow.add(member.get("p_no"));
							onerow.add(member.get("p_mem_name"));
							cdView.dtm.addRow(onerow);
						}
						
						////방 대화 다 보이도록
						System.out.println("--------------대화 내용-----------");
						if(converlistMap!=null) {
							for(Map con:converlistMap) {
								System.out.println("["+con.get("mem_name")+"]"+con.get("conver_text"));
								cdView.showChat_jta.append("["+con.get("mem_name")+"]"+con.get("conver_text")+"\n");
							}
						}
					}break;
					///// 내가 속하지 않은 그룹 방에 입장하기
					case Protocol.GROUPROOM_IN :{
						//이걸 해서 채팅방 창 다 켜줘야한다.
						int room_GI_num =  Integer.parseInt(st.nextToken()); 
						int me_num      =  Integer.parseInt(st.nextToken()); 
						String me_name  =  st.nextToken();
						 for(int i=0;i<tc.clientRoom.size();i++) {
							 if(tc.clientRoom.get(i).room_num==room_GI_num) {
								 Vector onerow = new Vector();
								 onerow.add(me_num);
								 onerow.add(me_name);
								 tc.clientRoom.get(i).dtm.addRow(onerow);
								 tc.clientRoom.get(i).showChat_jta.append("["+me_name+"]"+"님이 입장하셨습니다. \n");
								 break;
							 }
						 }
					}break;
					///// 내가 속하지 않은 그룹 방에 입장하기
					case Protocol.CHAT_IN:{
						//채팅창 목록에 추가할건지 삭제할건지
						String room_GI       = st.nextToken();
						int room_GI_num =  Integer.parseInt(st.nextToken()); 
						int me_num           = Integer.parseInt(st.nextToken()); 
						String me_name      = st.nextToken();
						//////방 안 사람들 뷰에 보이도록
						if("ADD".equals(room_GI)) {
							for(int i=0;i<tc.clientRoom.size();i++) {
								 if(tc.clientRoom.get(i).room_num==room_GI_num) {
									 Vector onerow = new Vector();
									 onerow.add(me_num);
									 onerow.add(me_name);
									 tc.clientRoom.get(i).dtm.addRow(onerow);
									 tc.clientRoom.get(i).showChat_jta.append("["+me_name+"]"+"님이 입장하셨습니다. \n");
									 break;
								 }
							 }
							System.out.println("그룹채팅 리스트에 생성시켜주기");				
						}
						else if("DEL".equals(room_GI)){							
							for(int i=0;i<tc.clientRoom.size();i++) {
								if(tc.clientRoom.get(i).room_num==room_GI_num) {
									for(int j=0;j<tc.clientRoom.get(i).dtm.getRowCount();j++) {
										if(Integer.parseInt(tc.clientRoom.get(i).dtm.getValueAt(j, 0).toString())==me_num) {
											tc.clientRoom.get(i).dtm.removeRow(j);
											break;
										}
									}
									tc.clientRoom.get(i).showChat_jta.append("["+me_name+"]"+"님이 나가셨습니다. \n");
									break;
								}
							}
							System.out.println("그룹채팅 리스트에 삭제해주기");
						}
					}break;
					/////새로운 사람이 방에 들어올때
					/////메세지 전송할 때
					case Protocol.MESSAGE :{
					    /////어느 방에서 말하는지
						int    conver_room_num = Integer.parseInt(st.nextToken());
						/////말하는 사람 이름은 뭔지
						String conver_name     = st.nextToken();
						/////뭐라고 말하는지
						String conversation    = st.nextToken();
						System.out.println("["+conver_name+"] "+conversation);
						 for(int i=0;i<tc.clientRoom.size();i++) {
							 if(tc.clientRoom.get(i).room_num==conver_room_num) {
								 tc.clientRoom.get(i).showChat_jta.append("["+conver_name+"] "+conversation+"\n");
								 break;
							 }
						 }
					}break;
					///// 개인 상세 정보보기 
					case Protocol.MY_INFO :{
						System.out.println("내 정보 보기");
						String mem_name  = st.nextToken();
						String mem_dept  = st.nextToken();
						String mem_level = st.nextToken();
						//System.out.println("[이름]"+st.nextToken());
						//System.out.println("[부서]"+st.nextToken());
						//System.out.println("[직급]"+st.nextToken());
						System.out.println(tc.menuListView.cbp.cscp.config_Status_dtm.getValueAt(0, 0));
						System.out.println(tc.menuListView.cbp.cscp.config_Status_dtm.getValueAt(1, 0));
						System.out.println(tc.menuListView.cbp.cscp.config_Status_dtm.getValueAt(2, 0));
						tc.menuListView.cbp.cscp.config_Status_dtm.setValueAt(mem_name, 0, 1);
						tc.menuListView.cbp.cscp.config_Status_dtm.setValueAt(mem_dept, 1, 1);
						tc.menuListView.cbp.cscp.config_Status_dtm.setValueAt(mem_level, 2, 1);
					}break;
					///// 나의 상태 변경
					case Protocol.MY_STATE :{
						String mem_state = st.nextToken();
						String mem_no = st.nextToken();
						System.out.println(tc.menuListView.mp.dtm.getValueAt(1,0));
						System.out.println(tc.menuListView.mp.dtm.getValueAt(1,1));
						System.out.println(tc.menuListView.mp.dtm.getValueAt(1,2));
						System.out.println(tc.menuListView.mp.dtm.getValueAt(1,3));
						for(int i=0;i<tc.menuListView.mp.dtm.getRowCount();i++) {
							if(mem_no.equals(tc.menuListView.mp.dtm.getValueAt(i,1).toString())){
								tc.menuListView.mp.dtm.setValueAt(mem_state, i, 3);
								break;
							}
						}
					}break;
					///// 방 삭제
					case Protocol.ROOM_DEL:{
						
					}break;
					///// 승인된 사원 추가하거나 또는 업데이트, 삭제하기
					case Protocol.MANAGER_APPROVE_DEL :{
						int    client_mem_no   = Integer.parseInt(st.nextToken());
						String client_mem_name = st.nextToken();
						String client_mem_info = st.nextToken();
						String room_GI         = st.nextToken();
						//멤버 목록에 추가
						if("ADD".equals(room_GI)){							
							System.out.println(client_mem_no+" "+client_mem_name);
							System.out.println("멤버 리스트에 생성시켜주기");
						}
						else if("UP".equals(room_GI)){							
							System.out.println(client_mem_no+" "+client_mem_name);
							System.out.println("멤버 정보 업데이트");
						}
						//멤버 목록에 삭제
						else if("DEL".equals(room_GI)){							
							System.out.println(client_mem_name+" ");
							System.out.println("멤버 리스트에서 삭제 시켜주기");
						}
					}break;
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
