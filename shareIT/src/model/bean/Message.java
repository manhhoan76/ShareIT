package model.bean;

import java.sql.Timestamp;

public class Message {
	private int id;
	private int id_sent;
	private int id_receive;
	private String content;
	private Timestamp date_create;
	public Message(int id, int id_sent, int id_receive, String content, Timestamp date_create) {
		super();
		this.id = id;
		this.id_sent = id_sent;
		this.id_receive = id_receive;
		this.content = content;
		this.date_create = date_create;
	}
	public Message() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_sent() {
		return id_sent;
	}
	public void setId_sent(int id_sent) {
		this.id_sent = id_sent;
	}
	public int getId_receive() {
		return id_receive;
	}
	public void setId_receive(int id_receive) {
		this.id_receive = id_receive;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getDate_create() {
		return date_create;
	}
	public void setDate_create(Timestamp date_create) {
		this.date_create = date_create;
	}
	
}
