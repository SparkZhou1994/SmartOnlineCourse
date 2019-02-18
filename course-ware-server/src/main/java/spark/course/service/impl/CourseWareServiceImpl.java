package spark.course.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spark.course.dao.CourseWareDTOMapper;
import spark.course.entity.bo.CourseWareBO;
import spark.course.entity.dto.CourseWareDTO;
import spark.course.error.BusinessException;
import spark.course.error.EmBusinessError;
import spark.course.service.CourseWareService;
import spark.course.util.SendLogMessageUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CourseWareServiceImpl
 * @Description TODO
 * @Author Spark
 * @Date 2/3/2019 9:37 PM
 * @Version 1.0
 **/
@Service
public class CourseWareServiceImpl implements CourseWareService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseWareServiceImpl.class);
    @Autowired
    CourseWareDTOMapper courseWareDTOMapper;
    @Autowired
    SendLogMessageUtil sendLogMessageUtil;
    @Override
    public CourseWareBO selectByCourseWareId(Integer courseWareId) {
        return convertFromDataObject(courseWareDTOMapper.selectByPrimaryKey(courseWareId));
    }

    @Override
    public List<CourseWareBO> selectByCourseId(Integer courseId, Integer start, Integer size) {
        return convertFromDataObjectList(courseWareDTOMapper.
                selectByCourseId(courseId, start, size));
    }

    @Override
    public CourseWareBO insert(CourseWareBO courseWareBO) {
        Integer courseWareId = courseWareDTOMapper.selectMaxCourseWareId();
        if (courseWareId == null) {
            courseWareId = 1;
        } else {
            courseWareId += 1;
        }
        Integer batch = courseWareDTOMapper.selectMaxBatchByCourseId(courseWareBO.getCourseId());
        if (batch == null) {
            batch = 1;
        } else {
            batch += 1;
        }
        courseWareBO.setCourseWareId(courseWareId);
        courseWareBO.setBatch(batch);
        courseWareBO.setVersion(Long.parseLong(Integer.toString(0)));
        courseWareDTOMapper.insertSelective(convertToDataObject(courseWareBO));
        LOGGER.warn(sendLogMessageUtil.sendInsertMessage(CourseWareBO.class, courseWareBO));
        return courseWareBO;
    }

    @Override
    public void delete(Integer courseWareId) {
        CourseWareDTO courseWareDTO = courseWareDTOMapper.selectByPrimaryKey(courseWareId);
        courseWareDTOMapper.deleteByPrimaryKey(courseWareId);
        LOGGER.warn(sendLogMessageUtil.sendDeleteMessage(CourseWareBO.class, convertFromDataObject(courseWareDTO)));
    }

    @Override
    public CourseWareBO update(CourseWareBO courseWareBO) throws BusinessException {
        Integer result = courseWareDTOMapper.updateByPrimaryKeyAndVersionSelective(convertToDataObject(courseWareBO));
        if (result != 1 ) {
            LOGGER.error(sendLogMessageUtil.sendErrorMessage(CourseWareBO.class, courseWareBO));
            throw new BusinessException(EmBusinessError.SERVER_BUSY);
        }
        courseWareBO.setVersion(courseWareBO.getVersion() + 1);
        LOGGER.warn(sendLogMessageUtil.sendUpdateMessage(CourseWareBO.class, courseWareBO));
        return courseWareBO;
    }

    private CourseWareBO convertFromDataObject(CourseWareDTO courseWareDTO) {
        if (courseWareDTO == null) {
            return null;
        }
        CourseWareBO courseWareBO = new CourseWareBO();
        BeanUtils.copyProperties(courseWareDTO, courseWareBO);
        if (courseWareDTO.getBatch() != null) {
            courseWareBO.setBatch(Byte.toUnsignedInt(courseWareDTO.getBatch()));
        }
        return courseWareBO;
    }

    private CourseWareDTO convertToDataObject(CourseWareBO courseWareBO) {
        if (courseWareBO == null) {
            return null;
        }
        CourseWareDTO courseWareDTO = new CourseWareDTO();
        BeanUtils.copyProperties(courseWareBO, courseWareDTO);
        if (courseWareBO.getBatch() != null) {
            courseWareDTO.setBatch(courseWareBO.getBatch().byteValue());
        }
        return courseWareDTO;
    }

    private List<CourseWareBO> convertFromDataObjectList(List<CourseWareDTO> courseWareDTOList) {
        List<CourseWareBO> courseWareBOList = new ArrayList<CourseWareBO>();
        for (CourseWareDTO courseWareDTO:courseWareDTOList) {
            courseWareBOList.add(convertFromDataObject(courseWareDTO));
        }
        return courseWareBOList;
    }
}
