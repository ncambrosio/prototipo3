package yes.share.library.services;

import org.springframework.dao.DataAccessException;

import yes.share.library.persistence.entity.User;

public interface UserManager extends Manager {

	public Iterable<User> findAll() throws DataAccessException;
	
    public User findById(Long userId) throws DataAccessException;
}
