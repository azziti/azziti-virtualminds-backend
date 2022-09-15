package com.virtualminds.test.backend.services.impl;

import com.virtualminds.test.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserService userService;

    //get user with roles
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.virtualminds.test.backend.entities.User
                user = userService.findUserByUsername(username);

        if(user == null)
        {
            System.out.println("not found");
            throw new UsernameNotFoundException("user not found");
        }

        Collection<GrantedAuthority> authorities= new ArrayList<>();
        user.getRoles().forEach(role->{
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });
        return new User(user.getUsername(),user.getPassword(),authorities);
    }

}

