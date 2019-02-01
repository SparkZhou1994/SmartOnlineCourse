package spark.course.service.impl;

import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spark.course.dao.SignDTOMapper;
import spark.course.entity.bo.SignBO;
import spark.course.entity.dto.SignDTO;
import spark.course.error.BusinessException;
import spark.course.error.EmBusinessError;
import spark.course.service.SignService;
import spark.course.util.DateUtil;

import java.net.Inet4Address;
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
    @Autowired
    SignDTOMapper signDTOMapper;
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
        signBO.setSignId(signId);
        signBO.setVersion(Long.parseLong(Integer.toString(0)));
        signDTOMapper.insert(convertToDataObject(signBO));
        return signBO;
    }

    @Override
    public void delete(Integer signId) {
        signDTOMapper.deleteByPrimaryKey(signId);
    }

    @Override
    public SignBO update(SignBO signBO) throws BusinessException {
        Integer result = signDTOMapper.
                updateByPrimaryKeyAndVersionSelective(convertToDataObject(signBO));
        if (result != 1 ) {
            throw new BusinessException(EmBusinessError.SERVER_BUSY);
        }
        signBO.setVersion(signBO.getVersion()+1);
        return signBO;
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
            signBO.setEndTime(DateUtil.convertToLocalDateTime(signDTO.getEndTime()));
        }
        if (signDTO.getSignTime() != null) {
            signBO.setSignTime(DateUtil.convertToLocalDateTime(signDTO.getSignTime()));
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
            signDTO.setEndTime(DateUtil.convertToDate(signBO.getEndTime()));
        }
        if (signBO.getSignTime() != null) {
            signDTO.setSignTime(DateUtil.convertToDate(signBO.getSignTime()));
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
