package com.memories;

import java.util.List;

/**
 * Created by rohitgirme on 1/17/16.
 */
public interface MemoryService {

    Memory get(String id);
    Memory create(Memory memory);
    Memory update(Memory memory);
    void delete(String id);
    List<Memory> getAll();
}
