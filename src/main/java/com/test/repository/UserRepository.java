package com.test.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.model.Advertisement;
import com.test.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query(nativeQuery = true, value = "select * from User c where c.email = :email")
	public Optional<User> findUserByEmail(@Param("email")String email);

	@Query(nativeQuery = true, value = "select * from User c where c.email = :email")
	public Optional<User> findUserProfile(@Param("email")String email);
	
	@Query(nativeQuery = true, value = "select * from User c where c.password = :password")
	public Optional<User> findUserByPassword(@Param("password")String password);
}
