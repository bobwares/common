package com.bobwares.common.registry;

import java.util.Optional;
import org.springframework.core.io.Resource;

public interface ResourceMapper<T> {

  Optional<T> resourceToObject(Resource resource);

}
