package com.bobwares.common.registry;

import java.util.Map;
import org.springframework.core.io.Resource;

public interface ResourceReader {

  <T> void read(Map<String, Resource> resourceMap, Class<T> registryType);

}
