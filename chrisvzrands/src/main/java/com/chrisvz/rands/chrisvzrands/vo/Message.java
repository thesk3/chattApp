package com.chrisvz.rands.chrisvzrands.vo;

public class Message {

	private long id;
	private String text;
	private int from;
	private int to;
	private boolean isUserMsg;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isUserMsg() {
		return isUserMsg;
	}
	public void setUserMsg(boolean isUserMsg) {
		this.isUserMsg = isUserMsg;
	}
	public Message(long id, String text, boolean isUserMsg) {
		super();
		this.id = id;
		this.text = text;
		this.isUserMsg = isUserMsg;
	}
	public Message() {
		// TODO Auto-generated constructor stub
	}
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public int getTo() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", text=" + text + ", from=" + from + ", to=" + to + ", isUserMsg=" + isUserMsg
				+ "]";
	}
	
}
