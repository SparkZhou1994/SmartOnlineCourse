package spark.course;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName CourseServerApplication
 * @Description TODO
 * @Author Spark
 * @Date 1/28/2019 4:39 PM
 * @Version 1.0
 **/
@SpringBootApplication(scanBasePackages = {"spark.course"})
@EnableEurekaClient
@MapperScan("spark.course.dao")
public class CourseServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CourseServerApplication.class, args);
    }
}
