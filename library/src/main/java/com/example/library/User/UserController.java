package com.example.library.User;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    //存放全部 User
    ArrayList<User> userList = new ArrayList<>();

    //创建新用户
    @PostMapping("/create")
    public String createUser(@RequestBody User newUser){

        //检查 username 是否已被使用
        Boolean isExisted = false;
        for (User user : userList) {
            if(user.getUsername().equals(newUser.getUsername())){
                isExisted = true;
                break;
            }
        }

        if(isExisted){
            //用户名已被使用
            return "already existed username";
        } else{
            //新用户创建成功
            userList.add(newUser);
            return "new user registered";
        }        
    }

    //根据 username 查看用户信息
    @GetMapping("/{username}")
    public User findWithUserName(@PathVariable String username){
        User result = null;
        for (User user : userList) {
            if(user.getUsername().equals(username)){
                result = user;
            }
        }
        return result;
    }

    //查看全部用户信息
    @GetMapping("")
    public ArrayList<User> findAllUser(){
        return userList;
    }

    //更新用户信息
    @PostMapping("/{username}/update")
    public String updateUser(@RequestBody User newUser, @PathVariable String username){
    
        Boolean updated = false;
        for (User user : userList) {
            if(user.getUsername().equals(username)){
                updated = true;
                user.setRealname(newUser.getRealname());
                user.setPassword(newUser.getPassword());
                user.setGender(newUser.getGender());
                break;
            }
        }

        return updated ? "updated" : "no match";
    }

    //删除用户
    @PostMapping("/{username}/delete")
    public String deleteUser(@PathVariable String username){
        Boolean deleted = false;
        for (User user : userList) {
            if(user.getUsername().equals(username)){
                deleted = true;
                userList.remove(user);
                break;
            }
        }
        return deleted ? "deleted" : "no match";
    }

}