package com.basf.challenge.repositories;

import com.basf.challenge.entities.Patent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatentRepository extends MongoRepository<Patent, String> {
}
