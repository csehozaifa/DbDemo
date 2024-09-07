package com.example.dbDemo1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/getById")
    public ResponseEntity getUserById(@RequestParam("id")Integer id) {

    try{
        UserInfo userInfo=userService.getUserById(id);
    return new ResponseEntity<>(userInfo, HttpStatus.OK);

    } catch (Exception e){
        log.error("This is an error and",e.getMessage());
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    }
    @PostMapping("/add")
    public String addUserInfo(@RequestBody UserInfo userInfo){
        return userService.addUser(userInfo);
    }
    @DeleteMapping("/deleteById")
    public String deleteById(@RequestParam("id")Integer id){
        return userService.deleteUserById(id);
    }
    @PutMapping("/updateUser")
    public String updateUser(@RequestParam("id")Integer id,@RequestParam("mail")String mail){
    return userService.updateMail(id,mail);
    }
    @GetMapping("/findUserAboveAge")
    public ResponseEntity<List<UserInfo>> getUserInfoList(@RequestParam("age") Integer age) {
        try {
            List<UserInfo> usersAboveAge = userService.getUserAboveAge(age);
            return new ResponseEntity<>(usersAboveAge, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching users above age", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
