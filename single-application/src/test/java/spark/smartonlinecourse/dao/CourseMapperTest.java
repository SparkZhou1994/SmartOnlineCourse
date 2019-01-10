package spark.smartonlinecourse.dao;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spark.smartonlinecourse.entity.*;
import spark.smartonlinecourse.service.CourseWareService;
import spark.smartonlinecourse.service.SignService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private ChooseCourseMapper chooseCourseMapper;

    @Ignore
    @Test
    public void courseTest(){
        Integer avgScoreInt=chooseCourseMapper.selectAvgScore(1);
        Course course=new Course();
        course.setCourseId(1);
        course.setAvgScore((byte)avgScoreInt.intValue());
        Integer status=courseMapper.updateScore(course);
        assertEquals(new Integer(1), status);
    }
}
