package spark.course.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spark.course.dao.CourseDTOMapper;
import spark.course.entity.bo.CourseBO;
import spark.course.entity.dto.CourseDTO;
import spark.course.error.BusinessException;
import spark.course.error.EmBusinessError;
import spark.course.service.CourseService;
import spark.course.util.SendLogMessageUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CourseServiceImpl
 * @Description TODO
 * @Author Spark
 * @Date 1/28/2019 4:43 PM
 * @Version 1.0
 **/
@Service
public class CourseServiceImpl implements CourseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseServiceImpl.class);
    @Autowired
    CourseDTOMapper courseDTOMapper;
    @Autowired
    SendLogMessageUtil sendLogMessageUtil;
    @Override
    public CourseBO selectByCourseId(Integer courseId) {
        return convertFromDataObject(courseDTOMapper.selectByPrimaryKey(courseId));
    }

    @Override
    public List<CourseBO> selectCourseByCourseName(CourseBO courseBO) {
        List<CourseDTO> courseDTOList = courseDTOMapper.selectCourseByCourseName(
                courseBO.getCourseName(), courseBO.getStart(), courseBO.getSize());
        List<CourseBO> courseBOList = new ArrayList<CourseBO>();
        for (CourseDTO courseDTO : courseDTOList) {
            courseBOList.add(convertFromDataObject(courseDTO));
        }
        return courseBOList;
    }

    @Override
    public List<CourseBO> selectCourseSortByScore(CourseBO courseBO) {
        List<CourseDTO> courseDTOList = courseDTOMapper.selectCourseSortByScore(
                courseBO.getStart(), courseBO.getSize());
        List<CourseBO> courseBOList = new ArrayList<CourseBO>();
        for (CourseDTO courseDTO : courseDTOList) {
            courseBOList.add(convertFromDataObject(courseDTO));
        }
        return courseBOList;
    }

    @Override
    public CourseBO insert(CourseBO courseBO) {
        Integer courseId = courseDTOMapper.selectMaxCourseId();
        if (courseId == null) {
            courseId = 1;
        } else {
            courseId += 1;
        }
        courseBO.setCourseId(courseId);
        courseBO.setVersion(Long.parseLong(Integer.toString(0)));
        courseDTOMapper.insertSelective(convertToDataObject(courseBO));
        sendLogMessageUtil.sendInsertMessage(CourseBO.class, courseBO);
        return courseBO;
    }

    @Override
    public void deleteByCourseId(Integer courseId) {
        CourseDTO courseDTO = courseDTOMapper.selectByPrimaryKey(courseId);
        courseDTOMapper.deleteByPrimaryKey(courseId);
        sendLogMessageUtil.sendDeleteMessage(CourseBO.class, convertFromDataObject(courseDTO));
    }

    @Override
    public CourseBO updateByCourseId(CourseBO courseBO) throws BusinessException {
        Integer result = courseDTOMapper.updateByPrimaryKeyAndVersionSelective(
                convertToDataObject(courseBO));
        if (result !=1) {
            sendLogMessageUtil.sendErrorMessage(CourseBO.class,courseBO);
            throw new BusinessException(EmBusinessError.SERVER_BUSY);
        }
        courseBO.setVersion(courseBO.getVersion() + 1);
        sendLogMessageUtil.sendUpdateMessage(CourseBO.class,courseBO);
        return courseBO;
    }

    private CourseBO convertFromDataObject(CourseDTO courseDTO) {
        if (courseDTO == null) {
            return null;
        }
        CourseBO courseBO = new CourseBO();
        BeanUtils.copyProperties(courseDTO, courseBO);
        if (courseDTO.getAvgScore() != null) {
            courseBO.setAvgScore(Byte.toUnsignedInt(courseDTO.getAvgScore()));
        }
        return courseBO;
    }

    private CourseDTO convertToDataObject(CourseBO courseBO) {
        if (courseBO == null) {
            return null;
        }
        CourseDTO courseDTO = new CourseDTO();
        BeanUtils.copyProperties(courseBO, courseDTO);
        if (courseBO.getAvgScore() != null ){
            courseDTO.setAvgScore(courseBO.getAvgScore().byteValue());
        }
        return courseDTO;
    }
}
