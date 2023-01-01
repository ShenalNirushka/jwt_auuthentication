package com.nirushka.jwt.dao;

import com.nirushka.jwt.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao  extends CrudRepository<User, String> {

    Optional<User> findByUserName(String userName);

}
