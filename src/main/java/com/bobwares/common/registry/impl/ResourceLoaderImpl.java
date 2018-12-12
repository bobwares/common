package com.bobwares.common.registry.impl;

import com.bobwares.common.registry.ResourceLoader;
import com.bobwares.common.util.ResourceUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ResourceLoaderImpl implements ResourceLoader {

  /*
  *
  *  Make a list of resource files contained in a folder with the required extension. Store
  *  the list in a map.
  */

  @Override
  public Map<String,Resource> load(String folder,String extension) {

    try {

      Map<String,Resource> resourceMap = new HashMap<>();

      for (Entry<String, Resource> entry : ResourceUtils.findClasspathResources(folder, extension).entrySet()) {
        String key = entry.getKey().substring(folder.length() + 1, entry.getKey().length() - extension.length() - 1);
        resourceMap.put(key, entry.getValue());
      }
      return resourceMap;
    } catch(IOException e) {
      log.error("Failed to scan folder {}", folder, e);
      return null;
    }
  }
}

