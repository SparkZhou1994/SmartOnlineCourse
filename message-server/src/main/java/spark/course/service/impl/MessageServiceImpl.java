package spark.course.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spark.course.dao.MessageDTOMapper;
import spark.course.entity.bo.MessageBO;
import spark.course.entity.dto.MessageDTO;
import spark.course.error.BusinessException;
import spark.course.error.EmBusinessError;
import spark.course.service.MessageService;
import spark.course.util.JsonUtil;
import spark.course.util.SendMessageUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MessageServiceImpl
 * @Description TODO
 * @Author Spark
 * @Date 2/4/2019 11:34 AM
 * @Version 1.0
 **/
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageDTOMapper messageDTOMapper;
    @Autowired
    SendMessageUtil sendMessageUtil;
    @Override
    public MessageBO selectByMessageId(Integer messageId) {
        return convertFromDataObject(messageDTOMapper.selectByPrimaryKey(messageId));
    }

    @Override
    public List<MessageBO> selectByChooseCourseId(Integer chooseCourseId, Integer start, Integer size) {
        return convertFromDataObjectList(messageDTOMapper.
                selectByChooseCourseId(chooseCourseId, start, size));
    }

    @Override
    public MessageBO insert(MessageBO messageBO) {
        Integer messageId = messageDTOMapper.selectMaxMessageId();
        if (messageId == null) {
            messageId = 1;
        } else {
            messageId += 1;
        }
        messageBO.setMessageId(messageId);
        messageBO.setVersion(Long.parseLong(Integer.toString(0)));
        messageBO.setPublishData(LocalDate.now());
        messageDTOMapper.insertSelective(convertToDataObject(messageBO));
        sendMessageUtil.sendInsertMessage(MessageBO.class, JsonUtil.convertToJson(messageBO));
        return messageBO;
    }

    @Override
    public void delete(Integer messageId) {
        MessageDTO messageDTO = messageDTOMapper.selectByPrimaryKey(messageId);
        messageDTOMapper.deleteByPrimaryKey(messageId);
        sendMessageUtil.sendDeleteMessage(MessageBO.class,
                JsonUtil.convertToJson(convertFromDataObject(messageDTO)));
    }

    @Override
    public MessageBO updata(MessageBO messageBO) throws BusinessException {
        messageBO.setPublishData(LocalDate.now());
        Integer result = messageDTOMapper.updateByPrimaryKeyAndVersionSelective(convertToDataObject(messageBO));
        if (result != 1 ) {
            sendMessageUtil.sendErrorMessage(MessageBO.class, JsonUtil.convertToJson(messageBO));
            throw new BusinessException(EmBusinessError.SERVER_BUSY);
        }
        messageBO.setVersion(messageBO.getVersion() + 1);
        sendMessageUtil.sendUpdateMessage(MessageBO.class, JsonUtil.convertToJson(messageBO));
        return messageBO;
    }

    private MessageBO convertFromDataObject(MessageDTO messageDTO) {
        if (messageDTO == null) {
            return null;
        }
        MessageBO messageBO = new MessageBO();
        BeanUtils.copyProperties(messageDTO, messageBO);
        return messageBO;
    }

    private MessageDTO convertToDataObject(MessageBO messageBO) {
        if (messageBO == null) {
            return null;
        }
        MessageDTO messageDTO = new MessageDTO();
        BeanUtils.copyProperties(messageBO, messageDTO);
        return messageDTO;
    }

    private List<MessageBO> convertFromDataObjectList(List<MessageDTO> messageDTOList) {
        List<MessageBO> messageBOList = new ArrayList<MessageBO>();
        for (MessageDTO messageDTO:messageDTOList) {
            messageBOList.add(convertFromDataObject(messageDTO));
        }
        return messageBOList;
    }
}
