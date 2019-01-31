package spark.course.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spark.course.dao.DiscussDTOMapper;
import spark.course.entity.bo.DiscussBO;
import spark.course.entity.dto.DiscussDTO;
import spark.course.error.BusinessException;
import spark.course.error.EmBusinessError;
import spark.course.service.DiscussService;
import spark.course.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DiscussServiceImpl
 * @Description TODO
 * @Author Spark
 * @Date 1/30/2019 10:47 PM
 * @Version 1.0
 **/
@Service
public class DiscussServiceImpl implements DiscussService {
    @Autowired
    DiscussDTOMapper discussDTOMapper;
    @Override
    public List<DiscussBO> selectByChooseCourseId(DiscussBO discussBO) {
        return convertFromDataObjectList(discussDTOMapper.
                selectByChooseCourseId(discussBO.getChooseCourseId()));
    }

    @Override
    public DiscussBO selectByDiscussId(Integer discussId) {
        return convertFromDataObject(discussDTOMapper.selectByPrimaryKey(discussId));
    }

    @Override
    public DiscussBO insert(DiscussBO discussBO) {
        Integer discussId = discussDTOMapper.selectMaxDiscussId();
        if (discussId == null) {
            discussId = 1;
        } else {
            discussId += 1;
        }
        discussBO.setDiscussId(discussId);
        discussBO.setVersion(Long.parseLong(Integer.toString(0)));
        discussDTOMapper.insertSelective(convertToDataObject(discussBO));
        return discussBO;
    }

    @Override
    public void delete(Integer discussId) {
        discussDTOMapper.deleteByPrimaryKey(discussId);
    }

    @Override
    public DiscussBO update(DiscussBO discussBO) throws BusinessException {
        Integer result = discussDTOMapper.updateByPrimaryKeyAndVersionSelective(
                convertToDataObject(discussBO));
        if (result !=1) {
            throw new BusinessException(EmBusinessError.SERVER_BUSY);
        }
        discussBO.setVersion(discussBO.getVersion()+1);
        return discussBO;
    }

    private DiscussBO convertFromDataObject(DiscussDTO discussDTO) {
        if (discussDTO == null) {
            return  null;
        }
        DiscussBO discussBO = new DiscussBO();
        BeanUtils.copyProperties(discussDTO, discussBO);
        if (discussDTO.getLastPublishTime() != null) {
            discussBO.setLastPublishTime(DateUtil.
                    convertToLocalDateTime(discussDTO.getLastPublishTime()));
        }
        return discussBO;
    }

    private DiscussDTO convertToDataObject(DiscussBO discussBO) {
        if (discussBO == null) {
            return null;
        }
        DiscussDTO discussDTO = new DiscussDTO();
        BeanUtils.copyProperties(discussBO, discussDTO);
        if (discussBO.getLastPublishTime() != null ){
            discussDTO.setLastPublishTime(DateUtil.convertToDate(discussBO.getLastPublishTime()));
        }
        return discussDTO;
    }

    private List<DiscussBO> convertFromDataObjectList(List<DiscussDTO> discussDTOList) {
        List<DiscussBO> discussBOList = new ArrayList<DiscussBO>();
        for (DiscussDTO discussDTO:discussDTOList) {
            discussBOList.add(convertFromDataObject(discussDTO));
        }
        return discussBOList;
    }
}
