package cn.icmyfuture.iarc.service.api;

import cn.icmyfuture.iarc.entity.User;

import java.util.List;

public interface UserService {
    User getUser(int id);

    List<User> getAllUsers();

    void insertUser(User user);

    List<User> getUsersByName(String name);
}
