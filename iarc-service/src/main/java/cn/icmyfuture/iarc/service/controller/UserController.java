package cn.icmyfuture.iarc.service.controller;

import cn.icmyfuture.iarc.entity.User;
import cn.icmyfuture.iarc.service.api.UserService;
import com.alibaba.dubbo.config.annotation.Reference;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@Api(tags = "user", description = "用户接口")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Reference
    private UserService userService;

    @ApiOperation(value="获取用户", notes="根据用户ID来获取用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "int")
    @CrossOrigin(origins = "*")
    @GetMapping("getUser/{id}")
    public User getUser(@PathVariable("id") int id) {
        return userService.getUser(id);
    }

    @ApiOperation(value="获取所有用户", notes="获取所有用户")
    @CrossOrigin(origins = "*")
    @GetMapping("getAllUsers")
    public List<User> getAllUsers() {
        logger.warn("javax.BusinessException: complex getAllUsers");
        logger.warn("simple getAllUsers");
        return userService.getAllUsers();
    }

    @ApiOperation(value="获取所有用户", notes="获取所有用户")
    @CrossOrigin(origins = "*")
    @GetMapping("getUsersByName/{name}")
    public List<User> getUsersByName(@PathVariable("name") String name) {
        return userService.getUsersByName(name);
    }

    @ApiOperation(value="新增用户", notes="新增用户")
    @CrossOrigin(origins = "*")
    @PostMapping("insertUser")
    public void insertUser(@RequestBody User user) {
        userService.insertUser(user);
    }
}
