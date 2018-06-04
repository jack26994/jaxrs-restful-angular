package com.login.ang.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "model")
public class Model {
	private int id;
	private String email;
	private String password;
	private String name;
	
	public Model(int id, String email, String password, String name) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
	}
	
	@XmlElement
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@XmlElement
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@XmlElement
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@XmlElement
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Model [id=" + this.id + ", name=" + this.name + "]";
	}
}
