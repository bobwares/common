package com.bobwares.common.registry.impl;

import com.bobwares.common.converter.ObjectConverter;
import com.bobwares.common.registry.ResourceMapper;
import java.io.InputStream;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;

@Slf4j
public class ResourceMapperImpl<T> implements ResourceMapper<T> {

  private final ObjectConverter objectMapper;

  private final Class<T> registryType;

  public ResourceMapperImpl(ObjectConverter objectMapperImpl, Class<T> registryType) {
    this.objectMapper = objectMapperImpl;
    this.registryType = registryType;
  }

  @Override
  public Optional<T> resourceToObject(Resource resource) {
    try {
      InputStream inputStream = resource.getInputStream();
      T entry = objectMapper.jsonToObject(inputStream, registryType);
      return Optional.of(entry);
    }
    catch (Exception e) {
      log.error("Failed to load configuration for {}", resource, e);
      return Optional.empty();
    }
  }
}
