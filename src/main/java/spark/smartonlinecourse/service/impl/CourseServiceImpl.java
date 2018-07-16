package spark.smartonlinecourse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import spark.smartonlinecourse.dao.CourseMapper;
import spark.smartonlinecourse.entity.Course;
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

    @Override
    public List<Course> selectTop(Integer start) {
        List<Course> courseList=courseMapper.selectTop(start);
        return courseList;
    }

    @Transactional
    @Override
    public Course insertCourse(Course course, MultipartFile file, HttpServletRequest request) {
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
            return course;
        }
        return null;
    }
}
