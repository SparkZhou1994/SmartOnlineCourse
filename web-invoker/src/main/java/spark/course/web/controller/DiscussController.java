package spark.course.web.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spark.course.api.FeignChooseCourseApi;
import spark.course.api.FeignDiscussApi;
import spark.course.api.FeignUserApi;
import spark.course.constants.CommonConstants;
import spark.course.entity.bo.CourseBO;
import spark.course.entity.bo.DiscussBO;
import spark.course.entity.bo.UserBO;
import spark.course.entity.vo.DiscussVO;
import spark.course.util.DateUtil;
import spark.course.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DiscussController
 * @Description TODO
 * @Author Spark
 * @Date 1/31/2019 4:06 PM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/discuss")
public class DiscussController {
    @Autowired
    FeignUserApi userService;
    @Autowired
    FeignChooseCourseApi chooseCourseService;
    @Autowired
    FeignDiscussApi discussService;
    @GetMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectByChooseCourseId(@RequestBody DiscussVO discussVO) {
        List<DiscussVO> discussVOList = new ArrayList<DiscussVO>();
        //get the courseId
        CourseBO courseBO = JsonUtil.json2Bean(chooseCourseService.selectByChooseCourseId(discussVO.getChooseCourseId()),CourseBO.class);
        //get the chooseCourseId List
        List<CourseBO> courseBOList = JsonUtil.json2List(chooseCourseService.selectByCourseId(courseBO.getCourseId()),CourseBO.class);
        for (CourseBO courseBOTemp:courseBOList) {
            DiscussBO discussBO = new DiscussBO();
            discussBO.setChooseCourseId(courseBOTemp.getChooseCourseId());
            //get the discussBO list
            discussVOList.addAll(convertFromBOListJson(discussService.selectByChooseCourseId(discussBO)));
        }
        //get the subList
        return JsonUtil.convertToJson(discussVOList.subList(discussVO.getStart(), discussVO.getSize()+discussVO.getStart()));
    }

    private DiscussVO convertFromBO(DiscussBO discussBO) {
        if (discussBO == null) {
            return null;
        }
        DiscussVO discussVO = new DiscussVO();
        BeanUtils.copyProperties(discussBO, discussVO);
        if (discussBO.getLastPublishTime() != null) {
            discussVO.setLastPublishTime(DateUtil.convertFromLocalDateTime(discussBO.
                    getLastPublishTime()));
        }
        if (discussBO.getChooseCourseId() != null) {
            CourseBO courseBO = JsonUtil.json2Bean(chooseCourseService.
                    selectByChooseCourseId(discussBO.getChooseCourseId()),CourseBO.class);
            discussVO.setUserId(courseBO.getUserId());
            discussVO.setCourseId(courseBO.getCourseId());
        }
        if (discussVO.getUserId() != null) {
            UserBO userBO = JsonUtil.json2Bean(userService.
                    selectByUserId(discussVO.getUserId()), UserBO.class);
            discussVO.setUsername(userBO.getUsername());
        }
        return discussVO;
    }

    private DiscussBO convertToBO(DiscussVO discussVO) {
        if (discussVO == null) {
            return null;
        }
        DiscussBO discussBO = new DiscussBO();
        BeanUtils.copyProperties(discussVO, discussBO);
        if (discussVO.getLastPublishTime() != null) {
            discussBO.setLastPublishTime(DateUtil.convertFromString(discussVO.
                    getLastPublishTime()));
        }
        return discussBO;
    }

    private List<DiscussVO> convertFromBOListJson(String discussBOListJson) {
        List<DiscussVO> discussVOList = new ArrayList<DiscussVO>();
        List<DiscussBO> discussBOList = JsonUtil.json2List(discussBOListJson, DiscussBO.class);
        for (DiscussBO discussBO:discussBOList) {
            discussVOList.add(convertFromBO(discussBO));
        }
        return discussVOList;
    }
}
