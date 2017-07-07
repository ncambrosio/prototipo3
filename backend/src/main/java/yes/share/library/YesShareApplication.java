package yes.share.library;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CompositeFilter;

import yes.share.library.config.YesShareProperties;

@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
@EnableConfigurationProperties(YesShareProperties.class)
@EnableJpaRepositories("yes.share.library.persistence.dao")
//@EnableOAuth2Sso
@EnableOAuth2Client
@RestController
public class YesShareApplication
	extends WebSecurityConfigurerAdapter 
{
	
	@Autowired
	OAuth2ClientContext oauth2ClientContext;
	  
    public static void main(String[] args) {
        SpringApplication.run(YesShareApplication.class, args);
    }
    
    @RequestMapping("/principal")
    public Principal principal(Principal principal) {
      return principal; // TODO devolver menos información que la recogida aquí
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
    	.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class)
        .authorizeRequests()
        	.antMatchers("/", "/login", "/login/**", "/logout").permitAll()
        	.antMatchers("/principal", "/user/**").authenticated()
        	//.anyRequest().authenticated()
        .and()
        	.exceptionHandling()
        	.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"))
        .and()
        	.logout()
        	.logoutSuccessUrl("/")
        .and()	
        	.csrf()
        	.disable();
        	
    	
//		http
//		.authorizeRequests()
//		.anyRequest().authenticated()
//		.and().formLogin()
//		.and().httpBasic()
//		.and().logout().logoutSuccessUrl("/").permitAll()
//        .and().csrf().disable();
    }
    
    private Filter ssoFilter() {
    	
    	CompositeFilter filter = new CompositeFilter();
    	List<Filter> filters = new ArrayList<Filter>();
    	  
    	OAuth2ClientAuthenticationProcessingFilter facebookFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/facebook");
    	OAuth2RestTemplate facebookTemplate = new OAuth2RestTemplate(facebook(), oauth2ClientContext);
    	facebookFilter.setRestTemplate(facebookTemplate);
    	UserInfoTokenServices tokenServices = new UserInfoTokenServices(facebookResource().getUserInfoUri(), facebook().getClientId());
    	tokenServices.setRestTemplate(facebookTemplate);
    	facebookFilter.setTokenServices(tokenServices);
    	filters.add(facebookFilter);
    	
    	OAuth2ClientAuthenticationProcessingFilter githubFilter = new OAuth2ClientAuthenticationProcessingFilter("/login/github");
    	OAuth2RestTemplate githubTemplate = new OAuth2RestTemplate(github(), oauth2ClientContext);
    	githubFilter.setRestTemplate(githubTemplate);
    	tokenServices = new UserInfoTokenServices(githubResource().getUserInfoUri(), github().getClientId());
    	tokenServices.setRestTemplate(githubTemplate);
    	githubFilter.setTokenServices(tokenServices);
    	filters.add(githubFilter);
    	
    	filter.setFilters(filters);
    	return filter;
    }
    
    @Bean
    @ConfigurationProperties("facebook.client")
    public AuthorizationCodeResourceDetails facebook() {
      return new AuthorizationCodeResourceDetails();
    }
    
    @Bean
    @ConfigurationProperties("facebook.resource")
    public ResourceServerProperties facebookResource() {
      return new ResourceServerProperties();
    }
    
    @Bean
    @ConfigurationProperties("github.client")
    public AuthorizationCodeResourceDetails github() {
      return new AuthorizationCodeResourceDetails();
    }
    
    @Bean
    @ConfigurationProperties("github.resource")
    public ResourceServerProperties githubResource() {
      return new ResourceServerProperties();
    }
    
    /**
     * 
     * O filtro {@link OAuth2ClientContextFilter} encárgase de redirixir dende a nosa app a Facebook.
     * 
     * Mediante o seguiente Bean asegurámonos de rexistralo cunha orde de preferencia prioritaria 
     * de modo que vaía por diante do filtro principal de Spring Security
     * 
     * @param filter
     * @return
     */
    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
      FilterRegistrationBean registration = new FilterRegistrationBean();
      registration.setFilter(filter);
      registration.setOrder(-100);
      return registration;
    }
}