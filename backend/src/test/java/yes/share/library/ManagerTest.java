package yes.share.library;

import javax.transaction.Transactional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import yes.share.library.persistence.entity.User;
import yes.share.library.services.UserManager;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class ManagerTest {

	@Autowired
	UserManager userManager;
	
	@Ignore
	@Test
	public void contextLoads() {
		
	}
	
	@Test
	@Rollback(false)
	public void newUserTest() {
		User nuevoUsuario = new User();
		nuevoUsuario.setName("Miguel");
		nuevoUsuario.setFamilyName1("Calvo");
		nuevoUsuario.setFamilyName2("Marques");
		nuevoUsuario.setUsername("Cunetas");
		nuevoUsuario.setPassword("1234".toCharArray());
		nuevoUsuario.setEmail("mickelcox@hotmail.com");
		try {
			userManager.save(nuevoUsuario);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}