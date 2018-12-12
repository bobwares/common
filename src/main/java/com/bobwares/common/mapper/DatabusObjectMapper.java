package com.bobwares.common.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.stereotype.Component;

@Component
public class DatabusObjectMapper extends ObjectMapper {

  public DatabusObjectMapper() {
    this.registerModule(new JodaModule());
    this.registerModule(new GuavaModule());
    this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    this.configure(SerializationFeature.INDENT_OUTPUT, true);
    this.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

}
