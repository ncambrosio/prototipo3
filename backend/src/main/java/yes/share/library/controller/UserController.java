package yes.share.library.controller;

import yes.share.library.persistence.entity.User;
import yes.share.library.services.UserManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 
 * @author Noé Comesaña
 */
@RestController
public class UserController {

    private UserManager userManager;
    
    @Autowired
    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }
    
    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public Collection<User> findAll() {
    	System.out.println("UserController -> /user/list ");
    	Collection<User> coleccion = new ArrayList<User>();
    	for (User user : userManager.findAll()) {
    		cleanUserResponse(user);
    		coleccion.add(user);
    	}
        return coleccion;
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public User findUser(@PathVariable("userId") int userId) {
    	System.out.println("UserController -> /user/userId ");
    	User user = userManager.findById(Long.valueOf(userId));
    	if (user != null) {
    		cleanUserResponse(user);
    	}
    	return user;
    }
    
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public User createUser(@RequestBody User newUser) {
    	System.out.println("UserController -> /user/userId ");
    	Long id = userManager.createUser(newUser);
    	return userManager.findById(id);
    }
    
    protected void cleanUserListResponse(List<User> users) {
    	for (User user : users) {
    		cleanUserResponse(user);
    	}
    }
    
    protected void cleanUserResponse(User user) {
    	user.setPassword(null);
    }
}
