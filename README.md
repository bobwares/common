
##Databus Registry Services


- Registry
  - Map of objects. 
  
- RegistryBuilder
  - Interface that exposes the put method of the registy so that it can be built.

- ResourceLoader
  - scans files system for resource files and loads them into a map.

- ResourceReader
  - Reads a resource and maps it to a registry object.
  
- RegistryLoader
  - Builds a registry by orchestrating calls to the ResourceLoader, ResourceReader, and RegistryBuilder.

###Setup new registry

- Create Registry Bean config JavaConfig class.
   ~~~
   @Bean
   public Registry<JsonTemplateGroupDefinition> jsonTemplateGroupDefinition() {
     return new Registry<>();
   }
   ~~~
   
### Loading a registry at Spring Context load time.

- Create Resource Reader
~~~~

~~~~   
   
   