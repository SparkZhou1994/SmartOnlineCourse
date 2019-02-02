package spark.course.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spark.course.dao.HomeworkDTOMapper;
import spark.course.entity.bo.HomeworkBO;
import spark.course.entity.dto.HomeworkDTO;
import spark.course.service.HomeworkService;

import java.util.List;

/**
 * @ClassName HomeworkServiceImpl
 * @Description TODO
 * @Author Spark
 * @Date 2/2/2019 3:28 PM
 * @Version 1.0
 **/
@Service
public class HomeworkServiceImpl implements HomeworkService {
    @Autowired
    HomeworkDTOMapper homeworkDTOMapper;
    @Override
    public HomeworkBO selectByHomeworkId(Integer homeworkId) {
        return null;
    }

    @Override
    public List<HomeworkBO> selectByChooseCourseId(Integer chooseCourseId, Integer start, Integer size) {
        return null;
    }

    @Override
    public HomeworkBO insert(HomeworkBO homeworkBO) {
        return null;
    }

    @Override
    public void delete(Integer homeworkId) {

    }

    @Override
    public HomeworkBO update(HomeworkBO homeworkBO) {
        return null;
    }

    private HomeworkBO convertFromDataObject(HomeworkDTO homeworkDTO) {
        if (homeworkDTO == null) {
            return null;
        }
        HomeworkBO homeworkBO = new HomeworkBO();
        BeanUtils.copyProperties(homeworkDTO, homeworkBO);
        if (homeworkDTO.getBatch() != null) {
            homeworkBO.setBatch(Byte.toUnsignedInt(homeworkDTO.getBatch()));
        }
        if (homeworkDTO.getScore() != null) {
            homeworkBO.setScore(Byte.toUnsignedInt(homeworkDTO.getScore()));
        }
        return homeworkBO;
    }

    private HomeworkDTO convertToDataObject(HomeworkBO homeworkBO) {
        if (homeworkBO == null) {
            return null;
        }
        HomeworkDTO homeworkDTO = new HomeworkDTO();
        BeanUtils.copyProperties(homeworkBO, homeworkDTO);
        //
        homeworkDTO.setBatch(homeworkBO.getBatch().byteValue());
        homeworkDTO.setScore(homeworkBO.getScore().byteValue());
    }
}
