package com.bobwares.common.registry;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Registry<T> {

  private Map<String,T> objectMap = new HashMap<>();

  public Optional<T> get(final String key) {
    T value = objectMap.get(key);
    if (value == null) {
      return Optional.empty();
    }
    return Optional.of(value);
  }

  public void put(String key, T value) {
    objectMap.put(key, value);
  }

  public int size() {
    return objectMap.size();
  }

  public Set<String> keySet() {
    return objectMap.keySet();
  }
}
