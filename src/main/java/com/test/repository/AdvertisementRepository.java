package com.test.repository;

import java.util.List;
import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.test.model.Advertisement;
import com.test.model.Category;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long>{
	
	@Query(nativeQuery = true, value = "select * from Advertisement c where c.user_id = :user_id")
	public List<Advertisement> findAdvertisementByUserId(@Param("user_id")Long user_id);
	
	
	@Query(nativeQuery = true, value = "select * from Advertisement c where c.category_id = :category_id")
	public List<Advertisement> findAdvertisementByCategoryId(@Param("category_id")Long category_id);
	
	@Query(nativeQuery = true, value = "select * from Advertisement c where c.location = :location")
	public List<Advertisement> findAdvertisementByLocation(@Param("location")String location);
	
	@Transactional
	@Modifying
	@Query(value = "delete from Advertisement c where c.user_id = :user_id",nativeQuery = true )
	public void deleteAdvertisementByUserId(@Param("user_id")Long user_id);


	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "delete from Advertisement c where c.category_id = :category_id")
	public void deleteAdvertisementByCategoryId(@Param("category_id")Long category_id);
	
}
