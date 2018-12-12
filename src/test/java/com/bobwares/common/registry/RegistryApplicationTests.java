//package com.bobwares.common.registry;
//
//import static junit.framework.TestCase.assertTrue;
//import static org.junit.Assert.assertNotNull;
//
//import com.bobwares.demo.app.common.registry.model.ResourceDefinition;
//import java.util.Map;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.core.io.Resource;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class RegistryApplicationTests {
//
//  @Autowired
//  Registry<String> registry;
//
//  @Autowired
//  Registry<ResourceDefinition> resourceDefinitionRegistry;
//
//  @Autowired
//  private ResourceLoader resourceLoader;
//
//	@Test
//	public void loader() {
//    final Map<String, Resource> resourceMap = resourceLoader.load("swagger", "md");
//    assertNotNull(resourceMap);
//    assertTrue(resourceMap.size() == 4);
//	}
//
////  @Test
////  public void reader() {
////
////    final Map<String, Resource> resourceMap = resourceLoader.load("swagger", "md");
////    assertNotNull(false);
////
////    ResourceReader resourceReader = new ApiDescriptionReader();
////    final Optional<String> apiInfo = resourceReader.read(resourceMap.get("apiInfo"), String.class);
////    assertNotNull(apiInfo);
////
////  }
//
//  @Test
//  public void builder() {
//    assertNotNull(registry);
//    assertNotNull(resourceDefinitionRegistry);
//  }
//
//}
