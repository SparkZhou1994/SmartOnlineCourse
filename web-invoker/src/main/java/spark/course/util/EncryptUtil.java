package spark.course.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @ClassName EncryptUtil
 * @Description TODO
 * @Author Spark
 * @Date 3/5/2019 1:15 PM
 * @Version 1.0
 **/
public class EncryptUtil {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encrypt(String rawPassword) {
        return encoder.encode(rawPassword.trim());
    }

    public static boolean match(String rawPassword, String password) {
        return encoder.matches(rawPassword, password);
    }
}
