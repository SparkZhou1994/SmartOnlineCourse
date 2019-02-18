package spark.course.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spark.course.dao.SignDTOMapper;
import spark.course.entity.bo.SignBO;
import spark.course.entity.dto.SignDTO;
import spark.course.error.BusinessException;
import spark.course.error.EmBusinessError;
import spark.course.service.SignService;
import spark.course.util.SendLogMessageUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SignServiceImpl
 * @Description TODO
 * @Author Spark
 * @Date 2/1/2019 10:31 AM
 * @Version 1.0
 **/
@Service
public class SignServiceImpl implements SignService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SignServiceImpl.class);
    @Autowired
    SignDTOMapper signDTOMapper;
    @Autowired
    SendLogMessageUtil sendLogMessageUtil;
    @Override
    public SignBO selectBySignId(Integer signId) {
        return convertFromDataObject(signDTOMapper.selectByPrimaryKey(signId));
    }

    @Override
    public List<SignBO> selectByChooseCourseId(Integer chooseCourseId,
                                               Integer start, Integer size) {
        return convertFromDataObjectList(signDTOMapper.
                selectByChooseCourseId(chooseCourseId, start, size));
    }

    @Override
    public SignBO insert(SignBO signBO) {
        Integer signId = signDTOMapper.selectMaxSignId();
        if (signId == null) {
            signId = 1;
        } else {
            signId += 1;
        }
        Integer batch = signDTOMapper.selectMaxBatchByChooseCourseId(signBO.getChooseCourseId());
        if (batch == null) {
            batch = 1;
        } else {
            batch += 1;
        }
        signBO.setSignId(signId);
        signBO.setBatch(batch);
        signBO.setEndTime(LocalDateTime.now().plusMinutes(signBO.getExpireTime()));
        signBO.setVersion(Long.parseLong(Integer.toString(0)));
        signDTOMapper.insert(convertToDataObject(signBO));
        LOGGER.warn(sendLogMessageUtil.sendInsertMessage(SignBO.class, signBO));
        return signBO;
    }

    @Override
    public void delete(Integer signId) {
        SignDTO signDTO = signDTOMapper.selectByPrimaryKey(signId);
        signDTOMapper.deleteByPrimaryKey(signId);
        LOGGER.warn(sendLogMessageUtil.sendDeleteMessage(SignBO.class, convertFromDataObject(signDTO)));
    }

    @Override
    public SignBO update(SignBO signBO) throws BusinessException {
        SignDTO signDTO = signDTOMapper.selectByPrimaryKey(signBO.getSignId());
        signBO.setEndTime(signDTO.getEndTime());
        if (signDTO.getCode().equals(signBO.getCode())) {
            signBO.setSignTime(LocalDateTime.now());
            if (signBO.getSignTime().isAfter(signBO.getEndTime())) {
                signBO.setRange("0");
            } else {
                signBO.setRange("1");
            }
            Integer result = signDTOMapper.
                    updateByPrimaryKeyAndVersionSelective(convertToDataObject(signBO));
            if (result != 1 ) {
                LOGGER.error(sendLogMessageUtil.sendErrorMessage(SignBO.class, signBO));
                throw new BusinessException(EmBusinessError.SERVER_BUSY);
            }
            signBO.setVersion(signBO.getVersion() + 1);
            LOGGER.warn(sendLogMessageUtil.sendUpdateMessage(SignBO.class, signBO));
            return signBO;
        } else {
            sendLogMessageUtil.sendErrorMessage(SignBO.class, signBO);
            throw new BusinessException(EmBusinessError.SIGN_CODE_ERROR);
        }
    }

    private SignBO convertFromDataObject(SignDTO signDTO) {
        if (signDTO == null) {
            return null;
        }
        SignBO signBO = new SignBO();
        BeanUtils.copyProperties(signDTO, signBO);
        if (signDTO.getBatch() != null) {
            signBO.setBatch(Byte.toUnsignedInt(signDTO.getBatch()));
        }
        if (signDTO.getEndTime() != null) {
            signBO.setEndTime(signDTO.getEndTime());
        }
        if (signDTO.getSignTime() != null) {
            signBO.setSignTime(signDTO.getSignTime());
        }
        return signBO;
    }

    private SignDTO convertToDataObject(SignBO signBO) {
        if (signBO == null) {
            return null;
        }
        SignDTO signDTO = new SignDTO();
        BeanUtils.copyProperties(signBO, signDTO);
        if (signBO.getBatch() != null) {
            signDTO.setBatch(signBO.getBatch().byteValue());
        }
        if (signBO.getEndTime() != null) {
            signDTO.setEndTime(signBO.getEndTime());
        }
        if (signBO.getSignTime() != null) {
            signDTO.setSignTime(signBO.getSignTime());
        }
        return signDTO;
    }

    private List<SignBO> convertFromDataObjectList(List<SignDTO> signDTOList) {
        List<SignBO> signBOList = new ArrayList<SignBO>();
        for (SignDTO signDTO:signDTOList) {
            signBOList.add(convertFromDataObject(signDTO));
        }
        return signBOList;
    }
}
