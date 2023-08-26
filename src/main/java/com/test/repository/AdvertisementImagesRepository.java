package com.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.model.Advertisement;
import com.test.model.AdvertisementImages;


@Repository
public interface AdvertisementImagesRepository extends JpaRepository<AdvertisementImages, Long>{

	@Query(nativeQuery = true, value = "select * from Advertisement_Images c where c.advertisement_id = :advertisement_id")
	public List<AdvertisementImages> findAdvImgByAdvId(@Param("advertisement_id")Long user_id);
	
}
