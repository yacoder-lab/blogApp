package com.geek.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Posts {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
 

	
	@NotBlank
	@NotNull
	@Size(min = 10)
	private String title;
	
	@NotBlank
	@NotNull
	@Size(min = 20)
	private String content;
		
	
	private boolean publish=false;
	
	
	Date date=new Date();
	
	
	@OneToMany(mappedBy = "post")
	List<Like> likes= new LinkedList<>();

	
	@OneToMany(mappedBy = "post")
	List<Comment> comments= new LinkedList<>();
	
	
	@ManyToOne
	@JsonBackReference
	private Register register;

	
	
	private int likep;
	


	@OneToMany(mappedBy = "post")
	private List<Comment> postss=new LinkedList<>();

	









	public Posts(int id, @NotBlank @NotNull @Size(min = 10) String title,
			@NotBlank @NotNull @Size(min = 20) String content, boolean publish, Date date, List<Like> likes,
			List<Comment> comments, Register register, int likep) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.publish = publish;
		this.date = date;
		this.likes = likes;
		this.comments = comments;
		this.register = register;
		this.likep = likep;
	}








	public int getId() {
		return id;
	}








	public void setId(int id) {
		this.id = id;
	}








	public String getTitle() {
		return title;
	}








	public void setTitle(String title) {
		this.title = title;
	}








	public String getContent() {
		return content;
	}








	public void setContent(String content) {
		this.content = content;
	}








	public boolean isPublish() {
		return publish;
	}








	public void setPublish(boolean publish) {
		this.publish = publish;
	}








	public Date getDate() {
		return date;
	}








	public void setDate(Date date) {
		this.date = date;
	}








	public List<Like> getLikes() {
		return likes;
	}








	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}








	public List<Comment> getComments() {
		return comments;
	}








	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}








	public Register getRegister() {
		return register;
	}








	public void setRegister(Register register) {
		this.register = register;
	}








	@Override
	public String toString() {
		return "Posts [id=" + id + ", title=" + title + ", content=" + content + ", publish=" + publish + ", date="
				+ date + ", likes=" + likes + ", comments=" + comments + ", register=" + register + "]";
	}








	public Posts() {
		// TODO Auto-generated constructor stub
	}








	public int getLikep() {
		return likep;
	}








	public void setLikep(int likep) {
		this.likep = likep;
	}



	
	
	


	
	
	
	
	
	
	
}