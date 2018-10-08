package spark.smartonlinecourse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import spark.smartonlinecourse.dao.ChooseCourseMapper;
import spark.smartonlinecourse.dao.CourseMapper;
import spark.smartonlinecourse.entity.ChooseCourse;
import spark.smartonlinecourse.entity.Course;
import spark.smartonlinecourse.entity.Key;
import spark.smartonlinecourse.service.CourseService;
import spark.smartonlinecourse.util.FileUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName CourseServiceImpl
 * @Description TODO
 * @Author Spark
 * @Date 2018/6/14 22:22
 * @Version 1.0
 **/
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ChooseCourseMapper chooseCourseMapper;

    @Override
    public List<Course> selectTop(Integer start) {
        List<Course> courseList=courseMapper.selectTop(start);
        return courseList;
    }

    @Transactional
    @Override
    public Course insertCourse(Course course, MultipartFile file, HttpServletRequest request,Integer userId) {
        if(!file.isEmpty()) {
            String fileName = course.getCourseName() + "_" + UUID.randomUUID() + "_" + file.getOriginalFilename();
            String filePath="E:/SmartOnlineCourse/course/";
            try{
                FileUtil.uploadFile(file, filePath, fileName);
                course.setAvatar(fileName);
            }catch (Exception e){

            }
        }
        int result=-1;
        result=courseMapper.insertCourse(course);
        if(result==1){
            Key key=new Key();
            key.setUserId(userId);
            key.setCourseName(course.getCourseName());
            int courseId=courseMapper.selectCoursIdByCourseNameAndUserId(key);
            key.setCourseId(courseId);
            int status=chooseCourseMapper.insertChooseCourse(key);
            if(status==1){
                return course;
            }else{
                return null;
            }
        }
        return null;
    }

    @Override
    public Course selectCourseByCourseId(Integer courseId) {
        return courseMapper.selectCourseByCourseId(courseId);
    }

    @Override
    public Boolean ownCourse(Integer courseId, Integer userId) {
        Course course=courseMapper.selectCourseByCourseId(courseId);
        if(course.getUserId()==userId){
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateScore(ChooseCourse chooseCourse) {
        Integer status=chooseCourseMapper.updateScore(chooseCourse);
        if(status==1){
            Integer avgScoreInt=chooseCourseMapper.selectAvgScore(chooseCourse.getCourseId());
            if(avgScoreInt!=null){
                Course course = new Course();
                course.setAvgScore((byte)avgScoreInt.intValue());
                course.setCourseId(chooseCourse.getCourseId());
                status=courseMapper.updateScore(course);
                if(status==1){
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public List<Course> selectCourseByCourseName(String courseName) {
        return courseMapper.selectCourseByCourseName(courseName);
    }
}
