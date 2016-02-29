package cloth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import cloth.config.data.DataConfig;

@Configuration
@Import({ DataConfig.class })
public class RootConfig {
}