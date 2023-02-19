package com.geek.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Follow {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private boolean follow;
	
	
	@ManyToOne
	private Register register;
	
	
	private String followname;
	





	public Follow(int id, boolean follow, Register register, String followname) {
		super();
		this.id = id;
		this.follow = follow;
		this.register = register;
		this.followname = followname;
	}


	public Follow() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public boolean isFollow() {
		return follow;
	}


	public void setFollow(boolean follow) {
		this.follow = follow;
	}


	public Register getRegister() {
		return register;
	}


	public void setRegister(Register register) {
		this.register = register;
	}

	

	public String getFollowname() {
		return followname;
	}


	public void setFollowname(String followname) {
		this.followname = followname;
	}


	@Override
	public String toString() {
		return "Follow [id=" + id + ", follow=" + follow + ", register=" + register + "]";
	}
	
	
	
	
	
	
}
