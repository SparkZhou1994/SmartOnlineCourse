package spark.smartonlinecourse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import spark.smartonlinecourse.dao.UserMapper;
import spark.smartonlinecourse.entity.User;
import spark.smartonlinecourse.service.UserService;
import spark.smartonlinecourse.util.FileUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

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
    @Transactional
    @Override
    public User updateUserInfo(User user, MultipartFile file, HttpServletRequest request) {
        if(!file.isEmpty()) {
            String fileName = null;
            if ("/image/user/default.png".equals(user.getAvatar())) {
                fileName = user.getUserName() + "_" + UUID.randomUUID() + "_" + file.getOriginalFilename();
            } else {
                fileName = user.getAvatar();
            }
            String filePath="E:/SmartOnlineCourse/user/";
            try{
            FileUtil.uploadFile(file, filePath, fileName);
            user.setAvatar(fileName);
            }catch (Exception e){

            }
        }
        int result=-1;
        result=userMapper.updateUser(user);
        if(result==1){
            return user;
        }
        return null;
    }
}
