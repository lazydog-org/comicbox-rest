package org.lazydog.comicbox;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;

/**
 * Application configuration.
 * 
 * @author rjrjr
 */
@SpringBootApplication
@PropertySource("classpath:comicbox.properties")
public class WarInitializerApplication extends SpringBootServletInitializer {
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WarInitializerApplication.class);
    }
}
