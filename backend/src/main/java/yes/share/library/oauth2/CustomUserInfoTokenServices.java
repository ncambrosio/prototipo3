package yes.share.library.oauth2;

import java.util.Map;

import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;

/**
 * Esta especialización de la clase inserta el 'accessToken' en el mapa de forma que queda
 * disponible para el 'PrincipalExtractor' que se emplea en el flujo del servicio en
 * en {@link UserInfoTokenServices#getPrincipal()}
 * 
 * @author noeca
 */
public class CustomUserInfoTokenServices extends UserInfoTokenServices {
	
	public CustomUserInfoTokenServices(String userInfoEndpointUrl, String clientId) {
		super(userInfoEndpointUrl, clientId);
	}
	
	@Override
	@SuppressWarnings({"unchecked"})
	protected Map<String, Object> getMap(String path, String accessToken) {
		Map<String, Object> map = super.getMap(path, accessToken);
		map.put("accessToken", accessToken);
		return map;
	}
}

