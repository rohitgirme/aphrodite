package com.memories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by rohitgirme on 1/17/16.
 */
@Service
public class MongoMemoryService implements MemoryService {

  private final MemoriesRepository memoriesRepository;

  @Autowired
  public MongoMemoryService(MemoriesRepository memoriesRepository) {
    this.memoriesRepository = memoriesRepository;
  }

  @Override
  public Memory get(String id) {
    return memoriesRepository.findOne(id);
  }

  @Override
  public Memory create(Memory memory) {
    return memoriesRepository.save(memory);
  }

  @Override
  public Memory update(Memory memory) {
    return memoriesRepository.save(memory);
  }

  @Override
  public void delete(String id) {
    memoriesRepository.delete(id);
  }

  @Override
  public List<Memory> getAll() {
    return memoriesRepository.findAll();
  }
}
