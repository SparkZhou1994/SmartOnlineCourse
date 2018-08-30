package spark.smartonlinecourse.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import spark.smartonlinecourse.entity.Homework;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @ClassName HomeworkService
 * @Description TODO
 * @Author Spark
 * @Date 2018/8/30 11:42
 * @Version 1.0
 **/
public interface HomeworkService {
    Boolean homeworkRelease(String title, String describe, MultipartFile file, Integer courseId, String endTime);
    ResponseEntity<byte[]> homeworkDownload(HttpServletRequest request, Integer homeworkId);
    Boolean homeworkUpload(Integer homeworkId,MultipartFile file);
    Boolean homeworkEvaluate(Integer homeworkId,Integer score);
}
