package spark.course;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @ClassName CourseWareServerApplication
 * @Description TODO
 * @Author Spark
 * @Date 2/3/2019 6:24 PM
 * @Version 1.0
 **/
@SpringBootApplication(scanBasePackages = {"spark.course"})
@EnableEurekaClient
@MapperScan("spark.course.dao")
public class CourseWareServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CourseWareServerApplication.class, args);
    }
}
