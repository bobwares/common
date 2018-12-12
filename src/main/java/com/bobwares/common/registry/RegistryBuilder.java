package com.bobwares.common.registry;

public interface RegistryBuilder<T> {

  <T> void put(String key, T value);

}
