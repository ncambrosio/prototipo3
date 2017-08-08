package yes.share.library.oauth2;

import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;

import yes.share.library.persistence.entity.User;

public interface Oauth2Service {
	
    public Oauth2ServerConfig getServerConfig();
    
    public PrincipalExtractor getPrincipalExtractor();
    
    public String getLoginPath();
    
	public Object getUserFromOauthServer(String accessToken);
	
	public User createUserFromOauthToken(String accessToken);
	
	public User createUserFromOauthUser(Object oauthUser);
	
	public void updateUserFromOauthUser(User appUser, Object oauthUser);
}
