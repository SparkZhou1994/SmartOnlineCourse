package spark.smartonlinecourse.dao;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import spark.smartonlinecourse.entity.Course;
import spark.smartonlinecourse.entity.Key;
import spark.smartonlinecourse.entity.Sign;

import java.time.LocalDateTime;
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

    @Ignore
    @Test
    public void courseTest(){
        Course course=courseMapper.selectCourseByCourseId(1);
        assertEquals(new Integer(2), course.getUserId());
    }

    @Autowired
    private ChooseCourseMapper chooseCourseMapper;

    @Ignore
    @Test
    public void chooseCourseTest(){
        Key key=new Key();
        key.setCourseId(1);
        List<Integer> chooseCourseIdList=chooseCourseMapper.selectChooseCourseId(key);
        for(Integer chooseCourseId : chooseCourseIdList){
            System.out.println("chooseCourseId:"+String.valueOf(chooseCourseId));
        }
    }

    @Autowired
    SignMapper signMapper;

    @Ignore
    @Test
    public void insertSignTest(){
        Sign sign=new Sign();
        sign.setChooseCourseId(1);
        sign.setBatch(1);
        sign.setCode("666666");
        //LocalDateTime dateTime=LocalDateTime.of(2018,8,21,13,26,20);
        LocalDateTime dateTime=LocalDateTime.now();
        LocalDateTime dateTimePlus=dateTime.plusSeconds(60*2);
        Boolean after=dateTimePlus.isAfter(dateTime);
        sign.setEndTime(dateTime);
        System.out.print(after.toString());
    }

    @Test
    public void selectSignTest(){
        Key key=new Key();
        key.setUserId(null);
        key.setCourseId(1);
        key.setStart(0);
        List<Sign> signList=signMapper.selectSignByCourseIdAndUserIdAndStart(key);
        for(Sign sign : signList){
            System.out.println(sign);
        }
    }
}
