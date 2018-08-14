package cn.icmyfuture.iarc.data.dao;

import cn.icmyfuture.iarc.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {
    User getUser(@Param("id") Integer id);
    List<User> getAllUsers();
    void insertUser(User entity);
}
