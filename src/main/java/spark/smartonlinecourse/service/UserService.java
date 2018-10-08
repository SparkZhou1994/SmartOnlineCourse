package spark.smartonlinecourse.service;

import org.springframework.web.multipart.MultipartFile;
import spark.smartonlinecourse.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    User loginComfirm(User user);
    User updateUserInfo(User user, MultipartFile file, HttpServletRequest request);
    User selectByEmail(String email);
    Boolean signUp(User user);
}
