package spark.smartonlinecourse.service.impl;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spark.smartonlinecourse.dao.ChooseCourseMapper;
import spark.smartonlinecourse.dao.SignMapper;
import spark.smartonlinecourse.entity.Key;
import spark.smartonlinecourse.entity.Sign;
import spark.smartonlinecourse.service.SignService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName SignServiceImpl
 * @Description TODO
 * @Author Spark
 * @Date 2018/8/21 13:59
 * @Version 1.0
 **/
@Service
public class SignServiceImpl implements SignService {
    @Autowired
    SignMapper signMapper;
    @Autowired
    ChooseCourseMapper chooseCourseMapper;

    @Transactional
    @Override
    public Boolean releaseSign(Integer courseId, String code,Integer effectiveSecond) {
        Key key=new Key();
        key.setCourseId(courseId);
        List<Integer> chooseCourseIdList=chooseCourseMapper.selectChooseCourseId(key);
        Integer batch=signMapper.selectBatchByCourseId(courseId);
        if(batch==null){
            batch=0;
        }else{
            batch+=1;
        }
        Sign sign=new Sign();
        sign.setBatch(batch);
        sign.setCode(code);
        LocalDateTime endTime =LocalDateTime.now().plusSeconds(effectiveSecond);
        sign.setEndTime(endTime);
        for(Integer chooseCourseId : chooseCourseIdList){
            sign.setChooseCourseId(chooseCourseId);
            Integer status=signMapper.insertSign(sign);
            if(status!=1){
                throw new RuntimeException("发布失败");
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean signIn(Integer courseId,Integer userId,String code) {
        Key key=new Key();
        key.setCourseId(courseId);
        key.setUserId(userId);
        List<Integer> chooseCourseIdList=chooseCourseMapper.selectChooseCourseId(key);
        if(chooseCourseIdList.size()>1){
            throw new RuntimeException("签到失败，程序异常");
        }else{
            Integer batch=signMapper.selectBatchByCourseId(courseId);
            key.setBatch(batch);
            Sign sign=signMapper.selectSignByChooseCourseIdAndBatch(key);
            if(sign.getCode().equals(code)){
                LocalDateTime signTime=LocalDateTime.now();
                sign.setSignTime(signTime);
                sign.setRange(sign.getEndTime().isAfter(signTime)?'1':'0');
                Integer status=signMapper.updateSign(sign);
                if(status != 1){
                    throw new RuntimeException("签到失败，数据库异常");
                }
            }else{
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }
}
