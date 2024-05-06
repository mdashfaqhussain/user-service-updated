package com.hasdedin.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hasdedin.user.entity.BudgetUser;



public interface IUserRepository  extends JpaRepository<BudgetUser, Integer> {

	Boolean existsByName(String name);
	
	Optional<BudgetUser> findByName(String name);
}
