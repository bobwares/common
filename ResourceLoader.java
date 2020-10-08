package com.climate.talus.util;

import com.amazonaws.util.StringInputStream;
import com.climate.btr.common.registry.exception.ResourceLoaderException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResourceLoader {
  public static <T> T  load(String path, Class<T> resultType) {
    try {

      ClassLoader classLoader = ResourceLoader.class.getClassLoader();
      File file = new File(classLoader.getResource(path).getFile());
      final ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.registerModule(new JavaTimeModule());
      objectMapper.registerModule(new JodaModule());
      objectMapper.registerModule(new GuavaModule());
      objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
      objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
      objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
      objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

      return objectMapper.readValue(file, resultType);
    } catch (IOException e) {
      log.error(e.getMessage());
      throw new ResourceLoaderException(e.getMessage());
    }
  }

  public static <T> T   jsonToObject(String jsonString, Class<T> resultType) throws IOException {
    return new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(new StringInputStream(jsonString), resultType);

  }

}
