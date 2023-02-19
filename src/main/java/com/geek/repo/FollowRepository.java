package com.geek.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.geek.model.Follow;
import com.geek.model.Register;

public interface FollowRepository extends JpaRepository<Follow, Integer> {
	
	public List<Follow> findByRegister(Register reg);
	
	
}
