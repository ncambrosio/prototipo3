package yes.share.library.persistence.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import yes.share.library.persistence.entity.User;

@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Long> {

  /**
   * Return the user having the passed its ID in Facebook or null if no user is found.
   * 
   * @param facebookId User's Facebook ID.
   */
  public User findByFacebookId(String facebookId);
  
  /**
   * Return the user having the passed email or null if no user is found.
   * 
   * @param email the user email.
   */
  public User findByEmail(String email);
  
}