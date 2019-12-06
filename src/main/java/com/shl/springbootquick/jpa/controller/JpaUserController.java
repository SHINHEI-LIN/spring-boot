package com.shl.springbootquick.jpa.controller;

import com.shl.springbootquick.jpa.entity.User;
import com.shl.springbootquick.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JpaUserController {

    // 默认按类型装配（是spring的注解），默认装配的对象必须存在；
    // 可以配合@Qualifier使用按照名称注入；
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        User user  = userRepository.getOne(id);

        return user;
    }

    /**
     * @GetMapping 是@RequestMapping(method = RequestMethod.GET)的缩写
     */
    @GetMapping("/user/insert")
    public User insertUser(User user) {
        User ret = userRepository.save(user);

        return ret;
    }

    @GetMapping(value = "/user")
    public List<User> listUser() {
        List<User> users = userRepository.findAll();

        return users;
    }
}
