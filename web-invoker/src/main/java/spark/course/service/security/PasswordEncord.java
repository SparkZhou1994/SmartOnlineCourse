package spark.course.service.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @ClassName PasswordEncord
 * @Description TODO
 * @Author Spark
 * @Date 3/4/2019 5:40 PM
 * @Version 1.0
 **/
public class PasswordEncord {
    public static String passwordEncord(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordEncord=encoder.encode(password.trim());
        return passwordEncord;
    }
}
