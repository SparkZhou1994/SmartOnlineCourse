package spark.smartonlinecourse.dao;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spark.smartonlinecourse.entity.Course;

import static org.junit.Assert.assertEquals;

/**
 * @ClassName CourseMapperTest
 * @Description TODO
 * @Author Spark
 * @Date 2018/8/2 16:58
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING) // 按方法名大小升序执行
public class CourseMapperTest {
    @Autowired
    private CourseMapper courseMapper;

    @Test
    public void courseTest(){
        Course course=courseMapper.selectCourseByCourseId(1);
        assertEquals(new Integer(2), course.getUserId());
    }
}
