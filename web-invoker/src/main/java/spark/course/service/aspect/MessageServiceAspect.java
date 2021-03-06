package spark.course.service.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import spark.course.api.FeignChooseCourseApi;
import spark.course.api.FeignCourseApi;
import spark.course.api.FeignMessageApi;
import spark.course.entity.bo.CourseBO;
import spark.course.entity.bo.MessageBO;
import spark.course.entity.vo.CourseWareVO;
import spark.course.entity.vo.HomeworkVO;
import spark.course.entity.vo.MessageVO;
import spark.course.error.BusinessException;
import spark.course.error.ClassBusinessError;
import spark.course.util.JsonUtil;
import spark.course.util.SendLogMessageUtil;

import java.util.List;

/**
 * @ClassName MessageServiceAspect
 * @Description TODO
 * @Author Spark
 * @Date 2/13/2019 9:16 AM
 * @Version 1.0
 **/
@Configuration
@Aspect
public class MessageServiceAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceAspect.class);
    @Autowired
    FeignMessageApi messageService;
    @Autowired
    FeignChooseCourseApi chooseCourseService;
    @Autowired
    FeignCourseApi courseService;
    @Autowired
    SendLogMessageUtil sendLogMessageUtil;
    private final String homeworkReleasePoint = "execution(* spark.course.web.controller.HomeworkController.insert(..))";
    private final String courseWareReleasePoint = "execution(* spark.course.web.controller.CourseWareController.insert(..))";

    @Pointcut(homeworkReleasePoint)
    public void homeworkRelease() {}

    @Pointcut(courseWareReleasePoint)
    public void courseWareRelease() {}

    @After("homeworkRelease()")
    public void doAfterHomeworkRelease(JoinPoint joinPoint) throws BusinessException {
        //get the param
        Object[] objects = joinPoint.getArgs();
        for (Object object:objects) {
            if (object instanceof HomeworkVO) {
                //get the courseId
                HomeworkVO homeworkVO = (HomeworkVO)object;
                CourseBO courseBO = JsonUtil.json2Bean(chooseCourseService.
                        selectByChooseCourseId(homeworkVO.getChooseCourseId()), CourseBO.class);
                //get the courseName
                CourseBO courseBOWithCourseDetail = JsonUtil.json2Bean(courseService.selectByCourseId(courseBO.getCourseId()), CourseBO.class);
                //get the chooseCourseId list by courseId
                List<CourseBO> courseBOList = JsonUtil.json2List(chooseCourseService.
                        selectByCourseId(courseBO.getCourseId()), CourseBO.class);
                for (CourseBO courseBOTemp:courseBOList) {
                    MessageVO messageVO = new MessageVO();
                    messageVO.setChooseCourseId(courseBOTemp.getChooseCourseId());
                    messageVO.setContent(courseBOWithCourseDetail.getCourseName() + ",有了一份新作业");
                    String result = messageService.insert(convertToBO(messageVO));
                    if (result.contains("true")) {
                        sendLogMessageUtil.sendErrorMessage(MessageBO.class, convertToBO(messageVO));
                        throw new BusinessException(new ClassBusinessError(result));
                    }
                }
            }
        }
    }

    @After("courseWareRelease()")
    public void doAfterCourseWareRelease(JoinPoint joinPoint) throws BusinessException {
        //get the param
        Object[] objects = joinPoint.getArgs();
        for (Object object:objects) {
            if (object instanceof CourseWareVO) {
                CourseWareVO courseWareVO = (CourseWareVO)object;
                //get the courseName
                CourseBO courseBOWithCourseDetail = JsonUtil.json2Bean(courseService.selectByCourseId(courseWareVO.getCourseId()), CourseBO.class);
                //get the chooseCourseId list by courseId
                List<CourseBO> courseBOList = JsonUtil.json2List(chooseCourseService.
                        selectByCourseId(courseWareVO.getCourseId()), CourseBO.class);
                for (CourseBO courseBO:courseBOList) {
                    MessageVO messageVO = new MessageVO();
                    messageVO.setChooseCourseId(courseBO.getChooseCourseId());
                    messageVO.setContent(courseBOWithCourseDetail.getCourseName() + ",有了一份新课件");
                    String result = messageService.insert(convertToBO(messageVO));
                    if (result.contains("true")) {
                        sendLogMessageUtil.sendErrorMessage(MessageBO.class, convertToBO(messageVO));
                        throw new BusinessException(new ClassBusinessError(result));
                    }
                }
            }
        }
    }

    private MessageBO convertToBO(MessageVO messageVO) {
        MessageBO messageBO = new MessageBO();
        BeanUtils.copyProperties(messageVO, messageBO);
        return messageBO;
    }
}