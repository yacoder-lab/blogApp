            package com.geek.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
		
	@OneToMany(mappedBy = "comment")
	private List<CommentReply> comments=new LinkedList<>();

	
	private String comment;
	
	@ManyToOne
	private Posts post;
	
	@ManyToOne
	private Register register;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Posts getPost() {
		return post;
	}

	public void setPost(Posts post) {
		this.post = post;
	}

	

	public Register getRegister() {
		return register;
	}

	public void setRegister(Register register) {
		this.register = register;
	}

	
	public Comment(int id, String comment, Posts post, Register register) {
		super();
		this.id = id;
		this.comment = comment;
		this.post = post;
		this.register = register;
	}

	public Comment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", comment=" + comment + ", post=" + post + ", register=" + register + "]";
	}
	
	
	
	
}
