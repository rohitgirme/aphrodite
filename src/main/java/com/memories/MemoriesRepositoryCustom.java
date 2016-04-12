package com.memories;

import com.memories.data.Memory;

import java.util.List;

/**
 * Created by rohitgirme on 2/12/16.
 */
public interface MemoriesRepositoryCustom {

  void updateImages(String memoryId, List<String> imagePaths);

  List<Memory> getFiltered(int skip, int limit);

  List<Memory> getTopMemories(int count);
}
