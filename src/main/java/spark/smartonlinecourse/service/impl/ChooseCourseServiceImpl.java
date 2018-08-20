package spark.smartonlinecourse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spark.smartonlinecourse.dao.ChooseCourseMapper;
import spark.smartonlinecourse.dao.CourseMapper;
import spark.smartonlinecourse.entity.ChooseCourse;
import spark.smartonlinecourse.entity.Course;
import spark.smartonlinecourse.entity.Key;
import spark.smartonlinecourse.service.ChooseCourseService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class ChooseCourseServiceImpl implements ChooseCourseService {
    @Autowired
    private ChooseCourseMapper chooseCourseMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Map<String, Object> chooseCourseLoad(Integer userId,Integer start) {
        int chooseCourseCount=chooseCourseMapper.selectCountByUserId(userId);
        Key key=new Key();
        key.setUserId(userId);
        key.setStart(start);
        ArrayList<ChooseCourse> chooseCourseList =(ArrayList<ChooseCourse>) chooseCourseMapper.selectByUserId(key);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("chooseCourseList",chooseCourseList);
        map.put("chooseCourseCount",chooseCourseCount);
        return map;
    }

    @Override
    public Boolean joinCourse(Integer courseId,Integer userId) {
        Key key=new Key();
        key.setUserId(userId);
        key.setCourseId(courseId);
        Integer result=chooseCourseMapper.insertChooseCourse(key);
        if(result==1){
            return true;
        }else{
            throw  new RuntimeException("添加课程失败!");
        }
    }

    @Override
    public Boolean ownCourse(Integer courseId, Integer userId) {
        Course course=courseMapper.selectCourseByCourseId(courseId);
        if(course.getUserId()==userId){
            return true;
        }
        return false;
    }
}
