package com.vo;

public class ConversationVO {
	private int conver_no = 0;
	private int mem_no = 0;
	private int room_no = 0;
	private String conver_text = "";
	private String conver_time = "";
	public int getConver_no() {
		return conver_no;
	}
	public void setConver_no(int conver_no) {
		this.conver_no = conver_no;
	}
	public int getMem_no() {
		return mem_no;
	}
	public void setMem_no(int mem_no) {
		this.mem_no = mem_no;
	}
	public int getRoom_no() {
		return room_no;
	}
	public void setRoom_no(int room_no) {
		this.room_no = room_no;
	}
	public String getConver_text() {
		return conver_text;
	}
	public void setConver_text(String conver_text) {
		this.conver_text = conver_text;
	}
	public String getConver_time() {
		return conver_time;
	}
	public void setConver_time(String conver_time) {
		this.conver_time = conver_time;
	}
}
