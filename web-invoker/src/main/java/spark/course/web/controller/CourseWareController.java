package spark.course.web.controller;

/**
 * @ClassName CourseWareController
 * @Description TODO
 * @Author Spark
 * @Date 2/4/2019 10:17 AM
 * @Version 1.0
 **/
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spark.course.api.FeignCourseApi;
import spark.course.api.FeignCourseWareApi;
import spark.course.constants.CommonConstants;
import spark.course.controller.BaseController;
import spark.course.entity.bo.CourseWareBO;
import spark.course.entity.vo.CourseWareVO;
import spark.course.error.BusinessException;
import spark.course.util.JsonUtil;
import java.util.ArrayList;
import java.util.List;

@RestController

@RequestMapping("/courseWare")
public class CourseWareController extends BaseController {
    @Autowired
    FeignCourseWareApi courseWareService;

    @GetMapping(value = "/{courseWareId:\\d+}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectByCourseWareId(@PathVariable("courseWareId") Integer courseWareId) {
        return JsonUtil.convertToJson(convertFromBO(JsonUtil.json2Bean(courseWareService.
                selectByCourseWareId(courseWareId), CourseWareBO.class)));
    }

    @GetMapping(value = "/{courseId:\\d+}/{start\\d+}/{size\\d+}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectByCourseId(@PathVariable("courseId") Integer courseId,
                            @PathVariable("start") Integer start,
                            @PathVariable("size") Integer size){
        return JsonUtil.convertToJson(convertFromBO(JsonUtil.json2Bean(courseWareService.
                selectByCourseId(courseId, start, size), CourseWareBO.class)));
    }

    @PostMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String insert(@RequestBody CourseWareVO courseWareVO){
        return JsonUtil.convertToJson(convertFromBO(JsonUtil.json2Bean(courseWareService.
                insert(convertToBO(courseWareVO)), CourseWareBO.class)));
    }

    @DeleteMapping(value = "/{courseWareId:\\d+}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    void delete(@PathVariable("courseWareId") Integer courseWareId){
        courseWareService.delete(courseWareId);
    }

    @PutMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String update(@RequestBody CourseWareVO courseWareVO) throws BusinessException{
        return JsonUtil.convertToJson(convertFromBO(JsonUtil.json2Bean(courseWareService.
                update(convertToBO(courseWareVO)), CourseWareBO.class)));
    }

    private CourseWareVO convertFromBO(CourseWareBO courseWareBO) {
        CourseWareVO courseWareVO = new CourseWareVO();
        BeanUtils.copyProperties(courseWareBO, courseWareVO);
        return courseWareVO;
    }

    private CourseWareBO convertToBO(CourseWareVO courseWareVO) {
        CourseWareBO courseWareBO = new CourseWareBO();
        BeanUtils.copyProperties(courseWareVO, courseWareBO);
        return courseWareBO;
    }

    private List<CourseWareVO> convertFromBOListJson(String courseWareBOListJson) {
        List<CourseWareBO> courseWareBOList = JsonUtil.json2List(courseWareBOListJson, CourseWareBO.class);
        List<CourseWareVO> courseWareVOList = new ArrayList<CourseWareVO>();
        for (CourseWareBO courseWareBO:courseWareBOList) {
            courseWareVOList.add(convertFromBO(courseWareBO));
        }
        return courseWareVOList;
    }
}
