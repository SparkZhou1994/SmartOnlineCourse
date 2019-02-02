package spark.course;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName HomeworkServerApplication
 * @Description TODO
 * @Author Spark
 * @Date 2/2/2019 1:47 PM
 * @Version 1.0
 **/
@SpringBootApplication(scanBasePackages = {"spark.course"})
@EnableEurekaClient
@MapperScan("spark.course.dao")
public class HomeworkServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HomeworkServerApplication.class, args);
    }
}
