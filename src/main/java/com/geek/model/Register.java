package com.geek.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Register {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private int id;
	
	@NotBlank(message = "Username can not be empty")
	@Size(min = 5,max = 15,message = "Username min 5 and max 15")
	@Column(unique = true)
	private String username;
	
	
	@Column(unique = true)
	private String email;
	
	
	private String password;
	
	private String gender;
	
	private String UserRole;
	
	
	private String job=null;
	
	private String website=null;

	private String github=null;
	
	
	private String facebook=null;
	
	
	private String instagram=null;
  
	private String twitter=null;
	
	
	@OneToMany(mappedBy = "register",cascade = CascadeType.ALL)
	private List<Like> like=new LinkedList<>();
	
	@OneToMany(mappedBy = "register",cascade = CascadeType.ALL)
	private List<Follow> follows=new LinkedList<>();

	
	@OneToMany(mappedBy = "register",cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Posts> posts=new LinkedList<>();
	
	
	@OneToMany(mappedBy = "register")
	private List<Comment> comments=new LinkedList<>();
	
	@OneToMany(mappedBy = "register")
	private List<Comment> registers=new LinkedList<>();

	
	public List<Posts> getPosts() {
		return posts;
	}

	public void setPosts(List<Posts> posts) {
		this.posts = posts;
	}

	
	

	@AssertTrue(message = "Please Accept Terms And Conditions !!!")
	private boolean agreed;






	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	




	public Register(@NotNull int id,
			@NotBlank(message = "Username can not be empty") @Size(min = 5, max = 15, message = "Username min 5 and max 15") String username,
			String email, String password, String gender, String userRole, String job, String website, String github,
			String facebook, String instagram, String twitter, List<Like> like, List<Follow> follows, List<Posts> posts,
			List<Comment> comments, @AssertTrue(message = "Please Accept Terms And Conditions !!!") boolean agreed) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.gender = gender;
		UserRole = userRole;
		this.job = job;
		this.website = website;
		this.github = github;
		this.facebook = facebook;
		this.instagram = instagram;
		this.twitter = twitter;
		this.like = like;
		this.follows = follows;
		this.posts = posts;
		this.comments = comments;
		this.agreed = agreed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUserRole() {
		return UserRole;
	}

	public void setUserRole(String userRole) {
		UserRole = userRole;
	}

	public boolean isAgreed() {
		return agreed;
	}

	public void setAgreed(boolean agreed) {
		this.agreed = agreed;
	}

	
	
	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getGithub() {
		return github;
	}

	public void setGithub(String github) {
		this.github = github;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	
	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	

	public List<Like> getLike() {
		return like;
	}

	public void setLike(List<Like> like) {
		this.like = like;
	}

	public List<Follow> getFollows() {
		return follows;
	}

	public void setFollows(List<Follow> follows) {
		this.follows = follows;
	}

	


	

	

	
	
	
	
	}