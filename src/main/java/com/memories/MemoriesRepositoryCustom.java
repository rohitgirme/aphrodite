package com.memories;

import java.util.List;

/**
 * Created by rohitgirme on 2/12/16.
 */
public interface MemoriesRepositoryCustom {

  public void updateImages(String memoryId, List<String> imagePaths);
}
