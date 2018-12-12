package com.bobwares.common.registry.impl;

import com.bobwares.common.registry.RegistryBuilder;
import com.bobwares.common.registry.RegistryKeyBuilder;
import com.bobwares.common.registry.ResourceMapper;
import com.bobwares.common.registry.ResourceReader;
import com.bobwares.common.registry.exception.ResourceReaderException;
import java.util.Map;
import java.util.Map.Entry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;


@Slf4j
public class ResourceReaderImpl<T> implements ResourceReader {

  private final ResourceMapper resourceMapper;

  private final RegistryBuilder registryBuilder;

  private final RegistryKeyBuilder registryKeyBuilder;

  public ResourceReaderImpl(ResourceMapper resourceMapper, RegistryBuilder registryBuilder, RegistryKeyBuilder registryKeyBuilder) {
    this.resourceMapper = resourceMapper;
    this.registryBuilder = registryBuilder;
    this.registryKeyBuilder = registryKeyBuilder;
  }

  @Override
  public <T> void read(Map<String, Resource> resourceMap, Class<T> registryType) {
    for (Entry<String, Resource> resourceEntry : resourceMap.entrySet()) {
      try {
        T entry = resourceMapper.resourceToObject(resourceEntry.getValue(), registryType).orElseThrow(ResourceReaderException::new);
        String registryKey = registryKeyBuilder.build(resourceEntry.getKey(), entry);
        registryBuilder.put(registryKey, entry);
      } catch (Throwable throwable) {
        throwable.printStackTrace();
      }
    }
  }
}

