package yes.share.library.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Autowired
    public UserManagerImpl(UserDao userDao) {
		this.userDao = userDao;
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
}