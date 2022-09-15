package com.virtualminds.test.backend.services;

import com.virtualminds.test.backend.entities.Role;
import com.virtualminds.test.backend.entities.User;

import javax.validation.Valid;

public interface UserService {
    public User saveUser(User user);
    public Role saveRole(Role role);
    public void AddRoleToUser(String username,String role);
    public User findUserByUsername(String usename);
    public Role findRoleByRoleName(String roleName);

}
