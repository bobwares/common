package com.bobwares.common.registry.boot;

import com.bobwares.common.registry.RegistryLoader;
import java.util.Collection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegistryBootstrap implements InitializingBean {

  private Collection<RegistryLoader<?>> registryLoaders;

  @Autowired
  public RegistryBootstrap(Collection<RegistryLoader<?>> registryLoaders) {
    this.registryLoaders = registryLoaders;
  }

  @Override
  public void afterPropertiesSet() {
    log.info("Registry Loading is starting.");
    loadRegistries();
    log.info("Registries are loaded.");
  }

  protected void loadRegistries() {
    for (RegistryLoader registryLoader : registryLoaders) {
      registryLoader.load();
    }
  }
}
