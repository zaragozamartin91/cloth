package cloth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import cloth.config.data.DataConfig;
import cloth.config.service.ServiceConfig;

@Configuration
@Import({ DataConfig.class, ServiceConfig.class })
public class RootConfig {
}