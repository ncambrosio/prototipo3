package yes.share.library;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import yes.share.library.config.YesShareProperties;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
@EnableConfigurationProperties(YesShareProperties.class)
@EnableJpaRepositories("yes.share.library.persistence.dao")
@EnableOAuth2Sso
@RestController
public class YesShareApplication
	extends WebSecurityConfigurerAdapter 
{
	
    public static void main(String[] args) {
        SpringApplication.run(YesShareApplication.class, args);
    }
    
    @RequestMapping("/principal")
    public Principal principal(Principal principal) {
      return principal;
    }
    
    @Override
    public void init(WebSecurity web) throws Exception {
    	super.init(web);
        web.ignoring().antMatchers("/");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*    	
    	http
        .antMatcher("/**").authorizeRequests()
        .antMatchers("/index.html", "/login**", "/webjars/**").permitAll()
        .anyRequest().authenticated()
        .and().exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/index.html"))
        .and().logout().logoutSuccessUrl("/")
        .and().csrf().disable();
*/

    	http
        .authorizeRequests()
        	.antMatchers("/", "/login", "/logout").permitAll()
        	.antMatchers("/principal", "/user/**").authenticated()
        	//.anyRequest().authenticated()
        .and()
        	.exceptionHandling()
        	.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"))
        .and()
        	.logout()
        	.logoutSuccessUrl("/")
        .and().
        	csrf()
        	.disable();

    	
//		http
//		.authorizeRequests()
//		.anyRequest().authenticated()
//		.and().formLogin()
//		.and().httpBasic()
//		.and().logout().logoutSuccessUrl("/").permitAll()
//        .and().csrf().disable();
    }
}