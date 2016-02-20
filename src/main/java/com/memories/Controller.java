package com.memories;

import com.memories.data.Memory;
import com.memories.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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
  // 'images' should be same as the key in FormData containing the images.
  // 'id' is the model id to which these images belong to.
  public void uploadFile(@RequestParam List<MultipartFile> images, @PathVariable String id) {
    logger.debug("### createMemory");
    if (!images.isEmpty()) {
      List<String> imagePaths = new ArrayList<>(images.size());
      // Save all files in the right location.
      // This is so that when requested by the browser, they are present.
      for (MultipartFile image : images) {
        FileUtils.saveFile(image, id);
        imagePaths.add("images/" + id + "/" + image.getOriginalFilename());
      }
      memoryService.updateImages(id, imagePaths);
    }
  }

  @ExceptionHandler
  public void handleException(Exception ex) {
    logger.error("### Error in handleException {}", ex.getMessage());
  }
}
