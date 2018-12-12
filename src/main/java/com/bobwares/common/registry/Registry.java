package com.bobwares.common.registry;

import java.util.Optional;

public interface Registry<T> {

  Optional<T> get(String key);

}
