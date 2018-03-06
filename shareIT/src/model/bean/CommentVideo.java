package model.bean;

import java.sql.Timestamp;

public class CommentVideo {
	private int id;
	private String content;
	private Timestamp date_create;
	private int parent_id;
	private int video_id;
	private String name_video;
	private String name;
	private String email;
	private String website;
	private int active;
	public CommentVideo(int id, String content, Timestamp date_create, int parent_id, int video_id, String name_video,
			String name, String email, String website, int active) {
		super();
		this.id = id;
		this.content = content;
		this.date_create = date_create;
		this.parent_id = parent_id;
		this.video_id = video_id;
		this.name_video = name_video;
		this.name = name;
		this.email = email;
		this.website = website;
		this.active = active;
	}
	public CommentVideo() {
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
	public int getVideo_id() {
		return video_id;
	}
	public void setVideo_id(int video_id) {
		this.video_id = video_id;
	}
	public String getName_video() {
		return name_video;
	}
	public void setName_video(String name_video) {
		this.name_video = name_video;
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
