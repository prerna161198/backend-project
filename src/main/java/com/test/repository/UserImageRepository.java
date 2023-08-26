package com.test.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.model.Advertisement;
import com.test.model.UserImage;



@Repository
public interface UserImageRepository extends JpaRepository<UserImage, Long> {
	
	@Query(nativeQuery = true, value = "select * from user_image c where c.user_id = :user_id")
	public Optional<UserImage> findImageByUserId(@Param("user_id")Long user_id);

}
