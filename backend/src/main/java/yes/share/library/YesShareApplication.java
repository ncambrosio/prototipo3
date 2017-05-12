package yes.share.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import yes.share.library.config.YesShareProperties;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
@EnableConfigurationProperties(YesShareProperties.class)
@EnableJpaRepositories("yes.share.library.persistence.dao")
public class YesShareApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(YesShareApplication.class, args);
    }
}