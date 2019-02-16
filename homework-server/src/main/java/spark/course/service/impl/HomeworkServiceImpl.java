package spark.course.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spark.course.constants.CommonConstants;
import spark.course.dao.HomeworkDTOMapper;
import spark.course.entity.bo.HomeworkBO;
import spark.course.entity.dto.HomeworkDTO;
import spark.course.error.BusinessException;
import spark.course.error.EmBusinessError;
import spark.course.service.HomeworkService;
import spark.course.util.JsonUtil;
import spark.course.util.SendMessageUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Autowired
    SendMessageUtil sendMessageUtil;
    @Override
    public HomeworkBO selectByHomeworkId(Integer homeworkId) {
        return convertFromDataObject(homeworkDTOMapper.selectByPrimaryKey(homeworkId));
    }

    @Override
    public List<HomeworkBO> selectByChooseCourseId(Integer chooseCourseId, Integer start, Integer size) {
        return convertFromDataObjectList(homeworkDTOMapper.
                selectByChooseCourseId(chooseCourseId, start, size));
    }

    @Override
    public HomeworkBO insert(HomeworkBO homeworkBO) {
        Integer homeworkId = homeworkDTOMapper.selectMaxHomeworkId();
        if (homeworkId == null) {
            homeworkId = 1;
        } else {
            homeworkId += 1;
        }
        Integer batch = homeworkDTOMapper.selectMaxBatchByChooseCourseId(homeworkBO.getChooseCourseId());
        if (batch == null) {
            batch = 1;
        } else {
            batch += 1;
        }
        homeworkBO.setHomeworkId(homeworkId);
        homeworkBO.setBatch(batch);
        homeworkBO.setVersion(Long.parseLong(Integer.toString(0)));
        homeworkDTOMapper.insertSelective(convertToDataObject(homeworkBO));
        sendMessageUtil.sendInsertMessage(HomeworkBO.class, JsonUtil.convertToJson(homeworkBO));
        return homeworkBO;
    }

    @Override
    public void delete(Integer homeworkId) {
        HomeworkDTO homeworkDTO = homeworkDTOMapper.selectByPrimaryKey(homeworkId);
        homeworkDTOMapper.deleteByPrimaryKey(homeworkId);
        sendMessageUtil.sendDeleteMessage(HomeworkBO.class,
                JsonUtil.convertToJson(convertFromDataObject(homeworkDTO)));
    }

    @Override
    public HomeworkBO update(HomeworkBO homeworkBO)throws BusinessException {
        HomeworkDTO homeworkDTO = homeworkDTOMapper.selectByPrimaryKey(homeworkBO.getHomeworkId());
        homeworkBO.setSubmitTime(LocalDateTime.now());
        if (homeworkBO.getSubmitTime().isAfter(homeworkDTO.getEndTime())) {
            homeworkBO.setRange("0");
        } else {
            homeworkBO.setRange("1");
        }
        Integer result = homeworkDTOMapper.updateByPrimaryKeyAndVersionSelective(convertToDataObject(homeworkBO));
        if (result != 1 ) {
            sendMessageUtil.sendErrorMessage(HomeworkBO.class, JsonUtil.convertToJson(homeworkBO));
            throw new BusinessException(EmBusinessError.SERVER_BUSY);
        }
        homeworkBO.setVersion(homeworkBO.getVersion() + 1);
        sendMessageUtil.sendUpdateMessage(HomeworkBO.class, JsonUtil.convertToJson(homeworkBO));
        return homeworkBO;
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
        if (homeworkBO.getBatch() != null) {
            homeworkDTO.setBatch(homeworkBO.getBatch().byteValue());
        }
        if (homeworkBO.getScore() != null) {
            homeworkDTO.setScore(homeworkBO.getScore().byteValue());
        }
        return homeworkDTO;
    }

    private List<HomeworkBO> convertFromDataObjectList(List<HomeworkDTO> homeworkDTOList) {
        List<HomeworkBO> homeworkBOList = new ArrayList<HomeworkBO>();
        for (HomeworkDTO homeworkDTO:homeworkDTOList) {
            homeworkBOList.add(convertFromDataObject(homeworkDTO));
        }
        return homeworkBOList;
    }
}
