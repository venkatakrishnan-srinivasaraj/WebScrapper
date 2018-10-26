package com.utdevelopers.webscraper.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.utdevelopers.webscraper.model.Zipcode;

@Service
public interface ZipcodeRepository extends MongoRepository<Zipcode, String> {

}
