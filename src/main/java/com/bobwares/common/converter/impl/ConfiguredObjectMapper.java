package com.bobwares.common.converter.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.springframework.stereotype.Component;

@Component
public class ConfiguredObjectMapper extends ObjectMapper {

  public ConfiguredObjectMapper() {
    this.registerModule(new JodaModule());
    this.registerModule(new GuavaModule());
    this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    this.configure(SerializationFeature.INDENT_OUTPUT, true);
    this.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    this.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
  }

}
