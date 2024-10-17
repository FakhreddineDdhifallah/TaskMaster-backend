package com.Friend.TaskMAster.service;

import com.Friend.TaskMAster.model.User;

public interface UserService {

    User findUserProfileByJwt(String jwt) throws Exception;
    User findUserByEmail(String email) throws Exception;


    User findUserById(long userId) throws Exception;

    User updateUserProjectSize(User user , int number) throws Exception;

}
