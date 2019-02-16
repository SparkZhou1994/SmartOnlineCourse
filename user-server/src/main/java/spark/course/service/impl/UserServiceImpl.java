package spark.course.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spark.course.dao.UserDTOMapper;
import spark.course.dao.UserPasswordDTOMapper;
import spark.course.entity.bo.UserBO;
import spark.course.entity.dto.UserDTO;
import spark.course.entity.dto.UserPasswordDTO;
import spark.course.error.BusinessException;
import spark.course.error.EmBusinessError;
import spark.course.service.UserService;
import spark.course.util.JsonUtil;
import spark.course.util.SendMessageUtil;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author Spark
 * @Date 1/22/2019 5:16 PM
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDTOMapper userDTOMapper;
    @Autowired
    private UserPasswordDTOMapper userPasswordDTOMapper;
    @Autowired
    private SendMessageUtil sendMessageUtil;

    @Override
    public UserBO selectByUserId(Integer userId) {
        UserDTO userDTO = userDTOMapper.selectByPrimaryKey(userId);
        UserPasswordDTO userPasswordDTO = userPasswordDTOMapper.selectByPrimaryKey(userId);
        UserBO userBO = convertFromDataObject(userDTO, userPasswordDTO);
        sendMessageUtil.sendErrorMessage(UserBO.class, JsonUtil.convertToJson(userBO));
        return userBO;
    }

    @Override
    @Transactional
    public UserBO insert(UserBO userBO) {
        Integer userId = userDTOMapper.selectMaxUserId();
        if(userId == null){
            userId = 1;
        } else {
            userId += 1;
        }
        userBO.setUserId(userId);
        userBO.setVersion(Long.parseLong(Integer.toString(0)));
        userBO.setVersionPassword(Long.parseLong(Integer.toString(0)));
        userDTOMapper.insertSelective(convertToUserDTO(userBO));
        userPasswordDTOMapper.insertSelective(convertToUserPasswordDTO(userBO));
        return userBO;
    }

    @Override
    @Transactional
    public void deleteByUserId(Integer userId) {
        userPasswordDTOMapper.deleteByPrimaryKey(userId);
        userDTOMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public UserBO updateUserByUserId(UserBO userBO) throws BusinessException {
        Integer result = userDTOMapper.
                updateByPrimaryKeyAndVersionSelective(convertToUserDTO(userBO));
        if (result != 1 ) {
            throw new BusinessException(EmBusinessError.SERVER_BUSY);
        }
        userBO.setVersion(userBO.getVersion() + 1);
        return userBO;
    }

    @Override
    public UserBO updatePasswordByUserId(UserBO userBO) throws BusinessException {
        Integer result = userPasswordDTOMapper.
                updateByPrimaryKeyAndVersionSelective(convertToUserPasswordDTO(userBO));
        if (result != 1 ) {
            throw new BusinessException(EmBusinessError.SERVER_BUSY);
        }
        userBO.setVersionPassword(userBO.getVersionPassword() + 1);
        return userBO;
    }

    @Override
    public UserBO selectByEmail(String email) {
        UserDTO userDTO = userDTOMapper.selectByEmail(email);
        UserPasswordDTO userPasswordDTO = userPasswordDTOMapper.selectByPrimaryKey(userDTO.getUserId());
        return convertFromDataObject(userDTO, userPasswordDTO);
    }

    @Override
    public void updateAge() {
        userDTOMapper.updateAge();
    }

    private UserBO convertFromDataObject(UserDTO userDTO, UserPasswordDTO userPasswordDTO) {
        if (userDTO == null) {
            return null;
        }
        UserBO userBO = new UserBO();
        BeanUtils.copyProperties(userDTO, userBO);
        userBO.setAge(Byte.toUnsignedInt(userDTO.getAge()));
        if (userPasswordDTO != null) {
            userBO.setEncryptPassword(userPasswordDTO.getEncryptPassword());
            userBO.setVersionPassword(userPasswordDTO.getVersion());
        }
        return userBO;
    }

    private UserDTO convertToUserDTO(UserBO userBO) {
        if (userBO == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userBO, userDTO);
        userDTO.setAge(userBO.getAge().byteValue());
        return userDTO;
    }

    private UserPasswordDTO convertToUserPasswordDTO(UserBO userBO) {
        if (userBO == null) {
            return null;
        }
        UserPasswordDTO userPasswordDTO = new UserPasswordDTO();
        BeanUtils.copyProperties(userBO, userPasswordDTO);
        userPasswordDTO.setVersion(userBO.getVersionPassword());
        return userPasswordDTO;
    }
}
