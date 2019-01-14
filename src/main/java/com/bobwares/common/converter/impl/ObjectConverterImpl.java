package com.bobwares.common.converter.impl;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.bobwares.common.converter.ObjectConverter;
import com.bobwares.common.converter.ObjectConverterException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ObjectConverterImpl implements ObjectConverter {

  private ConfiguredObjectMapper mapper;

  @Autowired
  public ObjectConverterImpl(ConfiguredObjectMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  public <T> Optional<T> loadResourceToObject(String path, Class<T> resultType) {
    File file;
    try {
      ClassLoader classLoader = getClass().getClassLoader();
      file = new File(classLoader.getResource(path).getFile());
    } catch (NullPointerException e) {
      log.error("Could find file {}.", path);
      return Optional.empty();
    }
    try {
      return Optional.ofNullable(mapper.readValue(file, resultType));
    } catch (IOException e) {
      log.error("Could not deserialize file {}.", path);
      return Optional.empty();
    }
  }

  @Override
  public Optional<JsonNode> mapToJson(List<Map<String, Object>> inputList) {
    //todo finish method
    try {
      //JsonNode jsonNode = mapper.objectToJsonNode(inputList);
      String valueAsString = mapper.writeValueAsString(inputList);
      return Optional.empty();
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return Optional.empty();
    }
  }

  @Override
  public Optional<Map<String, Object>> jsonToMap(String jsonString) {
    try {
      final TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String, Object>>() {};
      return Optional.ofNullable(mapper.readValue(jsonString, typeReference));
    } catch (IOException e) {
      log.error("Could not deserialize string {}.", jsonString);
      return Optional.empty();
    }
  }

  @Override
  public <T> Optional<T> jsonToObject(String inputString, TypeReference valueTypeRef, Class<T> resultType) {
    try {
      return Optional.ofNullable(mapper.readValue((new ByteArrayInputStream(inputString.getBytes(UTF_8))), valueTypeRef));
    } catch (IOException e) {
      log.error("Could not deserialize string {}.", inputString);
      return Optional.empty();
    }
  }

  @Override
  public <T> Optional<T> jsonToObject(String inputString, Class<T> resultType) {
    try {
      return Optional.ofNullable(mapper.readValue((new ByteArrayInputStream(inputString.getBytes(UTF_8))), resultType));
    } catch (IOException e) {
      log.error("Could not deserialize string {}.", inputString);
      return Optional.empty();
    }
  }
  @Override
  public <T> T jsonToObject(InputStream inputStream, Class<T> resultType) {
    return inputStreamToObject(inputStream, resultType);
  }

  @Override
  public <T> T jsonToObject(InputStreamReader inputStream, Class<T> resultType) {
    return inputStreamReaderToObject(inputStream, resultType);
  }


  private <T> T inputStreamToObject(InputStream inputStream, Class<T> resultType) {
    if (inputStream != null) {
      try {
        return mapper.readValue(inputStream, resultType);
      }
      catch (IOException e) {
        log.error(e.getMessage());
        throw new ObjectConverterException(e.getMessage());
      }
    }
    return null;
  }

  private <T> T inputStreamReaderToObject(InputStreamReader inputStreamReader, Class<T> resultType) {
    if (inputStreamReader != null) {
      try {
        return mapper.readValue(inputStreamReader, resultType);
      }
      catch (IOException e) {
        log.error(e.getMessage());
        throw new ObjectConverterException(e.getMessage());
      }
    }
    return null;
  }

  public <T> JsonNode objectToJsonNode(T object) {
    InputStream targetStream = new ByteArrayInputStream(object.toString().getBytes());

    JsonNode jsonNode = null;

    try {
      jsonNode = mapper.readValue(targetStream, JsonNode.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return jsonNode;
  }
}

