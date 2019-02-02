package spark.course.web.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spark.course.api.FeignChooseCourseApi;
import spark.course.api.FeignHomeworkApi;
import spark.course.api.FeignSignApi;
import spark.course.api.FeignUserApi;
import spark.course.constants.CommonConstants;
import spark.course.controller.BaseController;
import spark.course.entity.bo.CourseBO;
import spark.course.entity.bo.HomeworkBO;
import spark.course.entity.bo.SignBO;
import spark.course.entity.bo.UserBO;
import spark.course.entity.vo.HomeworkVO;
import spark.course.entity.vo.SignVO;
import spark.course.error.BusinessException;
import spark.course.util.DateUtil;
import spark.course.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName HomeworkController
 * @Description TODO
 * @Author Spark
 * @Date 2/3/2019 1:45 AM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/homework")
public class HomeworkController extends BaseController {
    @Autowired
    FeignChooseCourseApi chooseCourseService;
    @Autowired
    FeignHomeworkApi homeworkService;
    @Autowired
    FeignUserApi userService;
    @GetMapping(value = "/{homeworkId:\\d+}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectByHomeworkId(@PathVariable("homeworkId") Integer homeworkId) {
        return JsonUtil.convertToJson(convertFromBO(JsonUtil.json2Bean(homeworkService.
                selectByHomeworkId(homeworkId),HomeworkBO.class)));
    }

    @GetMapping(value = "/{chooseCourseId:\\d+}/{start:\\d+}/{size:\\d+}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectByChooseCourseId(Integer chooseCourseId, Integer start, Integer size) {
        return JsonUtil.convertToJson(convertFromBOListJson(homeworkService.
                selectByChooseCourseId(chooseCourseId, start, size)));
    }

    @PostMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String insert(@RequestBody HomeworkVO homeworkVO) {
        //get the courseId
        CourseBO courseBO = JsonUtil.json2Bean(chooseCourseService.
                selectByChooseCourseId(homeworkVO.getChooseCourseId()), CourseBO.class);
        //get the chooseCourseId list by courseId
        List<CourseBO> courseBOList = JsonUtil.json2List(chooseCourseService.
                selectByCourseId(courseBO.getCourseId()), CourseBO.class);
        List<HomeworkVO> homeworkVOList = new ArrayList<HomeworkVO>();
        for (CourseBO courseBOTemp:courseBOList) {
            homeworkVOList.add(convertFromBO(JsonUtil.json2Bean(homeworkService.
                    insert(convertToBO(homeworkVO)), HomeworkBO.class)));
        }
        return JsonUtil.convertToJson(homeworkVOList);
    }

    @DeleteMapping(value = "/{homeworkId:\\d+}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    void delete(@PathVariable("homeworkId") Integer homeworkId){
        homeworkService.delete(homeworkId);
    }

    @PutMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String update(@RequestBody HomeworkVO homeworkVO) throws BusinessException {
        return JsonUtil.convertToJson(convertFromBO(JsonUtil.json2Bean(homeworkService.
                update(convertToBO(homeworkVO)), HomeworkBO.class)));
    }

    private HomeworkVO convertFromBO(HomeworkBO homeworkBO) {
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
        //get the CourseBO
        CourseBO courseBO = JsonUtil.json2Bean(chooseCourseService.selectByChooseCourseId(homeworkBO
                .getChooseCourseId()), CourseBO.class);
        homeworkVO.setUserId(courseBO.getUserId());
        homeworkVO.setCourseId(courseBO.getCourseId());
        UserBO userBO = JsonUtil.json2Bean(userService.
                selectByUserId(courseBO.getUserId()), UserBO.class);
        homeworkVO.setUsername(userBO.getUsername());
        switch (homeworkBO.getRange()) {
            case "0" : homeworkVO.setRange(CommonConstants.Homework.RANGE_0); break;
            case "1" : homeworkVO.setRange(CommonConstants.Homework.RANGE_1); break;
            default : homeworkVO.setRange(CommonConstants.Homework.RANGE_NULL); break;
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
        switch (homeworkBO.getRange()) {
            case CommonConstants.Homework.RANGE_0 : homeworkVO.setRange("0"); break;
            case CommonConstants.Homework.RANGE_1 : homeworkVO.setRange("1"); break;
            default : homeworkVO.setRange(null); break;
        }
        return homeworkBO;
    }

    private List<HomeworkVO> convertFromBOListJson(String homeworkBOListJson) {
        List<HomeworkVO> homeworkVOList = new ArrayList<HomeworkVO>();
        List<HomeworkBO> homeworkBOList = JsonUtil.json2List(homeworkBOListJson, HomeworkBO.class);
        for (HomeworkBO homeworkBO:homeworkBOList) {
            homeworkVOList.add(convertFromBO(homeworkBO));
        }
        return homeworkVOList;
    }
}

