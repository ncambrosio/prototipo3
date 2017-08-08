package yes.share.library.oauth2;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.util.StringUtils;

import yes.share.library.persistence.entity.User;
import yes.share.library.services.UserManager;

public class PrincipalExtractorFacebook implements PrincipalExtractor {

	@Autowired
	UserManager userManager;
	
	@Autowired
	Oauth2ServiceFacebook oauthService;
	
	private static final String AUTHORITIES = "authorities";
	
	private static final String[] PRINCIPAL_KEYS = new String[] { "user", "username",
			"userid", "user_id", "login", "id", "name" };

    @Override
    public Object extractPrincipal(Map<String, Object> map) {   
        
    	String facebookId = null;
    	
    	if (map.get("id") instanceof String) {
    		facebookId = (String) map.get("id");
    	} else if (map.get("id") instanceof Integer) {
    		Integer id = (Integer) map.get("id");
    		facebookId = id.toString();
    	}
    	
    	User usuario = userManager.findByFacebookId(facebookId);
    	
    	String accessToken = null;
    	if (map.containsKey("accessToken")) {
    		accessToken = (String) map.get("accessToken");
    	}
    	
    	if (usuario == null && accessToken != null) {
        	usuario = oauthService.createUserFromOauthToken(accessToken);
            usuario.setFacebookId(facebookId);
            try {
            	Long id = userManager.createUser(usuario);
            	usuario.setId(id);
            } catch (Exception e) {
            	System.out.println("Error al registrar el nuevo usuario de Facebook: " + e.getMessage());
            	System.out.println(e.getStackTrace());
            }
        }

    	if (usuario != null) {
    		// Sobrescribimos las authorities recibidos del servidior de autorización por aquellos
    		// registrados para el usuario en la base de datos.
        	map.put(AUTHORITIES, usuario.getAuthorities());
        }
    	
        return usuario;
    }
}