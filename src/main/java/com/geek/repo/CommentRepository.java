package com.geek.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geek.model.Comment;
import com.geek.model.Posts;
import com.geek.model.Register;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	

	public List<Comment> findByPostAndRegister(Posts post,Register register);
	
	public List<Comment> findByPost(Posts post);
	
}
