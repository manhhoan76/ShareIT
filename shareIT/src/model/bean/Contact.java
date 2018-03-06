package model.bean;

public class Contact {
 private int id;
 private String name;
 private String email;
 private String web;
 private String content;
public Contact(int id, String name, String email, String web, String content) {
	super();
	this.id = id;
	this.name = name;
	this.email = email;
	this.web = web;
	this.content = content;
}
public Contact() {
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
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getWeb() {
	return web;
}
public void setWeb(String web) {
	this.web = web;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}

}
