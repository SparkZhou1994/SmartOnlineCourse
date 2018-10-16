package spark.smartonlinecourse.service.impl;

import com.google.gson.Gson;
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
import spark.smartonlinecourse.entity.Page;
import spark.smartonlinecourse.service.CourseService;
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

    @Autowired
    CourseService courseService;

    @Override
    public Boolean homeworkRelease(String title, String describe, MultipartFile file,
                                   Integer courseId,String endTimeString,Integer batch,Integer userId) {
        List<Homework> homeworkList=new ArrayList<Homework>();
        Key key=new Key();
        key.setCourseId(courseId);
        Course course =courseMapper.selectCourseByCourseId(courseId);
        List<Integer> chooseCourseIdList=new ArrayList<Integer>();
        List<Integer> chooseCourseIdListTemp=chooseCourseMapper.selectChooseCourseId(key);
        key.setUserId(userId);
        List<Integer> chooseCourseIdListOwn=chooseCourseMapper.selectChooseCourseId(key);
        Integer chooseCourseIdOwn=chooseCourseIdListOwn.get(0);
        for(Integer chooseCourseId:chooseCourseIdListTemp){
            if(chooseCourseId==chooseCourseIdOwn){
                continue;
            }else{
                chooseCourseIdList.add(chooseCourseId);
            }
        }
        Integer count=chooseCourseIdList.size();
        String fileName=null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime endTime = LocalDateTime.parse(endTimeString,dateTimeFormatter);
        if(!file.isEmpty()) {
            fileName =course.getCourseName() + "_" + UUID.randomUUID() + "_" + file.getOriginalFilename();
            String filePath="E:/SmartOnlineCourse/homework/";
            try{
                FileUtil.uploadFile(file, filePath, fileName);
            }catch (Exception e){
                return false;
            }
        }else{
            return false;
        }
        for(Integer chooseCourseId:chooseCourseIdList){
            Homework homework=new Homework();
            homework.setTitle(title);
            homework.setDescribe(describe);
            homework.setBatch(batch);
            homework.setEndTime(endTime);
            homework.setChooseCourseId(chooseCourseId);
            homework.setAttachment(fileName);
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
    public Boolean homeworkUpload(Integer homeworkId, MultipartFile file,Integer courseId) {
        Key key=new Key();
        key.setHomeworkId(homeworkId);
        List<Homework> homework=homeworkMapper.selectHomework(key);
        if(homework.size()!=1){
            return null;
        }else {
            if (!file.isEmpty()) {
                Course course=courseMapper.selectCourseByCourseId(courseId);
                String fileName = course.getCourseName() + "_" + UUID.randomUUID() + "_" + file.getOriginalFilename();
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

    @Override
    public String homeworkListToJson(Integer courseId, Integer userId, Integer page, Integer row) {
        Boolean ownFlag=courseService.ownCourse(courseId,userId);
        Key key=new Key();
        key.setStart((page-1)*row);
        key.setRow(row);
        List<Homework> homeworkList=new ArrayList<Homework>();
        Integer total;
        Integer homeworkCount;
        if(ownFlag){
            key.setCourseId(courseId);
            homeworkList=homeworkMapper.selectHomework(key);
        }else{
            key.setCourseId(courseId);
            key.setUserId(userId);
            homeworkList=homeworkMapper.selectHomework(key);
        }
        for(Homework homework:homeworkList){
            if(homework.getSubmitTime()==null){
                homework.setSubmitTimeString("未设置");
            }else{
                homework.setSubmitTimeString(homework.getSubmitTime().toString());
            }
            if(homework.getEndTime()==null){
                homework.setEndTimeString("未设置");
            }else{
                homework.setEndTimeString(homework.getEndTime().toString());
            }
            if(homework.getRange()==null){
                homework.setStatus("未提交");
            }else{
                switch (homework.getRange()){
                    case '0':homework.setStatus("超时提交");break;
                    case '1':homework.setStatus("按时提交");break;
                    default:homework.setStatus("未提交");break;
                }
            }
            homework.setRange(null);
        }
        homeworkCount=homeworkList.size();
        total=(int) Math.ceil((double)homeworkCount/row);
        Page pageEntity=new Page();
        pageEntity.setPage(page);
        pageEntity.setRecords(homeworkCount);
        pageEntity.setData(homeworkList);
        pageEntity.setTotal(total);
        pageEntity.setRows(row);
        Gson json =new Gson();
        String jsonString=json.toJson(pageEntity);
        return jsonString;
    }
}
