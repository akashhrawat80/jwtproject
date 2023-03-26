package com.jwtproject.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jwtproject.model.MetaFile;

@Repository
public interface JwtRepository extends MongoRepository<MetaFile, String> {
}
