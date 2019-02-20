package spark.course.web.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spark.course.api.FeignDiscussContentApi;
import spark.course.api.FeignUserApi;
import spark.course.constants.CommonConstants;
import spark.course.controller.BaseController;
import spark.course.entity.bo.CourseBO;
import spark.course.entity.bo.DiscussBO;
import spark.course.entity.bo.DiscussContentBO;
import spark.course.entity.bo.UserBO;
import spark.course.entity.vo.DiscussContentVO;
import spark.course.entity.vo.DiscussVO;
import spark.course.error.BusinessException;
import spark.course.util.DateUtil;
import spark.course.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DiscussContentController
 * @Description TODO
 * @Author Spark
 * @Date 1/31/2019 4:06 PM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/discussContent")
public class DiscussContentController extends BaseController {
    @Autowired
    FeignDiscussContentApi discussContentService;
    @Autowired
    FeignUserApi userService;

    @GetMapping(value = "/{discussId:\\d+}/{start:\\d+}/{size:\\d+}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    public String selectByDiscussId(@PathVariable("discussId") Integer discussId,
                                    @PathVariable("start") Integer start,
                                    @PathVariable("size") Integer size) throws BusinessException {
        return JsonUtil.convertToJson(convertFromBOListJson(discussContentService.selectByDiscussId(discussId, start, size)));
    }

    @GetMapping(value = "/{discussContentId:\\d+}")
    public String selectByPrimaryKey(@PathVariable(value = "discussContentId") Integer discussContentId) throws BusinessException {
        return JsonUtil.convertToJson(convertFromBO(JsonUtil.json2Bean(discussContentService.
                selectByPrimaryKey(discussContentId), DiscussContentBO.class)));
    }

    @PostMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    public String insert(@RequestBody DiscussContentVO discussContentVO) throws BusinessException {
        return JsonUtil.convertToJson(convertFromBO(JsonUtil.json2Bean(discussContentService.
                insert(convertToBo(discussContentVO)), DiscussContentBO.class)));
    }

    @DeleteMapping(value = "/{discussContentId:\\d+}",
            consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    public void delete(@PathVariable(value = "discussContentId") Integer discussContentId) throws BusinessException {
        discussContentService.delete(discussContentId);
    }

    @PutMapping(consumes = CommonConstants.BaseController.CONTENT_TYPE_JSON)
    public String update(@RequestBody DiscussContentVO discussContentVO) throws BusinessException {
        return JsonUtil.convertToJson(convertFromBO(JsonUtil.json2Bean(discussContentService.
                update(convertToBo(discussContentVO)), DiscussContentBO.class)));
    }

    private DiscussContentVO convertFromBO(DiscussContentBO discussContentBO) throws BusinessException {
        if (discussContentBO == null) {
            return null;
        }
        DiscussContentVO discussContentVO = new DiscussContentVO();
        BeanUtils.copyProperties(discussContentBO, discussContentVO);
        discussContentVO.setPublishTime(DateUtil.
                convertFromLocalDateTime(discussContentBO.getPublishTime()));
        discussContentVO.setUsername(JsonUtil.json2Bean(userService.
                selectByUserId(discussContentBO.getUserId()),UserBO.class).getUsername());
        return discussContentVO;
    }

    private DiscussContentBO convertToBo(DiscussContentVO discussContentVO) {
        if (discussContentVO == null) {
            return null;
        }
        DiscussContentBO discussContentBO = new DiscussContentBO();
        BeanUtils.copyProperties(discussContentVO, discussContentBO);
        discussContentBO.setPublishTime(DateUtil.convertFromString(discussContentVO.
                getPublishTime()));
        return discussContentBO;
    }

    private List<DiscussContentVO> convertFromBOListJson(String discussContentBOlistJson) throws BusinessException {
        List<DiscussContentVO> discussContentVOList = new ArrayList<DiscussContentVO>();
        List<DiscussContentBO> discussContentBOList = JsonUtil.
                json2List(discussContentBOlistJson, DiscussContentBO.class);
        for (DiscussContentBO discussContentBO:discussContentBOList) {
            discussContentVOList.add(convertFromBO(discussContentBO));
        }
        return discussContentVOList;
    }
}
