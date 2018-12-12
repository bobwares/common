package com.bobwares.common.registry;

import java.util.Map;
import org.springframework.core.io.Resource;

public interface ResourceLoader {

  Map<String,Resource> load(String folder,String extension);

}
