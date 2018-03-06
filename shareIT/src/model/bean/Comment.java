package model.bean;

import java.sql.Timestamp;

public class Comment {
	private int id;
	private String content;
	private Timestamp date_create;
	private int parent_id;
	private int news_id;
	private String name_news;
	private String name;
	private String email;
	private String website;
	private int active;
	public Comment(int id, String content, Timestamp date_create, int parent_id, int news_id, String name_news,
			String name, String email, String website, int active) {
		super();
		this.id = id;
		this.content = content;
		this.date_create = date_create;
		this.parent_id = parent_id;
		this.news_id = news_id;
		this.name_news = name_news;
		this.name = name;
		this.email = email;
		this.website = website;
		this.active = active;
	}
	public Comment() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public int getNews_id() {
		return news_id;
	}
	public void setNews_id(int news_id) {
		this.news_id = news_id;
	}
	public String getName_news() {
		return name_news;
	}
	public void setName_news(String name_news) {
		this.name_news = name_news;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	
	
}
