/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package yes.share.library.controller;

import yes.share.library.persistence.entity.User;
import yes.share.library.services.UserManager;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 
 * @author Noé Comesaña
 */
@RestController
public class UserController extends AbstractResourceController {

    private UserManager userManager;
    
    @Autowired
    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }
	
    /**
     * Read List of Users
     */
    @GetMapping("/user/list")
    public Collection<User> findAll() {
    	System.out.println("UserController -> /user/list ");
    	Collection<User> coleccion = new ArrayList<User>();
    	for (User u : userManager.findAll()) {
    		coleccion.add(u);
    	}
        return coleccion;
    }

    /**
     * Read single User
     */
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public User findOwner(@PathVariable("userId") int userId) {
    	System.out.println("UserController -> /user/userId ");
    	return userManager.findById(Long.valueOf(userId));
    }
}
