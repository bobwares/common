package com.bobwares.common.swagger.mapper;

import com.bobwares.common.registry.ResourceMapper;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;

@Slf4j
public class ApiDescriptionResourceMapper implements ResourceMapper<String> {

  @Override
  public Optional<String> resourceToObject(Resource resource) {
    StringBuilder result = new StringBuilder();

    try {
      File file = resource.getFile();

      Scanner scanner = new Scanner(file);

      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        result.append(line).append("\n");
      }

      return Optional.of(result.toString());
    }
    catch (IOException e) {
      log.error("Failed to load configuration for {}", resource.getFilename(), e);
      return Optional.empty();
    }
  }
}
