package yes.share.library.config;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CompositeFilter;

import yes.share.library.oauth2.CustomUserInfoTokenServices;
import yes.share.library.oauth2.Oauth2ServerConfig;
import yes.share.library.oauth2.Oauth2Service;
import yes.share.library.oauth2.Oauth2ServiceFacebook;
import yes.share.library.oauth2.Oauth2ServiceGithub;

@Configuration
@EnableOAuth2Client
@RestController
public class Oauth2Config extends WebSecurityConfigurerAdapter 
{  
	@Autowired
	OAuth2ClientContext oauth2ClientContext;
    
	@Resource(name="facebookService")
	Oauth2ServiceFacebook oauth2Facebook;
	
	@Resource(name="githubService")
	Oauth2ServiceGithub oauth2Github;
	
    @RequestMapping("/principal")
    public Principal principal(Principal principal) {
    	
    	//aqui la idea es devolver un usuario tipo user y tal vez un token JWT (haría falta?)
    	
    	if (principal != null) {
    		return principal; // TODO devolver menos información que la recogida aquí
        }
        return null;
    }
    
//    @RequestMapping("/principal")
//    public Map<String, String> user(Principal principal) {
//        if (principal != null) {
//        	
//        	OAuth2AccessToken accessToken = oauth2ClientContext.getAccessToken(); // TODO check 'oauth2ClientContext' 
//        	
//        	OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
//                     
//            Authentication authentication = oAuth2Authentication.getUserAuthentication();
//            
//            //Map<String, String> details = new LinkedHashMap<>();
//            //details = (Map<String, String>) authentication.getDetails();
//            //logger.info("details = " + details);  // id, email, name, link etc.
//            Map<String, String> map = new LinkedHashMap<>();
//            //map.put("email", details.get("email"));
//            //map.put("userAuthentication", details.get("name"));
//            map.put("email", "email@gad.es");
//            map.put("userAuthentication", "Noé");
//            
//            return map;
//        }
//        return null;
//    }  
    
    @Override
    public void init(WebSecurity web) throws Exception {
    	super.init(web);
        web.ignoring().antMatchers("/");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	http
    	.addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class)
        .authorizeRequests()
        	.antMatchers("/", "/login", "/login/**", "/logout").permitAll()
        	//.antMatchers("/principal", "/user/**").authenticated()
        	//.antMatchers("/principal").authenticated() // accede
        	//.antMatchers("/principal").hasAnyRole("ROLE_USER") // non accede
        	.antMatchers("/principal").hasAnyAuthority("ROLE_USER")
        	.antMatchers("/user/**").hasAnyAuthority("ROLE_ADMIN")
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
    }
    
    private Filter ssoFilter() {
    	
    	CompositeFilter filter = new CompositeFilter();
    	List<Filter> filters = new ArrayList<Filter>();
    	filters.add(ssoFilter(oauth2Facebook));
    	filters.add(ssoFilter(oauth2Github));
    	filter.setFilters(filters);
    	return filter;
    }
    
    private Filter ssoFilter(Oauth2Service oauth2Service) {
    	
    	Oauth2ServerConfig loginServer = oauth2Service.getServerConfig();
    	PrincipalExtractor principalExtrator = oauth2Service.getPrincipalExtractor();
    	String loginPath = oauth2Service.getLoginPath();
    	
    	OAuth2ClientAuthenticationProcessingFilter oauth2ClientFilter = new OAuth2ClientAuthenticationProcessingFilter(loginPath);
    	OAuth2RestTemplate oauth2RestTemplate = new OAuth2RestTemplate(loginServer.getClient(), oauth2ClientContext);
    	oauth2ClientFilter.setRestTemplate(oauth2RestTemplate);
//    	UserInfoTokenServices tokenServices = new UserInfoTokenServices(loginServer.getResource().getUserInfoUri(), loginServer.getClient().getClientId());
    	UserInfoTokenServices tokenServices = new CustomUserInfoTokenServices(loginServer.getResource().getUserInfoUri(), loginServer.getClient().getClientId());   	
    	tokenServices.setPrincipalExtractor(principalExtrator);
    	tokenServices.setRestTemplate(oauth2RestTemplate);
    	oauth2ClientFilter.setTokenServices(tokenServices);
    	return oauth2ClientFilter;
    }
    
    /**
     * 
     * O filtro {@link OAuth2ClientContextFilter} encárgase de interceptar a petición a petición
     * para redirixir ao servidor de oauth2 pertinente: Facebook, Github,...
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