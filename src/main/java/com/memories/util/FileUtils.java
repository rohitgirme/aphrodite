package com.memories.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by rohitgirme on 2/7/16.
 */
public final class FileUtils {

  private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

  // This should be added to an environment variable.
  private static final String rootDirectory = "/Users/rohitgirme/Documents/Projects/Memories/images";

  public static void saveFile (MultipartFile file, String directoryName) {
    String fileName = file.getOriginalFilename();
    try {
      Path directoryPath = getDirectoryPath(directoryName);
      byte[] bytes = file.getBytes();
      Path imagePath = Paths.get(directoryPath.toString()).resolve(fileName);
      BufferedOutputStream stream =
          new BufferedOutputStream(new FileOutputStream(new File(imagePath.toString())));
      stream.write(bytes);
      stream.close();
    } catch (IOException ex) {
      logger.error("*******Error while saving file: " + fileName, ex.getStackTrace());
    }
  }

  public static Path getDirectoryPath (String directoryName) {
    Path memoryPath = null;
    try {
      memoryPath = FileSystems.getDefault().getPath(rootDirectory, directoryName);
      if (!Files.exists(memoryPath)) {
        Files.createDirectory(memoryPath);
      }
    } catch (IOException ex) {
      logger.error("*******Error while creating directory: " + directoryName, ex.getStackTrace());
    }
    return memoryPath;
  }

}
