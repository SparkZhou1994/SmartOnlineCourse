package spark.smartonlinecourse.service.impl;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spark.smartonlinecourse.dao.CourseMapper;
import spark.smartonlinecourse.dao.CourseWareMapper;
import spark.smartonlinecourse.entity.Course;
import spark.smartonlinecourse.entity.CourseWare;
import spark.smartonlinecourse.entity.Key;
import spark.smartonlinecourse.entity.Page;
import spark.smartonlinecourse.service.CourseWareService;
import spark.smartonlinecourse.util.FileUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName CourseWareServiceImpl
 * @Description TODO
 * @Author Spark
 * @Date 2018/8/25 18:13
 * @Version 1.0
 **/
@Service
public class CourseWareServiceImpl implements CourseWareService {
    @Autowired
    CourseWareMapper courseWareMapper;

    @Autowired
    CourseMapper courseMapper;

    @Override
    public String courseWareListToJson(Integer courseId, Integer page, Integer row) {
        Key key=new Key();
        key.setCourseId(courseId);
        key.setStart((page-1)*row);
        key.setRow(row);
        List<CourseWare> courseWareList=courseWareMapper.selectByCourseIdAndStartAndRow(key);
        Integer records=courseWareMapper.selectCountByCourseId(courseId);
        Integer total=(int) Math.ceil((double)records/row);
        Page pageEntity=new Page();
        pageEntity.setPage(page);
        pageEntity.setRows(row);
        pageEntity.setTotal(total);
        pageEntity.setRecords(records);
        pageEntity.setData(courseWareList);
        Gson json =new Gson();
        String jsonString=json.toJson(pageEntity);
        return jsonString;
    }

    @Override
    public Boolean courseWareUpload(Integer courseId, String title, Integer batch, MultipartFile file) {
        Course course=courseMapper.selectCourseByCourseId(courseId);
        CourseWare courseWare=new CourseWare();
        courseWare.setCourseId(courseId);
        courseWare.setBatch(batch);
        courseWare.setTitle(title);
        if(!file.isEmpty()) {
            String fileName =course.getCourseName() + "_" + UUID.randomUUID() + "_" + file.getOriginalFilename();
            String filePath="E:/SmartOnlineCourse/courseware/";
            try{
                FileUtil.uploadFile(file, filePath, fileName);
                courseWare.setAttachment(fileName);
            }catch (Exception e){
                return false;
            }
        }else{
            return false;
        }
        int result=-1;
        result=courseWareMapper.insertCourseWare(courseWare);
        if(result==1){
            return true;
        }else{
            return  false;
        }
    }

    @Override
    public ResponseEntity<byte[]> courseWareDownload(HttpServletRequest request, Integer courseWareId) {
        CourseWare courseWare=courseWareMapper.selectByCourseWareId(courseWareId);
        String fileName=courseWare.getAttachment();
        String filePath="E://SmartOnlineCourse//courseware";
        ResponseEntity<byte[]> entity=FileUtil.downloadFile(fileName,filePath , request);
        return entity;
    }
}
