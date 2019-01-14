package com.bobwares.common.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ObjectConverter {
  <T> Optional<T>  loadResourceToObject(String path, Class<T> resultType);

  Optional<JsonNode> mapToJson(List<Map<String, Object>> inputList);

  Optional<Map<String, Object>> jsonToMap(String jsonString);

  <T> Optional<T> jsonToObject(String inputString, TypeReference valueTypeRef, Class<T> resultType);

  <T> Optional<T> jsonToObject(String inputString, Class<T> resultType);

  <T> T jsonToObject(InputStream inputStream, Class<T> resultType);

  <T> T jsonToObject(InputStreamReader inputStream, Class<T> resultType);

  <T> JsonNode objectToJsonNode(T object);

}
