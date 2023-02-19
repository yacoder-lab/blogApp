package com.geek.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geek.model.Like;
import com.geek.model.Posts;
import com.geek.model.Register;

public interface LikeRepository extends JpaRepository<Like, Integer> {
	

	public List<Like> findByRegisterAndPost(Register register,Posts post);
	
	public List<Like> findByPost(Posts posts);
	
}
