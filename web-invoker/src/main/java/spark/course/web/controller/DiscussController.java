package spark.course.web.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spark.course.api.FeignChooseCourseApi;
import spark.course.api.FeignDiscussApi;
import spark.course.api.FeignUserApi;
import spark.course.constants.CommonConstants;
import spark.course.controller.BaseController;
import spark.course.entity.bo.CourseBO;
import spark.course.entity.bo.DiscussBO;
import spark.course.entity.bo.UserBO;
import spark.course.entity.vo.DiscussVO;
import spark.course.error.BusinessException;
import spark.course.error.ClassBusinessError;
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
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class DiscussController extends BaseController {
    @Autowired
    FeignUserApi userService;
    @Autowired
    FeignChooseCourseApi chooseCourseService;
    @Autowired
    FeignDiscussApi discussService;
    @GetMapping(value = "/{chooseCourseId:\\d+}/{start:\\d+}/{size:\\d+}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    public String selectByChooseCourseId(@PathVariable("chooseCourseId") Integer chooseCourseId,
                                         @PathVariable("start") Integer start,
                                         @PathVariable("size") Integer size) throws BusinessException {
        List<DiscussVO> discussVOList = new ArrayList<DiscussVO>();
        //get the courseId
        CourseBO courseBO = JsonUtil.json2Bean(chooseCourseService.
                selectByChooseCourseId(chooseCourseId),CourseBO.class);
        //get the chooseCourseId List
        List<CourseBO> courseBOList = JsonUtil.json2List(chooseCourseService.
                selectByCourseId(courseBO.getCourseId()),CourseBO.class);
        for (CourseBO courseBOTemp:courseBOList) {
            DiscussBO discussBO = new DiscussBO();
            discussBO.setChooseCourseId(courseBOTemp.getChooseCourseId());
            //get the discussBO list
            discussVOList.addAll(convertFromBOListJson(discussService.
                    selectByChooseCourseId(discussBO.getChooseCourseId())));
        }
        //get the subList
        return JsonUtil.convertToJson(discussVOList.subList(start, size+start));
    }

    @GetMapping(value = "/{discussId:\\d+}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    public String selectByDiscussId(@PathVariable("discussId") Integer discussId) throws BusinessException {
        return JsonUtil.convertToJson(convertFromBO(JsonUtil.json2Bean(discussService.
                selectByDiscussId(discussId),DiscussBO.class)));
    }

    @PostMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    public String insert(@RequestBody DiscussVO discussVO) throws BusinessException {
        String result = discussService.insert(convertToBO(discussVO));
        if (result.contains("true")) {
            throw new BusinessException(new ClassBusinessError(result));
        }
        return JsonUtil.convertToJson(convertFromBO(JsonUtil.json2Bean(result, DiscussBO.class)));
    }

    @DeleteMapping(value = "/{discussId:\\d+",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    public void delete(@PathVariable("discussId") Integer discussId)throws BusinessException {
        discussService.delete(discussId);
    }

    @PutMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    public String update(@RequestBody DiscussVO discussVO) throws BusinessException {
        return JsonUtil.convertToJson(convertFromBO(JsonUtil.json2Bean(discussService.
                update(convertToBO(discussVO)),DiscussBO.class)));
    }

    private DiscussVO convertFromBO(DiscussBO discussBO) throws BusinessException {
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
        switch (discussBO.getVote()) {
            case "0" : discussVO.setVote(CommonConstants.Discuss.VOTE_0); break;
            case "1" : discussVO.setVote(CommonConstants.Discuss.VOTE_1); break;
        }
        return discussVO;
    }

    private DiscussBO convertToBO(DiscussVO discussVO) throws BusinessException {
        if (discussVO == null) {
            return null;
        }
        DiscussBO discussBO = new DiscussBO();
        BeanUtils.copyProperties(discussVO, discussBO);
        if (discussVO.getLastPublishTime() != null) {
            discussBO.setLastPublishTime(DateUtil.convertFromString(discussVO.
                    getLastPublishTime()));
        }
        switch (discussVO.getVote()) {
            case CommonConstants.Discuss.VOTE_0 : discussBO.setVote("0"); break;
            case CommonConstants.Discuss.VOTE_1 : discussBO.setVote("1"); break;
        }
        return discussBO;
    }

    private List<DiscussVO> convertFromBOListJson(String discussBOListJson) throws BusinessException {
        List<DiscussVO> discussVOList = new ArrayList<DiscussVO>();
        List<DiscussBO> discussBOList = JsonUtil.json2List(discussBOListJson, DiscussBO.class);
        for (DiscussBO discussBO:discussBOList) {
            discussVOList.add(convertFromBO(discussBO));
        }
        return discussVOList;
    }
}
