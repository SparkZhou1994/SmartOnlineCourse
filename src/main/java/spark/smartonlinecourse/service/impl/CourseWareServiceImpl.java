package spark.smartonlinecourse.service.impl;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import spark.smartonlinecourse.dao.CourseWareMapper;
import spark.smartonlinecourse.entity.CourseWare;
import spark.smartonlinecourse.entity.Key;
import spark.smartonlinecourse.service.CourseWareService;

/**
 * @ClassName CourseWareServiceImpl
 * @Description TODO
 * @Author Spark
 * @Date 2018/8/25 18:13
 * @Version 1.0
 **/
public class CourseWareServiceImpl implements CourseWareService {
    @Autowired
    CourseWareMapper courseWareMapper;
    @Override
    public String courseWareListToJson(Integer courseId, Integer page, Integer row) {
        Key key=new Key();
        key.setCourseId(courseId);
        key.setStart((page-1)*row);
        key.setRow(row);
        CourseWare courseWare=courseWareMapper.selectByCourseIdAndStartAndRow(key);
        Gson json =new Gson();
        String jsonString=json.toJson(courseWare);
        return jsonString;
    }
}
