package com.bobwares.common.mapper;

import static org.junit.Assert.assertNotNull;

import com.bobwares.common.converter.ObjectConverter;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ObjectConverterTest {

  @Autowired
  ObjectConverter objectMapper;

  @Test
  public void mapToJson() {

    List<Map<String, Object>> list = new ArrayList<>();
    Map<String, Object> map = new HashMap<>();
    map.put("userId", "12345");
    list.add(map);

    Optional<JsonNode> optional = objectMapper.mapToJson(list);
    assertNotNull(optional);
  }

  @Test
  public void jsonToMap() {
  }

  @Test
  public void jsonToObject() {
  }

  @Test
  public void jsonToObject1() {
  }

  @Test
  public void jsonToObject2() {
  }

  @Test
  public void jsonToObject3() {
  }

  @Test
  public void objectToJsonNode() {
  }
}