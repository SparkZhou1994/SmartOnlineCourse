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
import spark.smartonlinecourse.entity.Course;
import spark.smartonlinecourse.entity.Homework;
import spark.smartonlinecourse.entity.Key;
import spark.smartonlinecourse.entity.Sign;
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

    @Ignore
    @Test
    public void selectSignTest(){
        Key key=new Key();
        key.setUserId(null);
        key.setCourseId(1);
        key.setStart(0);
        key.setRow(10);
        List<Sign> signList=signMapper.selectSignByCourseIdAndUserIdAndStartAndRow(key);
        for(Sign sign : signList){
            System.out.println(sign);
        }
    }

    @Autowired
    SignService signService;

    @Ignore
    @Test
    public void signJsonTest(){
        System.out.print(signService.signListToJson(1,1,1,10,false));
    }

    @Autowired
    CourseWareService courseWareService;

    @Ignore
    @Test
    public void courseWare(){
        String json=courseWareService.courseWareListToJson(1, 1, 10);
        System.out.print(json);
    }

    @Autowired
    HomeworkMapper homeworkMapper;

    @Ignore
    @Test
    public void homeworkTest(){
        List<Homework> homeworkList=new ArrayList<Homework>();
        Homework homework=new Homework();
        homework.setChooseCourseId(1);
        homework.setAttachment("1");
        homework.setBatch(1);
        homework.setDescribe("1");
        homework.setEndTime(LocalDateTime.now());
        homework.setTitle("1");
        homeworkList.add(homework);
        homeworkList.add(homework);
        Integer resutl=homeworkMapper.insertHomework(homeworkList);
    }

    @Ignore
    @Test
    public void homeworkUpdateTest(){
        Homework homework=new Homework();
        homework.setHomeworkId(13);
        homework.setSubmitTime(LocalDateTime.now());
        homeworkMapper.updateHomework(homework);
    }

    @Ignore
    @Test
    public void homeworkUpdate2Test(){
        Homework homework=new Homework();
        homework.setHomeworkId(13);
        homework.setScore(100);
        homeworkMapper.updateHomework(homework);
    }
}
