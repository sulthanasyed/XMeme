package com.crio.starter.repository;

import com.crio.starter.data.Meme;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemeRepository extends MongoRepository<Meme , String> {
    
}
