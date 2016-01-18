package com.memories;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by rohitgirme on 1/16/16.
 */
public interface MemoriesRepository extends MongoRepository<Memory, String> {
}
