package spark.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spark.course.api.IHomeworkApi;
import spark.course.entity.bo.HomeworkBO;
import spark.course.error.BusinessException;
import spark.course.service.HomeworkService;
import spark.course.util.JsonUtil;

/**
 * @ClassName HomeworkController
 * @Description TODO
 * @Author Spark
 * @Date 2/3/2019 1:32 AM
 * @Version 1.0
 **/
@RestController
public class HomeworkController implements IHomeworkApi {
    @Autowired
    HomeworkService homeworkService;
    @Override
    public String selectByHomeworkId(@PathVariable("homeworkId") Integer homeworkId) {
        return JsonUtil.convertToJson(homeworkService.selectByHomeworkId(homeworkId));
    }

    @Override
    public String selectByChooseCourseId(@PathVariable("chooseCourseId") Integer chooseCourseId,
                                         @PathVariable("start") Integer start,
                                         @PathVariable("size") Integer size) {
        return JsonUtil.convertToJson(homeworkService.
                selectByChooseCourseId(chooseCourseId, start, size));
    }

    @Override
    public String insert(@RequestBody HomeworkBO homeworkBO) {
        return JsonUtil.convertToJson(homeworkService.insert(homeworkBO));
    }

    @Override
    public void delete(@PathVariable("homeworkId") Integer homeworkId) {
        homeworkService.delete(homeworkId);
    }

    @Override
    public String update(@RequestBody HomeworkBO homeworkBO) throws BusinessException {
        return JsonUtil.convertToJson(homeworkService.update(homeworkBO));
    }
}
