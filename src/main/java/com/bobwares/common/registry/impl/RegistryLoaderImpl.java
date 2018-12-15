package com.bobwares.common.registry.impl;

import com.bobwares.common.registry.RegistryLoader;
import com.bobwares.common.registry.ResourceLoader;
import com.bobwares.common.registry.ResourceReader;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;

@Slf4j
public class RegistryLoaderImpl<T> implements RegistryLoader {

  private final Class<T> registryType;

  private final ResourceLoader resourceLoader;

  private final ResourceReader resourceReader;

  private final String folder;

  private final String extension;

  public RegistryLoaderImpl(
      ResourceLoader resourceLoader,
      ResourceReader resourceReader,
      String folder,
      String extension,
      Class<T> registryType) {
    this.resourceLoader = resourceLoader;
    this.resourceReader = resourceReader;
    this.folder = folder;
    this.extension = extension;
    this.registryType = registryType;
  }

  @Override
  public void load() {
    final Map<String, Resource> resourceMap = resourceLoader.load(folder, extension);
    resourceReader.read(resourceMap);
  }
}
