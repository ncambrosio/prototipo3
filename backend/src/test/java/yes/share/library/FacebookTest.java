package yes.share.library;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookObject;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FacebookTest {
	
	String appId = "1846070458978797";
	String appSecret = "b8916948aa72dd7d8a190f951882ff00";

	String userToken = "EAAaOZCcXwEe0BAH13rT6M22TrEvgeD7gKGZBpR1moe83Iw3ZC4BB8S10TGWuXA1NapW7YOOlPivx6AGkqj3qZAQ9hgkKziR07VfGLaDER3qbuGcAIZBhg34sMqH5cJkKkVWrY5B6JEZAA2ClP6MZCEjLTamCkCIZCZAt08raA7sqrfAZDZD";
	String facebookUserId = "1198002996995517";
	
	@Test
	public void facebookTest() {
		
		// Funciona pero hai que ter, en cada caso, un TOKEN válido!
		
		FacebookConnectionFactory facebookConnectionFactory = new FacebookConnectionFactory(appId, appSecret);
		
		AccessGrant accessGrant = new AccessGrant(userToken);
		
		Connection<Facebook> connection = facebookConnectionFactory.createConnection(accessGrant);
		Facebook facebook = connection.getApi();
		String [] fields = { "id", "email",  "first_name", "last_name" };
		
		User userProfile = facebook.fetchObject("me", User.class, fields);
		
		System.out.println("ID: " + userProfile.getId());
		System.out.println("Fisrt: " + userProfile.getFirstName());
		System.out.println("Email: " + userProfile.getEmail());
	}
	
	
	
	
}
