package com.example.test.sevice;


import com.example.test.model.User;
import com.example.test.model.UserRole;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;

public interface UserService {

    //creating user
    public User createUser(User user, Set<UserRole> userRoles);

    //get user by username
    public User getUser(String username);

    //delete user by id
    public void deleteUser(Long id);

    //get all user
    public List<User> getAllUser();

}
