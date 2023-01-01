package com.nirushka.jwt.service;

import com.nirushka.jwt.dao.RoleDao;
import com.nirushka.jwt.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;
    public Role createNewRole(Role role){
        return roleDao.save(role);
    }

}
