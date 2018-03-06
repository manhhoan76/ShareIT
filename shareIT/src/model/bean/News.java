package model.bean;

import java.sql.Timestamp;

public class News {

	 private int id;
	 private String name;
	 private String preview;
	 private String detail;
	 private Timestamp date_create;
	 private String picture;
	 private int cat_id;
	 private String cat_name;
	 private int is_slide;
	 private int view;
	 private int user_id;
	 private String user_name;
	 private int cat_parent;
	public News(int id, String name, String preview, String detail, Timestamp date_create, String picture, int cat_id,
			String cat_name, int is_slide, int view, int user_id, String user_name, int cat_parent) {
		super();
		this.id = id;
		this.name = name;
		this.preview = preview;
		this.detail = detail;
		this.date_create = date_create;
		this.picture = picture;
		this.cat_id = cat_id;
		this.cat_name = cat_name;
		this.is_slide = is_slide;
		this.view = view;
		this.user_id = user_id;
		this.user_name = user_name;
		this.cat_parent = cat_parent;
	}
	public News() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Timestamp getDate_create() {
		return date_create;
	}
	public void setDate_create(Timestamp date_create) {
		this.date_create = date_create;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getCat_id() {
		return cat_id;
	}
	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}
	public String getCat_name() {
		return cat_name;
	}
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	public int getIs_slide() {
		return is_slide;
	}
	public void setIs_slide(int is_slide) {
		this.is_slide = is_slide;
	}
	public int getView() {
		return view;
	}
	public void setView(int view) {
		this.view = view;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getCat_parent() {
		return cat_parent;
	}
	public void setCat_parent(int cat_parent) {
		this.cat_parent = cat_parent;
	}
	
}
