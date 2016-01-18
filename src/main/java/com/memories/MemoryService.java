package com.memories;

import java.util.List;

/**
 * Created by rohitgirme on 1/17/16.
 */
public interface MemoryService {

    Memory get(String id);
    void create(Memory memory);
    void update(Memory memory);
    void delete(String id);
    List<Memory> getAll();
}
