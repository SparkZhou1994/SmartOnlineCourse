package spark.course.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spark.course.dao.ChooseCourseDTOMapper;
import spark.course.entity.bo.CourseBO;
import spark.course.entity.dto.ChooseCourseDTO;
import spark.course.error.BusinessException;
import spark.course.service.ChooseCourseService;

/**
 * @ClassName ChooseCourseServiceImpl
 * @Description TODO
 * @Author Spark
 * @Date 1/29/2019 4:21 PM
 * @Version 1.0
 **/
@Service
public class ChooseCourseServiceImpl implements ChooseCourseService {
    @Autowired
    ChooseCourseDTOMapper chooseCourseDTOMapper;

    @Override
    public CourseBO selectByChooseCourseId(Integer chooseCourseId) {
        return convertFromDataObject(chooseCourseDTOMapper.selectByPrimaryKey(chooseCourseId));
    }

    @Override
    public CourseBO selectChooseCourseIdByUserIdAndCourseId(CourseBO courseBO) {
        return convertFromDataObject(chooseCourseDTOMapper.selectChooseCourseIdByUserIdAndCourseId(convertToChooseCourseDTO(courseBO)));
    }

    @Override
    public CourseBO insert(CourseBO courseBO) {
        chooseCourseDTOMapper.insertSelective(convertToChooseCourseDTO(courseBO));
        return courseBO;
    }

    @Override
    public void deleteByChooseCourseId(Integer chooseCourseId) {
        chooseCourseDTOMapper.deleteByPrimaryKey(chooseCourseId);
    }

    @Override
    public CourseBO updateByChooseCourseId(CourseBO courseBO) throws BusinessException {
        chooseCourseDTOMapper.updateByPrimaryKeyAndVersionSelective(convertToChooseCourseDTO(courseBO));
        return courseBO;
    }

    private CourseBO convertFromDataObject(ChooseCourseDTO chooseCourseDTO) {
        if (chooseCourseDTO == null) {
            return null;
        }
        CourseBO courseBO = new CourseBO();
        BeanUtils.copyProperties(chooseCourseDTO, courseBO);
        courseBO.setVersionChooseCourse(chooseCourseDTO.getVersion());
        return courseBO;
    }

    private ChooseCourseDTO convertToChooseCourseDTO(CourseBO courseBO) {
        if (courseBO == null) {
            return null;
        }
        ChooseCourseDTO chooseCourseDTO = new ChooseCourseDTO();
        BeanUtils.copyProperties(courseBO, chooseCourseDTO);
        chooseCourseDTO.setVersion(courseBO.getVersionChooseCourse());
        return chooseCourseDTO;
    }
}
