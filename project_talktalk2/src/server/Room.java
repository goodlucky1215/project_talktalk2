package server;

import java.util.List;
import java.util.Vector;

public class Room {
	  List<TalkServerThread>  userList  = new Vector<TalkServerThread>();
	  List<Integer>  mem_no    = new Vector<Integer>();
	  //List<String>   mem_name  = new Vector<String>();
	  String title = null;//방 이름
	  private int room_num = 0;//고유의 방 번호
	  public Room() {}
	  public String getTitle() {
	  	return title;
	  }
	  public void setTitle(String title) {
	  	this.title = title;
	  }
	  public int getRoom_num() {
		  return room_num;
	  }
	  public void setRoom_num(int room_num) {
		  this.room_num = room_num;
	  }

}