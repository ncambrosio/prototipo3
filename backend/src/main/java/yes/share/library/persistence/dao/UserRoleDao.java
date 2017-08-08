package yes.share.library.persistence.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import yes.share.library.persistence.entity.UserRole;

@Repository
@Transactional
public interface UserRoleDao extends CrudRepository<UserRole, Long> {
  
}