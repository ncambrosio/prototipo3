package yes.share.library.oauth2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;

import yes.share.library.persistence.entity.User;

@Configuration
@Service("facebookService")
public class Oauth2ServiceFacebook implements Oauth2Service {
	
	@Value("${facebook.client.clientId}")
	private String appId;
	
	@Value("${facebook.client.clientSecret}")
    private String secretId;
	
	private String loginPath = "/login/facebook";

    @Bean(name="facebookConfig")
    @ConfigurationProperties("facebook")
    public Oauth2ServerConfig getServerConfig() {
    	return new Oauth2ServerConfig();
    }

    @Bean(name="facebookExtractor")
    public PrincipalExtractor getPrincipalExtractor() {
    	return new PrincipalExtractorFacebook();    	
    }
    
    @Override
    public String getLoginPath() {
    	return loginPath;
    }
    
	@Override
	public org.springframework.social.facebook.api.User getUserFromOauthServer(String accessToken) {
		
		FacebookConnectionFactory facebookConnectionFactory = new FacebookConnectionFactory(appId, secretId);
		
		AccessGrant accessGrant = new AccessGrant(accessToken);
		Connection<Facebook> connection = facebookConnectionFactory.createConnection(accessGrant);
		Facebook facebook = connection.getApi();
		
		String [] fields = { "id", "email",  "first_name", "last_name" };
		
		org.springframework.social.facebook.api.User userProfile = 
				facebook.fetchObject("me", org.springframework.social.facebook.api.User.class, fields);
		
		System.out.println("ID: " + userProfile.getId());
		System.out.println("Fisrt: " + userProfile.getFirstName());
		System.out.println("Email: " + userProfile.getEmail());
		
		return userProfile;
	}
	
	@Override
	public User createUserFromOauthToken(String accessToken) {
		org.springframework.social.facebook.api.User oauthUser = getUserFromOauthServer(accessToken);
		return createUserFromOauthUser(oauthUser);
	}
	
	@Override
	public User createUserFromOauthUser(Object oauthUser) {
		User appUser = new User();
		updateUserFromOauthUser(appUser, oauthUser);
		return appUser;
	}

	@Override
	public void updateUserFromOauthUser(User appUser, Object oauthUser) {
		
		org.springframework.social.facebook.api.User profile = 
				(org.springframework.social.facebook.api.User) oauthUser;
		
		if (StringUtils.isNullOrEmpty(appUser.getFacebookId())) {
			appUser.setFacebookId(profile.getId());	
		}
		if (StringUtils.isNullOrEmpty(appUser.getName())) {
			appUser.setName(profile.getFirstName());	
		}
		if (StringUtils.isNullOrEmpty(appUser.getEmail())) {
			appUser.setEmail(profile.getEmail());
		}
	}
}
