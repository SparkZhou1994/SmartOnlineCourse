package spark.smartonlinecourse.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @InterfaceName CourseWareService
 * @Description TODO
 * @Author Spark
 * @Date 2018/8/25 18:11
 * @Version 1.0
 **/
public interface CourseWareService {
    String courseWareListToJson(Integer courseId,Integer page,Integer row);
    Boolean courseWareUpload(Integer courseId, String title, Integer batch, MultipartFile file);
    ResponseEntity<byte[]> courseWareDownload(HttpServletRequest request,Integer courseWareId);
}
