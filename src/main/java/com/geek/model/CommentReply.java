package com.geek.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CommentReply {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String comentsreturns;

	@ManyToOne
	private Comment comment;
	
	@ManyToOne
	private Posts posts;
	
	
	@ManyToOne
	private Register register;


	public CommentReply(int id, String comentsreturns, Comment comment, Posts posts, Register register) {
		super();
		this.id = id;
		this.comentsreturns = comentsreturns;
		this.comment = comment;
		this.posts = posts;
		this.register = register;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getComentsreturns() {
		return comentsreturns;
	}


	public void setComentsreturns(String comentsreturns) {
		this.comentsreturns = comentsreturns;
	}


	public Comment getComment() {
		return comment;
	}


	public void setComment(Comment comment) {
		this.comment = comment;
	}


	public Posts getPosts() {
		return posts;
	}


	public void setPosts(Posts posts) {
		this.posts = posts;
	}


	public Register getRegister() {
		return register;
	}


	public void setRegister(Register register) {
		this.register = register;
	}


	@Override
	public String toString() {
		return "CommentReply [id=" + id + ", comentsreturns=" + comentsreturns + ", comment=" + comment + ", posts="
				+ posts + ", register=" + register + "]";
	}
	
	
	
	
	
	
}
