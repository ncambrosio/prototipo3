package yes.share.library.oauth2;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.FixedPrincipalExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.config.BeanDefinition;

import yes.share.library.persistence.entity.User;

@Configuration
@Service("githubService")
public class Oauth2ServiceGithub implements Oauth2Service {

	@Value("${github.client.clientId}")
	private String appId;
	
	@Value("${github.client.clientSecret}")
    private String secretId;
	
	private String loginPath = "/login/github";
	
    @Bean(name="githubConfig")
    @ConfigurationProperties("github")
    public Oauth2ServerConfig getServerConfig() {
    	return new Oauth2ServerConfig();
    }

    @Bean(name="githubExtractor")
    public PrincipalExtractor getPrincipalExtractor() {
    	return new FixedPrincipalExtractor();    	
    }

	@Override
	public String getLoginPath() {
		return loginPath;
	}

	@Override
	public Object getUserFromOauthServer(String accessToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User createUserFromOauthToken(String accessToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User createUserFromOauthUser(Object oauthUser) {
		User appUser = new User();
		updateUserFromOauthUser(appUser, oauthUser);
		return appUser;
	}

	@Override
	public void updateUserFromOauthUser(User appUser, Object oauthUser) {
		// TODO Auto-generated method stub
	}
}
