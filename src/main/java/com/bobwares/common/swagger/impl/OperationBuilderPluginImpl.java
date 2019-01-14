package com.bobwares.common.swagger.impl;

import com.bobwares.common.registry.Registry;
import com.bobwares.common.swagger.SwaggerAPIDescription;
import com.google.common.base.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER)
@Slf4j
public class OperationBuilderPluginImpl implements OperationBuilderPlugin {

  private Registry<String> registry;

  public OperationBuilderPluginImpl(Registry<String> apiDescriptionRegistry) {
    this.registry = apiDescriptionRegistry;
  }

  @Override
  public void apply(OperationContext context) {
    Optional<SwaggerAPIDescription> methodAnnotation = context.findAnnotation(SwaggerAPIDescription.class);
    if (methodAnnotation.isPresent() && StringUtils.hasText(methodAnnotation.get().value())) {
      String value = methodAnnotation.get().value();
      log.info("Swagger Description Markdown plugin: value=" + value);
      context.operationBuilder().notes(registry.get(value).orElseThrow(RuntimeException::new));
    }
  }

  @Override
  public boolean supports(DocumentationType documentationType) {
    return true;
  }
}
