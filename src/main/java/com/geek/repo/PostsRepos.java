package com.geek.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geek.model.Posts;
import com.geek.model.Register;

public interface PostsRepos extends JpaRepository<Posts, Integer> {

	public List<Posts> findByRegister(Register register);
	
	
	
}
