package com.nirushka.jwt.service;

import com.nirushka.jwt.dao.UserDao;
import com.nirushka.jwt.entity.SecurityUser;
import com.nirushka.jwt.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JpaUserDetailsService implements  UserDetailsService {

    private final UserDao userDao;

    public JpaUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userDao.findByUserName(username);
        System.out.println("user is : "+user.toString());
        SecurityUser sUser = userDao.findByUserName(username)
                .map(SecurityUser::new)
                .orElseThrow(()-> new UsernameNotFoundException("Username not found: "+ username));
        System.out.println("Secu user is : "+sUser.getAuthorities());
        return userDao.findByUserName(username)
                .map(SecurityUser::new)
                .orElseThrow(()-> new UsernameNotFoundException("Username not found: "+ username));
    }
}


