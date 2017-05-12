package yes.share.library.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ImportResource("classpath:/applicationContext.xml")
public class YesShareWebConfig extends WebMvcConfigurerAdapter{

}