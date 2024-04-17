package com.first.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.first.entity.User;

   public interface UserRepository extends JpaRepository<User,Long>   {                   
	   Optional<User> findById(Long userId);
	@SuppressWarnings("unchecked")
	User save(User user); 

}
