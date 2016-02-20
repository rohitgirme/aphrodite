package com.memories;

import com.memories.data.Memory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * Created by rohitgirme on 2/12/16.
 */
// Name of class should be same as class extending MongoRepository with 'Impl' suffix.
// This enabled Spring to find the class and load the bean correctly.
public class MemoriesRepositoryImpl implements MemoriesRepositoryCustom {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public void updateImages(String memoryId, List<String> imagePaths) {
    // AddToSet updates the array if its already present.
    Update update = new Update();
    Update.AddToSetBuilder builder = update.addToSet("photos");
    builder.each(imagePaths.toArray());

    Query query = new Query();
    query.addCriteria(Criteria.where("_id").is(memoryId));

    // Upsert adds the field if its not present in the DB.
    this.mongoTemplate.upsert(query, update, Memory.class);
  }
}
