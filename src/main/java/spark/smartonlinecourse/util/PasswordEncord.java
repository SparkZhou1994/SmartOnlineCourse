package spark.smartonlinecourse.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @ClassName PasswordEncord
 * @Description TODO
 * @Author Spark
 * @Date 2018/9/30 10:27
 * @Version 1.0
 **/
public class PasswordEncord {

    public static String passwordEncord(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordEncord=encoder.encode(password.trim());
        return passwordEncord;
    }
}
