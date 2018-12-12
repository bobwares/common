package com.bobwares.common.mapper;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ObjectMappingUtils {

  private DatabusObjectMapper mapper;

  @Autowired
  public ObjectMappingUtils(DatabusObjectMapper mapper) {
    this.mapper = mapper;
  }

  public Optional<Map<String, Object>> jsonToMap(String jsonString) {
    try {
      final TypeReference<Map<String, Object>> typeReference = new TypeReference<Map<String, Object>>() {};
      return Optional.ofNullable(mapper.readValue(jsonString, typeReference));
    } catch (IOException e) {
      log.error("Could not deserialize string {}.", jsonString);
      return Optional.ofNullable(null);
    }
  }

  public <T> Optional<T> jsonToObject(String inputString, TypeReference valueTypeRef, Class<T> resultType) {
    try {
      return Optional.ofNullable(mapper.readValue((new ByteArrayInputStream(inputString.getBytes(UTF_8))), valueTypeRef));
    } catch (IOException e) {
      log.error("Could not deserialize string {}.", inputString);
      return Optional.ofNullable(null);
    }
  }

  public <T> Optional<T> jsonToObject(String inputString, Class<T> resultType) {
    try {
      return Optional.ofNullable(mapper.readValue((new ByteArrayInputStream(inputString.getBytes(UTF_8))), resultType));
    } catch (IOException e) {
      log.error("Could not deserialize string {}.", inputString);
      return Optional.ofNullable(null);
    }
  }

  public <T> T jsonToObject(InputStream inputStream, Class<T> resultType) {
    return inputStreamToObject(inputStream, resultType);
  }

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
        throw new DatabusMappingException(e.getMessage());
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
        throw new DatabusMappingException(e.getMessage());
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

