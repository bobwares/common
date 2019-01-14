package com.bobwares.common.registry.impl;

import com.bobwares.common.registry.Registry;
import com.bobwares.common.registry.RegistryKeyBuilder;
import com.bobwares.common.registry.ResourceMapper;
import com.bobwares.common.registry.ResourceReader;
import com.bobwares.common.registry.exception.ResourceReaderException;
import java.util.Map;
import java.util.Map.Entry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;


@Slf4j
public class ResourceReaderImpl<T>  implements  ResourceReader {

  private final ResourceMapper<T> resourceMapper;

  private final RegistryKeyBuilder registryKeyBuilder;

  private final Registry<T> registry;

  public ResourceReaderImpl(
      Registry<T> registry,
      ResourceMapper<T> resourceMapper,
      RegistryKeyBuilder registryKeyBuilder) {
    this.registry = registry;
    this.resourceMapper = resourceMapper;
    this.registryKeyBuilder = registryKeyBuilder;
  }

  public void read(Map<String, Resource> resourceMap) {
    for (Entry<String, Resource> resourceEntry : resourceMap.entrySet()) {
      try {
        T entry = resourceMapper.resourceToObject(resourceEntry.getValue()).orElseThrow(ResourceReaderException::new);
        String registryKey = registryKeyBuilder.build(resourceEntry.getKey(), entry);
        registry.put(registryKey, entry);
      } catch (Throwable throwable) {
        throwable.printStackTrace();
      }
    }
  }
}

