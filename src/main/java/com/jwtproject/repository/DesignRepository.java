package com.jwtproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.jwtproject.model.Design;

public interface DesignRepository extends MongoRepository<Design, String> {
	 List<Design> findByTitleContaining(String title);
	 Optional<Design> findByTitle(String title);
	  @Query("{description:'?0'}")
	  Optional<Design> findDesignByDescription(String description);
}
