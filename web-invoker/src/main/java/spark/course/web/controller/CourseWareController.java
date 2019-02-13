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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spark.course.api.FeignCourseWareApi;
import spark.course.constants.CommonConstants;
import spark.course.controller.BaseController;
import spark.course.entity.bo.CourseWareBO;
import spark.course.entity.bo.HomeworkBO;
import spark.course.entity.vo.CourseWareVO;
import spark.course.entity.vo.HomeworkVO;
import spark.course.error.BusinessException;
import spark.course.error.ClassBusinessError;
import spark.course.error.EmBusinessError;
import spark.course.util.FileUtil;
import spark.course.util.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/courseWare")
public class CourseWareController extends BaseController {
    @Autowired
    FeignCourseWareApi courseWareService;

    @ResponseBody
    @GetMapping(value = "/{courseWareId:\\d+}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectByCourseWareId(@PathVariable("courseWareId") Integer courseWareId) {
        return JsonUtil.convertToJson(convertFromBO(JsonUtil.json2Bean(courseWareService.
                selectByCourseWareId(courseWareId), CourseWareBO.class)));
    }

    @ResponseBody
    @GetMapping(value = "/{courseId:\\d+}/{start:\\d+}/{size:\\d+}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectByCourseId(@PathVariable("courseId") Integer courseId,
                            @PathVariable("start") Integer start,
                            @PathVariable("size") Integer size) {
        return JsonUtil.convertToJson(convertFromBOListJson(courseWareService.
                selectByCourseId(courseId, start, size)));
    }

    @ResponseBody
    @PostMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String insert(@RequestBody CourseWareVO courseWareVO) throws BusinessException {
        String result = courseWareService.insert(convertToBO(courseWareVO));
        if (result.contains("true")) {
            throw new BusinessException(new ClassBusinessError(result));
        }
        return JsonUtil.convertToJson(convertFromBO(JsonUtil.json2Bean(result, CourseWareBO.class)));
    }

    @PostMapping(value = "/courseWareUpload")
    String courseWareUpload(Integer courseId, String title, MultipartFile attachmentFile,
                            Integer batch) throws Exception{
        String fileName = FileUtil.fileNameConvert(attachmentFile);
        try {
            FileUtil.uploadFile(attachmentFile, CommonConstants.CourseWare.FILE_PATH, fileName);
        } catch (Exception e) {
            throw new BusinessException(EmBusinessError.COURSE_WARE_UPLOAD_ERROR);
        }
        CourseWareVO courseWareVO = new CourseWareVO();
        courseWareVO.setCourseId(courseId);
        courseWareVO.setTitle(title);
        courseWareVO.setAttachment(fileName);
        courseWareVO.setBatch(batch);
        return insert(courseWareVO);
    }

    @GetMapping(value = "/courseWareDownload/{courseWareId}")
    ResponseEntity<byte[]> homeworkDownload(@PathVariable("courseWareId") Integer courseWareId,
                                            HttpServletRequest request){
        CourseWareBO courseWareBO = JsonUtil.json2Bean(courseWareService.
                selectByCourseWareId(courseWareId), CourseWareBO.class);
        return FileUtil.downloadFile(courseWareBO.getAttachment(),
                CommonConstants.Homework.FILE_PATH , request);
    }

    @ResponseBody
    @DeleteMapping(value = "/{courseWareId:\\d+}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    void delete(@PathVariable("courseWareId") Integer courseWareId){
        courseWareService.delete(courseWareId);
    }

    @ResponseBody
    @PutMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String update(@RequestBody CourseWareVO courseWareVO) throws BusinessException {
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
