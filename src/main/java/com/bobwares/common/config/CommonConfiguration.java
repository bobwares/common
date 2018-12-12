package com.bobwares.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.bobwares.common"})
public class CommonConfiguration {


    @Bean
    public CommonMarker batchServerMarkerBean() {
        return new CommonMarker();
    }

    public class CommonMarker {
    }
}
