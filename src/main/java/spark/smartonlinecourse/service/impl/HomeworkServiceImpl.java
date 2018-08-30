package spark.smartonlinecourse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spark.smartonlinecourse.dao.ChooseCourseMapper;
import spark.smartonlinecourse.dao.CourseMapper;
import spark.smartonlinecourse.dao.HomeworkMapper;
import spark.smartonlinecourse.entity.Course;
import spark.smartonlinecourse.entity.Homework;
import spark.smartonlinecourse.entity.Key;
import spark.smartonlinecourse.service.HomeworkService;
import spark.smartonlinecourse.util.FileUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName HomeworkServiceImpl
 * @Description TODO
 * @Author Spark
 * @Date 2018/8/30 12:51
 * @Version 1.0
 **/
@Service
public class HomeworkServiceImpl implements HomeworkService {

    @Autowired
    ChooseCourseMapper chooseCourseMapper;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    HomeworkMapper homeworkMapper;

    @Override
    public Boolean homeworkRelease(String title, String describe, MultipartFile file, Integer courseId,String endTimeString) {
        List<Homework> homeworkList=new ArrayList<Homework>();
        Key key=new Key();
        key.setCourseId(courseId);
        Course course =courseMapper.selectCourseByCourseId(courseId);
        List<Integer> chooseCourseIdList=chooseCourseMapper.selectChooseCourseId(key);
        Integer count=chooseCourseIdList.size();
        Homework homework=new Homework();
        homework.setTitle(title);
        homework.setDescribe(describe);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm");
        LocalDateTime endTime = LocalDateTime.parse("2017-09-28 17:07:05",dateTimeFormatter);
        homework.setEndTime(endTime);
        if(!file.isEmpty()) {
            String fileName =course.getCourseName() + "_" + file.getOriginalFilename() + "_" + UUID.randomUUID();
            String filePath="E:/SmartOnlineCourse/homework/";
            try{
                FileUtil.uploadFile(file, filePath, fileName);
                homework.setAttachment(fileName);
            }catch (Exception e){
                return false;
            }
        }else{
            return false;
        }
        for(Integer chooseCourseId:chooseCourseIdList){
            homework.setChooseCourseId(chooseCourseId);
            homeworkList.add(homework);
        }
        Integer result=homeworkMapper.insertHomework(homeworkList);
        if(result==count){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public ResponseEntity<byte[]> homeworkDownload(HttpServletRequest request, Integer homeworkId) {
        Key key=new Key();
        key.setHomeworkId(homeworkId);
        List<Homework> homework=homeworkMapper.selectHomework(key);
        if(homework.size()!=1){
            return null;
        }else{
            String fileName=homework.get(0).getAttachment();
            String filePath="E://SmartOnlineCourse//homework";
            ResponseEntity<byte[]> entity=FileUtil.downloadFile(fileName,filePath , request);
            return entity;
        }
    }

    @Override
    public Boolean homeworkUpload(Integer homeworkId, MultipartFile file) {
        Key key=new Key();
        key.setHomeworkId(homeworkId);
        List<Homework> homework=homeworkMapper.selectHomework(key);
        if(homework.size()!=1){
            return null;
        }else {
            if (!file.isEmpty()) {
                String fileName = homework.get(0).getHomeworkId() + "_" + file.getOriginalFilename() + "_" + UUID.randomUUID();
                String filePath = "E:/SmartOnlineCourse/homework/";
                try {
                    FileUtil.uploadFile(file, filePath, fileName);
                    homework.get(0).setAttachment(fileName);
                } catch (Exception e) {
                    return false;
                }
            } else {
                return false;
            }
            homework.get(0).setSubmitTime(LocalDateTime.now());
            Integer result=homeworkMapper.updateHomework(homework.get(0));
            if(result==1){
                return true;
            }else{
                return false;
            }
        }
    }

    @Override
    public Boolean homeworkEvaluate(Integer homeworkId, Integer score) {
        Homework homework=new Homework();
        homework.setHomeworkId(homeworkId);
        homework.setScore(score);
        Integer result=homeworkMapper.updateHomework(homework);
        if(result==1){
            return true;
        }else{
            return false;
        }
    }
}
