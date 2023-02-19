package com.geek.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.geek.model.Register;

public interface RegisterRepository extends JpaRepository<Register, Integer> {
	
	
	@Query("select r from Register r where r.email=:email")
	public Register getByEmail(@Param("email") String email);
	

	
	public List<Register> findByEmailAndPassword(String email,String password);
	
}
