package spark.course.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spark.course.dao.DiscussContentDTOMapper;
import spark.course.entity.bo.DiscussContentBO;
import spark.course.entity.dto.DiscussContentDTO;
import spark.course.error.BusinessException;
import spark.course.error.EmBusinessError;
import spark.course.service.DiscussContentService;
import spark.course.util.DateUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DiscussContentServiceImpl
 * @Description TODO
 * @Author Spark
 * @Date 1/31/2019 3:17 PM
 * @Version 1.0
 **/
@Service
public class DiscussContentServiceImpl implements DiscussContentService {
    @Autowired
    DiscussContentDTOMapper discussContentDTOMapper;
    @Override
    public List<DiscussContentBO> selectByDiscussId(Integer discussId,Integer start,Integer size) {
        return convertFromDataObjectList(discussContentDTOMapper.
                selectByDiscussId(discussId, start, size));
    }

    @Override
    public DiscussContentBO selectByPrimaryKey(Integer discussContentId) {
        return convertFromDataObject(discussContentDTOMapper.selectByPrimaryKey(discussContentId));
    }

    @Override
    public DiscussContentBO insert(DiscussContentBO discussContentBO) {
        Integer discussContentId = discussContentDTOMapper.selectMaxDiscussContentId();
        if (discussContentId == null) {
            discussContentId = 1;
        } else {
            discussContentId += 1;
        }
        discussContentBO.setDiscussContentId(discussContentId);
        discussContentBO.setVersion(Long.parseLong(Integer.toString(0)));
        discussContentBO.setPublishTime(LocalDateTime.now());
        discussContentDTOMapper.insertSelective(convertToDataObject(discussContentBO));
        return discussContentBO;
    }

    @Override
    public void delete(Integer discussContentId) {
        discussContentDTOMapper.deleteByPrimaryKey(discussContentId);
    }

    @Override
    public DiscussContentBO update(DiscussContentBO discussContentBO) throws BusinessException {
        discussContentBO.setPublishTime(LocalDateTime.now());
        Integer result = discussContentDTOMapper.updateByPrimaryKeyAndVersionSelective(
                convertToDataObject(discussContentBO));
        if (result !=1) {
            throw new BusinessException(EmBusinessError.SERVER_BUSY);
        }
        discussContentBO.setVersion(discussContentBO.getVersion()+1);
        return discussContentBO;
    }

    private DiscussContentBO convertFromDataObject(DiscussContentDTO discussContentDTO) {
        if (discussContentDTO == null) {
            return  null;
        }
        DiscussContentBO discussContentBO = new DiscussContentBO();
        BeanUtils.copyProperties(discussContentDTO, discussContentBO);
        if (discussContentDTO.getPublishTime() != null) {
            discussContentBO.setPublishTime(DateUtil.convertToLocalDateTime(discussContentDTO.getPublishTime()));
        }
        return discussContentBO;
    }

    private DiscussContentDTO convertToDataObject(DiscussContentBO discussContentBO) {
        if (discussContentBO == null) {
            return null;
        }
        DiscussContentDTO discussContentDTO = new DiscussContentDTO();
        BeanUtils.copyProperties(discussContentBO, discussContentDTO);
        if (discussContentBO.getPublishTime() != null ){
            discussContentDTO.setPublishTime(DateUtil.convertToDate(discussContentBO.getPublishTime()));
        }
        return discussContentDTO;
    }

    private List<DiscussContentBO> convertFromDataObjectList(List<DiscussContentDTO> discussContentDTOList) {
        List<DiscussContentBO> discussContentBOList = new ArrayList<DiscussContentBO>();
        for (DiscussContentDTO discussContentDTO:discussContentDTOList) {
            discussContentBOList.add(convertFromDataObject(discussContentDTO));
        }
        return discussContentBOList;
    }
}
