package com.bobwares.common.swagger.config;

import com.bobwares.common.registry.Registry;
import com.bobwares.common.registry.RegistryKeyBuilder;
import com.bobwares.common.registry.RegistryLoader;
import com.bobwares.common.registry.ResourceLoader;
import com.bobwares.common.registry.ResourceMapper;
import com.bobwares.common.registry.ResourceReader;
import com.bobwares.common.registry.impl.RegistryKeyBuilderImpl;
import com.bobwares.common.registry.impl.RegistryLoaderImpl;
import com.bobwares.common.registry.impl.ResourceReaderImpl;
import com.bobwares.common.swagger.mapper.ApiDescriptionResourceMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiDescriptionRegistryConfig {

  @Bean
  public Registry<String> apiDescriptionRegistry() {
    return new Registry<>();
  }

  @Bean
  public ResourceMapper<String> apiDescriptionResourceMapper() {
    return new ApiDescriptionResourceMapper();
  }

  @Bean
  public RegistryKeyBuilder<String> apiDescriptionRegistryKeyBuilder() {
    return new RegistryKeyBuilderImpl<>();
  }

  @Bean
  public ResourceReader apiDescriptionResourceReader(
      Registry<String> apiDescriptionRegistry,
      ResourceMapper<String> apiDescriptionResourceMapper,
      RegistryKeyBuilder<String> registryKeyBuilder
  ) {
    return new ResourceReaderImpl<>(apiDescriptionRegistry, apiDescriptionResourceMapper, registryKeyBuilder);
  }

  @Bean
  public RegistryLoader apiDescriptionRegistryLoader(
      ResourceLoader resourceLoader,
      ResourceReader apiDescriptionResourceReader) {
    return new RegistryLoaderImpl(
        resourceLoader,
        apiDescriptionResourceReader,
        "swagger",
        "md");
  }

}
