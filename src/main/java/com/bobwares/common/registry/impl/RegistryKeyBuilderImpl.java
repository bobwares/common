package com.bobwares.common.registry.impl;

import com.bobwares.common.registry.RegistryKeyBuilder;

public class RegistryKeyBuilderImpl<T> implements RegistryKeyBuilder<T> {

  @Override
  public String build(String resourceName, T item) {
    return resourceName;
  }
}
