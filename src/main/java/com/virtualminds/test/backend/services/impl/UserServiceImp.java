package com.virtualminds.test.backend.services.impl;

import com.virtualminds.test.backend.entities.Role;
import com.virtualminds.test.backend.entities.User;
import com.virtualminds.test.backend.repositories.RoleRepository;
import com.virtualminds.test.backend.repositories.UserRepository;
import com.virtualminds.test.backend.services.UserService;
import com.virtualminds.test.backend.utils.error_managment.exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;


@Service
@Transactional
public class UserServiceImp implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    // add new user
    @Override
    public User saveUser(User user) {
        User checkUser = userRepository.findByUsername(user.getUsername());

        if( checkUser == null ){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        } else {
            throw new UserAlreadyExistsException("Ce nom d'utilisateur est déjà utilisé !");
        }
    }

    @Override
    public Role saveRole(@Valid Role role) {
        // TODO Auto-generated method stub
        return roleRepository.save(role);
    }

    @Override
    public void AddRoleToUser(String username, String roleName) {
        User user=userRepository.findByUsername(username);
        Role role=roleRepository.findByRoleName(roleName);

        user.getRoles().add(role);
    }

    @Override
    public User findUserByUsername(String usename) {
        return userRepository.findByUsername(usename);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }


}
