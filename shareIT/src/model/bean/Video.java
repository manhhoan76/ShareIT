package model.bean;

import java.sql.Timestamp;

public class Video {
	private int id;
	private String link;
	private int cat_id;
	private int show_link;
	private String name;
	private int view;
	private Timestamp date_create;
	private int user_id;
	public Video(int id, String link, int cat_id, int show_link, String name, int view, Timestamp date_create,
			int user_id) {
		super();
		this.id = id;
		this.link = link;
		this.cat_id = cat_id;
		this.show_link = show_link;
		this.name = name;
		this.view = view;
		this.date_create = date_create;
		this.user_id = user_id;
	}
	public Video() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getCat_id() {
		return cat_id;
	}
	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}
	public int getShow_link() {
		return show_link;
	}
	public void setShow_link(int show_link) {
		this.show_link = show_link;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	public Timestamp getDate_create() {
		return date_create;
	}
	public void setDate_create(Timestamp date_create) {
		this.date_create = date_create;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
}
