package spark.smartonlinecourse.dao;

import spark.smartonlinecourse.entity.User;

public interface UserMapper {
    User selectByEmail(String email);
    Integer updateUser(User user);
    Integer insertUser(User user);
}