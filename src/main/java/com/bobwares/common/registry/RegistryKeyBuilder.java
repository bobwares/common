package com.bobwares.common.registry;

public interface RegistryKeyBuilder<T> {

  <T> String  build(String resourceName, T item);

}
