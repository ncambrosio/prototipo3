package yes.share.library.services.impl;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.StringUtils;

import yes.share.library.persistence.dao.UserDao;
import yes.share.library.persistence.entity.User;
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
    private BCryptPasswordEncoder passEncoder;
	
	@Autowired
    public UserManagerImpl(UserDao userDao, BCryptPasswordEncoder passEncoder) {
		this.userDao = userDao;
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
    @Transactional
    public void save(User user) throws DataAccessException {
		
		CharSequence rawPassword = new String(user.getPassword());
		
		user.setPassword(passEncoder.encode(rawPassword).toCharArray());
		
        userDao.save(user);
    }
	
	@Override
	@Transactional
	public Long createUser(User user) throws DataAccessException {
		this.validateNewUser(user);
		this.save(user);
		return user.getId();
	}
	
	protected void validateNewUser(User user) throws ValidationException {
		// TODO crear unha excepción propia ou buscar unha para indicar error de validación dunha entidade
		if (StringUtils.isNullOrEmpty(user.getUsername())) {
			throw new ValidationException("Empty data");
		} else if (StringUtils.isNullOrEmpty(user.getPassword().toString())) {
			throw new ValidationException("Empty data");
		} else if (StringUtils.isNullOrEmpty(user.getEmail())) {
			throw new ValidationException("Empty data");
		} else if (StringUtils.isNullOrEmpty(user.getName())) {
			throw new ValidationException("Empty data");
		}
			
		validatePasswordStrength(user.getPassword().toString());
	}
	
	protected void validatePasswordStrength(String password) throws ValidationException {
		// TODO validar fortaleza de la contraseña
	}
}