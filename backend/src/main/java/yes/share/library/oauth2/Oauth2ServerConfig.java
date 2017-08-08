package yes.share.library.oauth2;

import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;

/**
 * Emprégase para albergar os dous obxetos que cargas as propiedades do 'application.properties'.
 * 
 * Busca as propiedades do seguiente forma:
 * 	<ul>
 * 		<li>{cadea-heredada}.client</li>
 * 		<li>{cadea-heredada}.resource</li>
 * 	</ul>
 * @author Usuario
 *
 */
public class Oauth2ServerConfig {

	  @NestedConfigurationProperty
	  private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();

	  @NestedConfigurationProperty
	  private ResourceServerProperties resource = new ResourceServerProperties();

	  public AuthorizationCodeResourceDetails getClient() {
	    return client;
	  }

	  public ResourceServerProperties getResource() {
	    return resource;
	  }
}