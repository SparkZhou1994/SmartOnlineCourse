package spark.course.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spark.course.dao.ChooseCourseDTOMapper;
import spark.course.entity.bo.CourseBO;
import spark.course.entity.dto.ChooseCourseDTO;
import spark.course.error.BusinessException;
import spark.course.service.ChooseCourseService;

import java.util.ArrayList;
import java.util.List;

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
    public CourseBO selectChooseCourseByUserIdAndCourseId(CourseBO courseBO) {
        return convertFromDataObject(chooseCourseDTOMapper.selectChooseCourseByUserIdAndCourseId(convertToDataObject(courseBO)));
    }

    @Override
    public CourseBO insert(CourseBO courseBO) {
        Integer chooseCourseId = chooseCourseDTOMapper.selectMaxChooseCourseId();
        if (chooseCourseId == null) {
            chooseCourseId = 1;
        } else {
            chooseCourseId += 1;
        }
        courseBO.setChooseCourseId(chooseCourseId);
        courseBO.setVersionChooseCourse(Long.parseLong(Integer.toString(0)));
        chooseCourseDTOMapper.insertSelective(convertToDataObject(courseBO));
        return courseBO;
    }

    @Override
    public void deleteByChooseCourseId(Integer chooseCourseId) {
        chooseCourseDTOMapper.deleteByPrimaryKey(chooseCourseId);
    }

    @Override
    public CourseBO updateByChooseCourseId(CourseBO courseBO) throws BusinessException {
        chooseCourseDTOMapper.updateByPrimaryKeyAndVersionSelective(convertToDataObject(courseBO));
        courseBO.setVersionChooseCourse(courseBO.getVersionChooseCourse()+1);
        return courseBO;
    }

    @Override
    public List<CourseBO> selectChooseCourseByCourseId(Integer courseId) {
        return convertFromDataObjectList(chooseCourseDTOMapper.selectChooseCourseIdByCourseId(courseId));
    }

    private CourseBO convertFromDataObject(ChooseCourseDTO chooseCourseDTO) {
        if (chooseCourseDTO == null) {
            return null;
        }
        CourseBO courseBO = new CourseBO();
        courseBO.setVersionChooseCourse(chooseCourseDTO.getVersion());
        chooseCourseDTO.setVersion(null);
        BeanUtils.copyProperties(chooseCourseDTO, courseBO);
        if (chooseCourseDTO.getScore() != null) {
            courseBO.setScore(Byte.toUnsignedInt(chooseCourseDTO.getScore()));
        }
        return courseBO;
    }

    private ChooseCourseDTO convertToDataObject(CourseBO courseBO) {
        if (courseBO == null) {
            return null;
        }
        ChooseCourseDTO chooseCourseDTO = new ChooseCourseDTO();
        BeanUtils.copyProperties(courseBO, chooseCourseDTO);
        chooseCourseDTO.setVersion(courseBO.getVersionChooseCourse());
        if (courseBO.getScore() != null) {
            chooseCourseDTO.setScore(courseBO.getScore().byteValue());
        }
        return chooseCourseDTO;
    }

    private List<CourseBO> convertFromDataObjectList(List<ChooseCourseDTO> chooseCourseDTOList) {
        List<CourseBO> courseBOList = new ArrayList<CourseBO>();
        for (ChooseCourseDTO chooseCourseDTO:chooseCourseDTOList) {
            courseBOList.add(convertFromDataObject(chooseCourseDTO));
        }
        return courseBOList;
    }
}
