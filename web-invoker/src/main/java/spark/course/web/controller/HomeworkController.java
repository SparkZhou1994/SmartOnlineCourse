package spark.course.web.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spark.course.api.FeignChooseCourseApi;
import spark.course.api.FeignHomeworkApi;
import spark.course.api.FeignUserApi;
import spark.course.constants.CommonConstants;
import spark.course.controller.BaseController;
import spark.course.entity.bo.CourseBO;
import spark.course.entity.bo.HomeworkBO;
import spark.course.entity.bo.UserBO;
import spark.course.entity.vo.HomeworkVO;
import spark.course.error.BusinessException;
import spark.course.error.ClassBusinessError;
import spark.course.error.EmBusinessError;
import spark.course.util.DateUtil;
import spark.course.util.FileUtil;
import spark.course.util.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName HomeworkController
 * @Description TODO
 * @Author Spark
 * @Date 2/3/2019 1:45 AM
 * @Version 1.0
 **/
@Controller
@RequestMapping("/homework")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class HomeworkController extends BaseController {
    @Autowired
    FeignChooseCourseApi chooseCourseService;
    @Autowired
    FeignHomeworkApi homeworkService;
    @Autowired
    FeignUserApi userService;

    @ResponseBody
    @GetMapping(value = "/{homeworkId:\\d+}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectByHomeworkId(@PathVariable("homeworkId") Integer homeworkId) throws BusinessException {
        return JsonUtil.convertToJson(convertFromBO(JsonUtil.json2Bean(homeworkService.
                selectByHomeworkId(homeworkId),HomeworkBO.class)));
    }

    @ResponseBody
    @GetMapping(value = "/{chooseCourseId:\\d+}/{start:\\d+}/{size:\\d+}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectByChooseCourseId(@PathVariable("chooseCourseId") Integer chooseCourseId,
                                  @PathVariable("start") Integer start,
                                  @PathVariable("size") Integer size) throws BusinessException {
        return JsonUtil.convertToJson(convertFromBOListJson(homeworkService.
                selectByChooseCourseId(chooseCourseId, start, size)));
    }

    @ResponseBody
    @PostMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String insert(@RequestBody HomeworkVO homeworkVO) throws BusinessException {
        //get the courseId
        CourseBO courseBO = JsonUtil.json2Bean(chooseCourseService.
                selectByChooseCourseId(homeworkVO.getChooseCourseId()), CourseBO.class);
        //get the chooseCourseId list by courseId
        List<CourseBO> courseBOList = JsonUtil.json2List(chooseCourseService.
                selectByCourseId(courseBO.getCourseId()), CourseBO.class);
        List<HomeworkVO> homeworkVOList = new ArrayList<HomeworkVO>();
        for (CourseBO courseBOTemp:courseBOList) {
            String result = homeworkService.insert(convertToBO(homeworkVO));
            if (result.contains("true")) {
                throw new BusinessException(new ClassBusinessError(result));
            }
            homeworkVOList.add(convertFromBO(JsonUtil.json2Bean(result, HomeworkBO.class)));
        }
        return JsonUtil.convertToJson(homeworkVOList);
    }

    @PostMapping(value = "/homeworkUpload")
    String homeworkUpload(Integer chooseCourseId, String describe, String title,
                          MultipartFile attachmentFile,String endTime,
                          Integer batch) throws Exception {
        String fileName = FileUtil.fileNameConvert(attachmentFile);
        try {
            FileUtil.uploadFile(attachmentFile, CommonConstants.Homework.FILE_PATH, fileName);
        } catch (Exception e) {
            throw new BusinessException(EmBusinessError.HOMEWORK_UPLOAD_ERROR);
        }
        HomeworkVO homeworkVO = new HomeworkVO();
        homeworkVO.setChooseCourseId(chooseCourseId);
        homeworkVO.setDescribe(describe);
        homeworkVO.setTitle(title);
        homeworkVO.setAttachment(fileName);
        homeworkVO.setEndTime(endTime);
        homeworkVO.setBatch(batch);
        return insert(homeworkVO);
    }

    @GetMapping(value = "/homeworkDownload/{homeworkId}")
    ResponseEntity<byte[]> homeworkDownload(@PathVariable("homeworkId") Integer homeworkId,
                                            HttpServletRequest request) throws BusinessException {
        HomeworkBO homeworkBO = JsonUtil.json2Bean(homeworkService.
                selectByHomeworkId(homeworkId),HomeworkBO.class);
        return FileUtil.downloadFile(homeworkBO.getAttachment(),
                CommonConstants.Homework.FILE_PATH , request);
    }

    @ResponseBody
    @DeleteMapping(value = "/{homeworkId:\\d+}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    void delete(@PathVariable("homeworkId") Integer homeworkId) throws BusinessException {
        homeworkService.delete(homeworkId);
    }

    @ResponseBody
    @PutMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String update(@RequestBody HomeworkVO homeworkVO) throws BusinessException {
        return JsonUtil.convertToJson(convertFromBO(JsonUtil.json2Bean(homeworkService.
                update(convertToBO(homeworkVO)), HomeworkBO.class)));
    }

    private HomeworkVO convertFromBO(HomeworkBO homeworkBO) throws BusinessException {
        if (homeworkBO == null) {
            return null;
        }
        HomeworkVO homeworkVO = new HomeworkVO();
        BeanUtils.copyProperties(homeworkBO, homeworkVO);
        if (homeworkBO.getEndTime() != null) {
            homeworkVO.setEndTime(DateUtil.convertFromLocalDateTime(homeworkBO.getEndTime()));
        }
        if (homeworkBO.getSubmitTime() != null) {
            homeworkVO.setEndTime(DateUtil.convertFromLocalDateTime(homeworkBO.getSubmitTime()));
        }
        if (homeworkBO.getChooseCourseId() != null) {
            //get the CourseBO
            CourseBO courseBO = JsonUtil.json2Bean(chooseCourseService.selectByChooseCourseId(homeworkBO
                    .getChooseCourseId()), CourseBO.class);
            homeworkVO.setUserId(courseBO.getUserId());
            homeworkVO.setCourseId(courseBO.getCourseId());
            UserBO userBO = JsonUtil.json2Bean(userService.
                    selectByUserId(courseBO.getUserId()), UserBO.class);
            homeworkVO.setUsername(userBO.getUsername());
        }
        if (homeworkBO.getRange() != null) {
            switch (homeworkBO.getRange()) {
                case "0":
                    homeworkVO.setRange(CommonConstants.Homework.RANGE_0);
                    break;
                case "1":
                    homeworkVO.setRange(CommonConstants.Homework.RANGE_1);
                    break;
                default:
                    homeworkVO.setRange(CommonConstants.Homework.RANGE_NULL);
                    break;
            }
        }
        return homeworkVO;
    }

    private HomeworkBO convertToBO(HomeworkVO homeworkVO) {
        if (homeworkVO == null) {
            return null;
        }
        HomeworkBO homeworkBO = new HomeworkBO();
        BeanUtils.copyProperties(homeworkVO, homeworkBO);
        if (homeworkVO.getEndTime() != null) {
            homeworkBO.setEndTime(DateUtil.convertFromString(homeworkVO.getEndTime()));
        }
        if (homeworkVO.getSubmitTime() != null) {
            homeworkBO.setEndTime(DateUtil.convertFromString(homeworkVO.getSubmitTime()));
        }
        if (homeworkBO.getRange() != null) {
            switch (homeworkBO.getRange()) {
                case CommonConstants.Homework.RANGE_0:
                    homeworkVO.setRange("0");
                    break;
                case CommonConstants.Homework.RANGE_1:
                    homeworkVO.setRange("1");
                    break;
                default:
                    homeworkVO.setRange(null);
                    break;
            }
        }
        return homeworkBO;
    }

    private List<HomeworkVO> convertFromBOListJson(String homeworkBOListJson) throws BusinessException {
        List<HomeworkVO> homeworkVOList = new ArrayList<HomeworkVO>();
        List<HomeworkBO> homeworkBOList = JsonUtil.json2List(homeworkBOListJson, HomeworkBO.class);
        for (HomeworkBO homeworkBO:homeworkBOList) {
            homeworkVOList.add(convertFromBO(homeworkBO));
        }
        return homeworkVOList;
    }
}

