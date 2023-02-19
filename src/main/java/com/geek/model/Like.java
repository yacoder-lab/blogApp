package com.geek.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "likes")
public class Like {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	
	private boolean like_id;

	@ManyToOne
	private Register register;
	
	
	@ManyToOne
	private Posts post;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public boolean isLike() {
		return like_id;
	}


	public void setLike(boolean like) {
		this.like_id = like;
	}


	public Register getRegister() {
		return register;
	}


	public void setRegister(Register register) {
		this.register = register;
	}


	public Posts getPost() {
		return post;
	}


	public void setPost(Posts post) {
		this.post = post;
	}


	public Like(int id, boolean like, Register register, Posts post) {
		super();
		this.id = id;
		this.like_id = like;
		this.register = register;
		this.post = post;
	}


	public Like() {
		super();
		// TODO Auto-generated constructor stub
	}





	
	
	
	
	
	
	
	
}
