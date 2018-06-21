package spark.smartonlinecourse.service;

import spark.smartonlinecourse.entity.User;

public interface UserService {
    User loginComfirm(User user);
    User updateUser(User user);
}
