package com.memories;

import com.memories.data.Memory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by rohitgirme on 1/17/16.
 */
@Service
public interface MemoryService {

  Memory get(String id);
  Memory create(Memory memory);
  Memory update(Memory memory);
  void updateImages(String memoryId, List<String> imagePaths);
  void delete(String id);
  List<Memory> getAll();
}
