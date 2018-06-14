package spark.smartonlinecourse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spark.smartonlinecourse.dao.UserMapper;
import spark.smartonlinecourse.entity.User;
import spark.smartonlinecourse.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User loginComfirm(User user) {
        String email=user.getEmail();
        User usersComfirm=userMapper.selectByEmail(email);
        if(user.getPassword().equals(usersComfirm.getPassword())) {
            return usersComfirm;
        }else{
            throw  new RuntimeException("用户密码错误!");
        }
    }
}
