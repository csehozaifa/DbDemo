package com.example.dbDemo1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
@Autowired
    private UserRepository userRepository;
public String addUser(UserInfo userInfo){
    userRepository.save(userInfo);
    return "user have been added to db successfully";
}
    public UserInfo getUserById(Integer userId) throws Exception{
        Optional<UserInfo> optionalUserInfo=userRepository.findById(userId);
        if(!optionalUserInfo.isPresent()){
            throw new Exception("User with Id is not present");
        }
        UserInfo userInfo=optionalUserInfo.get();
        return userInfo;
    }
    public String deleteUserById(Integer id){
    if(userRepository.existsById(id)){
        userRepository.deleteById(id);
        return "User with Id is deleted";
    } else{
        return "UserId is invalid";
    }


    }
    public String updateMail(Integer userId,String newMail){
    Optional<UserInfo> optionalUserInfo=userRepository.findById(userId);
    UserInfo userInfo=optionalUserInfo.get();
    userInfo.setMail((newMail));
    userRepository.save(userInfo);
    return "email updated succesfully";
    }
    public List<UserInfo> getUserAboveAge(Integer age){
    List<UserInfo> allUsers=userRepository.findAll();
    List<UserInfo> ansList=new ArrayList<>();
    for(UserInfo userInfo:allUsers){
        if(userInfo.getAge()>age){
            ansList.add(userInfo);
        }
    }
    return ansList;
    }


}
