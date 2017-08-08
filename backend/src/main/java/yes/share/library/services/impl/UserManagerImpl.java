package yes.share.library.services.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import yes.share.library.persistence.dao.UserDao;
import yes.share.library.persistence.dao.UserRoleDao;
import yes.share.library.persistence.entity.User;
import yes.share.library.persistence.entity.UserRole;
import yes.share.library.services.UserManager;

/**
 * 
 * Also a placeholder for @Transactional and @Cacheable annotations
 *
 * @author Noé Comesaña
 */
@Service
public class UserManagerImpl implements UserManager {

    private UserDao userDao;
    private UserRoleDao userRoleDao;
    private BCryptPasswordEncoder passEncoder;
	
	@Autowired
    public UserManagerImpl(UserDao userDao, UserRoleDao userRoleDao, BCryptPasswordEncoder passEncoder) {
		this.userDao = userDao;
		this.userRoleDao = userRoleDao;
		this.passEncoder = passEncoder;
	}

	@Override
    @Transactional(readOnly = true)
    public Iterable<User> findAll() throws DataAccessException {
        return userDao.findAll();
    }
	
	@Override
    @Transactional(readOnly = true)
    public User findById(Long userId) throws DataAccessException {
        return userDao.findOne(userId);
    }
	
	@Override
    @Transactional(readOnly = true)
    public User findByFacebookId(String facebookId) throws DataAccessException {
        return userDao.findByFacebookId(facebookId);
    }
	
	@Override
    @Transactional
    public void save(User user) throws DataAccessException {
        userDao.save(user);
        userRoleDao.save(user.getUserRoles());
    }
	
	@Override
	@Transactional
	public Long createUser(User user) throws DataAccessException {
		//Validamos los datos para la creacion de un nuevo usuario
		this.validateNewUser(user);
		//De haber una contraseña, la encriptamos
		if (user.getPassword() != null) {
			CharSequence rawPassword = new String(user.getPassword());
			user.setPassword(passEncoder.encode(rawPassword).toCharArray());
		}
		//Añadimos el rol por defecto que tendrán todos los usuarios
		user.setUserRoles(new HashSet<UserRole>());
		user.getUserRoles().add(new UserRole(user));
		this.save(user);
		return user.getId();
	}
	
	protected void validateNewUser(User user) throws ValidationException {
		
		if ( StringUtils.isEmpty(user.getFacebookId()) && 
				StringUtils.isEmpty(user.getGoogleId()) && 
					StringUtils.isEmpty(user.getGithubId()) && 
						StringUtils.isEmpty(user.getEmail()) )
		{
			throw new ValidationException("Datos insuficientes para crear nueva cuenta de usuario");
		}
		
		// TODO crear unha excepción propia ou buscar unha para indicar error de validación dunha entidade
//		if (StringUtils.isNullOrEmpty(user.getUsername())) {
//			//throw new ValidationException("Empty data");
//		} else if (StringUtils.isNullOrEmpty(user.getPassword().toString())) {
//			//throw new ValidationException("Empty data");
//		} else if (StringUtils.isNullOrEmpty(user.getEmail())) {
//			//throw new ValidationException("Empty data");
//		} else if (StringUtils.isNullOrEmpty(user.getName())) {
//			//throw new ValidationException("Empty data");
//		}
//			
//		validatePasswordStrength(user.getPassword());
	}
	
	protected boolean validatePasswordStrength(char[] password) throws ValidationException {
		return true;
		// TODO validar fortaleza de la contraseña
	}
}