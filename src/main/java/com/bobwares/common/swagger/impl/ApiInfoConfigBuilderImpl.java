package com.bobwares.common.swagger.impl;

import com.bobwares.common.registry.Registry;
import com.bobwares.common.swagger.ApiDescriptionException;
import com.bobwares.common.swagger.ApiInfoConfigBuilder;
import org.springframework.stereotype.Service;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;

@Service
public class ApiInfoConfigBuilderImpl implements ApiInfoConfigBuilder {

  private Registry<String> registry;

  public ApiInfoConfigBuilderImpl(Registry<String> apiDescriptionRegistry) {
    this.registry = apiDescriptionRegistry;
  }

  @Override
  public ApiInfo build() {

    String apiInfoDescription = registry.get("apiInfo").
        orElseThrow(ApiDescriptionException::new);

    return new ApiInfoBuilder()
        .title("")
        .description(apiInfoDescription)
        .version("v1")
        .build();
  }
}
