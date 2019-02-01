package spark.course.web.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spark.course.api.FeignChooseCourseApi;
import spark.course.api.FeignSignApi;
import spark.course.api.FeignUserApi;
import spark.course.constants.CommonConstants;
import spark.course.controller.BaseController;
import spark.course.entity.bo.CourseBO;
import spark.course.entity.bo.SignBO;
import spark.course.entity.bo.UserBO;
import spark.course.entity.vo.SignVO;
import spark.course.error.BusinessException;
import spark.course.util.DateUtil;
import spark.course.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SignController
 * @Description TODO
 * @Author Spark
 * @Date 2/1/2019 11:54 AM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/sign")
public class SignController extends BaseController {
    @Autowired
    FeignChooseCourseApi chooseCourseService;
    @Autowired
    FeignSignApi signService;
    @Autowired
    FeignUserApi userService;

    @GetMapping(value = "/{signId:\\d+}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectBySignId(@PathVariable(value = "signId") Integer signId) {
        return JsonUtil.convertToJson(convertFromBO(JsonUtil.json2Bean(signService.
                selectBySignId(signId), SignBO.class)));
    }

    @GetMapping(value = "/{chooseCourseId}/{start}/{size}", consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String selectByChooseCoureId(@PathVariable("chooseCourseId") Integer chooseCourseId,
                                 @PathVariable("start") Integer start,
                                 @PathVariable("size") Integer size) {
        return JsonUtil.convertToJson(convertFromBOListJson(signService.
        selectByChooseCoureId(chooseCourseId, start, size)));
    }

    @PostMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String insert(@RequestBody SignVO signVO) {
        return JsonUtil.convertToJson(convertFromBO(JsonUtil.json2Bean(signService.
                insert(convertToBO(signVO)), SignBO.class)));
    }

    @DeleteMapping(value = "/{signId}")
    void delete(@PathVariable(value = "signId") Integer signId) {
        signService.delete(signId);
    }

    @PutMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    String update(@RequestBody SignVO signVO) throws BusinessException {
        return JsonUtil.convertToJson(convertFromBO(JsonUtil.json2Bean(signService.
                update(convertToBO(signVO)), SignBO.class)));
    }

    private SignVO convertFromBO(SignBO signBO) {
        if (signBO == null) {
            return null;
        }
        SignVO signVO = new SignVO();
        BeanUtils.copyProperties(signBO, signVO);
        signVO.setEndTime(DateUtil.convertFromLocalDateTime(signBO.getEndTime()));
        signVO.setSignTime(DateUtil.convertFromLocalDateTime(signBO.getSignTime()));
        //get the CourseBO
        CourseBO courseBO = JsonUtil.json2Bean(chooseCourseService.selectByChooseCourseId(signBO
                .getChooseCourseId()), CourseBO.class);
        signVO.setUserId(courseBO.getUserId());
        signVO.setCourseId(courseBO.getCourseId());
        UserBO userBO = JsonUtil.json2Bean(userService.
                selectByUserId(courseBO.getUserId()), UserBO.class);
        signVO.setUsername(userBO.getUsername());
        return signVO;
    }

    private SignBO convertToBO(SignVO signVO) {
        if (signVO == null) {
            return null;
        }
        SignBO signBO = new SignBO();
        BeanUtils.copyProperties(signVO, signBO);
        if (signVO.getEndTime() != null) {
            signBO.setEndTime(DateUtil.convertFromString(signVO.getEndTime()));
        }
        if (signVO.getSignTime() != null) {
            signBO.setSignTime(DateUtil.convertFromString(signVO.getSignTime()));
        }
        return signBO;
    }

    private List<SignVO> convertFromBOListJson(String signBOListJson) {
        List<SignVO> signVOList = new ArrayList<SignVO>();
        List<SignBO> signBOList = JsonUtil.json2List(signBOListJson, SignBO.class);
        for (SignBO signBO:signBOList) {
            signVOList.add(convertFromBO(signBO));
        }
        return signVOList;
    }
}
