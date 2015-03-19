package com.pomela.springmvc.test.mvc.services;


import com.pomela.springmvc.test.mvc.entities.User;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 13-12-29
 * <p>Version: 1.0
 */
public interface UserService {

    public User findById(Long id);
}
