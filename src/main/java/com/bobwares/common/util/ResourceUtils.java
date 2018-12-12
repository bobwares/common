package com.bobwares.common.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Slf4j
public final class ResourceUtils {

  private ResourceUtils() {
  }

  private static ClassLoader loader;
  static{
    loader = ResourceUtils.class.getClassLoader();
  }

  public static Map<String,Resource> findClasspathResources(String folder, String extension) throws IOException {
    Map<String,Resource> resourceMap = new HashMap<>();

    String newFolder = folder;

    if (newFolder == null || newFolder.length() == 0) {
      newFolder = "/";
    }
    else if (!newFolder.endsWith("/")) {
      newFolder += "/";
    }

    boolean startsWithSlash = newFolder.startsWith("/");

      String resourcePattern = "classpath*:" + newFolder +  "**/*";

      if (extension != null) {
        resourcePattern += extension.startsWith(".") ? extension : "." + extension;
      }

      PathMatchingResourcePatternResolver rr = new PathMatchingResourcePatternResolver();
      Resource[] resources = rr.getResources(resourcePattern);
      for (Resource resource : resources) {
        String url = resource.getURL().toString();

        int i = url.lastIndexOf(newFolder);
        if (startsWithSlash) {
          i++;
        }

        String resourceName = url.substring(i);

        Resource classPathResource = resourceMap.get(resourceName);
        if (classPathResource == null) {
          classPathResource = new ClassPathResource(resourceName);
          log.debug("adding resource {} from {}", classPathResource, resource);
            resourceMap.put(resourceName, classPathResource);
        }
      }

      return resourceMap;
  }

}
