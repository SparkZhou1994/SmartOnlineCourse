package spark.smartonlinecourse.util;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName PasswordTest
 * @Description TODO
 * @Author Spark
 * @Date 2018/10/8 15:13
 * @Version 1.0
 **/
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 按方法名大小升序执行
public class PasswordTest {
    @Test
    public void passwordTest(){
        String password=PasswordEncord.passwordEncord("123");
    }
}
