package com.memories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by rohitgirme on 1/10/16.
 */

// This annotation exposes the methods as HTTP endpoints. The methods are invoked based on
// criteria specified by the @RequestMapping annotation.
// @ResponseBody means write the response back to the HTTP response instead of to a model etc.
@RestController
public class Controller {

  private final MemoryService memoryService;
  private static final String rootDirectory = "/Users/rohitgirme/Documents/Projects/Memories/images";

  @Autowired
  public Controller(MemoryService memoryService) {
    this.memoryService = memoryService;
  }

  private static final Logger logger = LoggerFactory.getLogger(Controller.class);

  // Because Jackson 2 is on the classpath, Spring’s MappingJackson2HttpMessageConverter
  // is automatically chosen to convert the Memory instance to JSON.
  // JSON is chosen; since the Header has application type as JSON.
  @RequestMapping(value = "/memories", method = RequestMethod.GET)
  public List<Memory> getMemories() {
    logger.debug("### getMemories");
    return memoryService.getAll();
  }

  @RequestMapping(value = "/memories/{id}", method = RequestMethod.GET)
  public Memory getMemory(@PathVariable String id) {
    logger.debug("### getMemory with id {}", id);
    return memoryService.get(id);
  }

  @RequestMapping(value = "/memories/{id}", method = RequestMethod.PUT)
  public Memory updateMemory(@RequestBody Memory updatedMemory, @PathVariable String id) {
    logger.debug("### getMemory with id {}", id);
    return memoryService.update(updatedMemory);
  }

  @RequestMapping(value = "/memories/{id}", method = RequestMethod.DELETE)
  public void deleteMemory(@PathVariable String id) {
    logger.debug("### deleteMemory with id {}", id);
    memoryService.delete(id);
  }

  // Use DTO and Memory separate. DTO has file.
  @RequestMapping(value = "/memories", method = RequestMethod.POST)
  public void createMemory(@RequestBody Memory newMemory) {
    logger.debug("### createMemory");
    memoryService.create(newMemory);
  }

  @RequestMapping(value = "/uploadImage/{id}", method = RequestMethod.POST)
  public void uploadFile(@RequestParam MultipartFile image, @PathVariable String id) {
    logger.debug("### createMemory");
    if (!image.isEmpty()) {
      // Move to util
      try {
        Path memoryPath = FileSystems.getDefault().getPath(rootDirectory, id);
        if (!Files.exists(memoryPath)) {
          Files.createDirectory(memoryPath);
        }

        byte[] bytes = image.getBytes();
        Path imagePath = Paths.get(memoryPath.toString()).resolve(image.getOriginalFilename());
        BufferedOutputStream stream =
            new BufferedOutputStream(new FileOutputStream(new File(imagePath.toString())));
        stream.write(bytes);
        stream.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    //get path and store in memory with id.
  }

  @ExceptionHandler
  public void handleException(Exception ex) {
    logger.error("### Error in handleException {}", ex.getMessage());
  }
}
