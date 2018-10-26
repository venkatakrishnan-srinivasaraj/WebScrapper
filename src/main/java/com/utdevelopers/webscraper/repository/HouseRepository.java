package com.utdevelopers.webscraper.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import com.utdevelopers.webscraper.model.House;

@Service
public interface HouseRepository extends MongoRepository<House, String> {

}
