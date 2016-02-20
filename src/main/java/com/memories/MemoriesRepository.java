package com.memories;

import com.memories.data.Memory;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by rohitgirme on 1/16/16.
 */
public interface MemoriesRepository extends MongoRepository<Memory, String>, MemoriesRepositoryCustom {
}
