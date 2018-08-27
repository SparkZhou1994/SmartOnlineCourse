package spark.smartonlinecourse.service.impl;

import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spark.smartonlinecourse.dao.ChooseCourseMapper;
import spark.smartonlinecourse.dao.SignMapper;
import spark.smartonlinecourse.entity.Key;
import spark.smartonlinecourse.entity.Page;
import spark.smartonlinecourse.entity.Sign;
import spark.smartonlinecourse.service.SignService;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public Boolean releaseSign(Integer courseId, String code,Integer effectiveSecond,Integer userId) {
        Key key=new Key();
        key.setCourseId(courseId);
        List<Integer> chooseCourseIdList=chooseCourseMapper.selectChooseCourseId(key);
        key.setUserId(userId);
        List<Integer> chooseCoursIdOwner=chooseCourseMapper.selectChooseCourseId(key);
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
            if(chooseCourseId==chooseCoursIdOwner.get(0)){
                continue;
            }
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
            key.setChooseCourseId(chooseCourseIdList.get(0));
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

    @Override
    public List<Sign> selectSignByCourseIdAndUserIdAndStartAndRow(Integer courseId, Integer userId, Integer page,Integer row) {
        Key key=new Key();
        key.setUserId(userId);
        key.setCourseId(courseId);
        key.setStart((page-1)*row);
        key.setRow(row);
        List<Sign> signList=signMapper.selectSignByCourseIdAndUserIdAndStartAndRow(key);
        for(Sign sign:signList){
            if(sign.getSignTime()!=null){
                sign.setSignTimeString(sign.getSignTime().toString());
                sign.setSignTime(null);
            }else{
                sign.setSignTimeString("未签到");
            }
            if(sign.getEndTime()!=null){
                sign.setEndTimeString(sign.getEndTime().toString());
                sign.setEndTime(null);
            }else{
                sign.setEndTimeString("未设置");
            }
            if(sign.getRange()!=null){
                if(sign.getRange()=='1'){
                    sign.setStatus("正常");
                }else{
                    sign.setStatus("迟到");
                }
            }else{
                sign.setStatus("未签到");
            }
            sign.setRange(null);
        }
        return signList;
    }

    @Override
    public Integer selectCountByCourseIdAndUserId(Integer courseId, Integer userId) {
        Key key=new Key();
        key.setUserId(userId);
        key.setCourseId(courseId);
        Integer signCount=signMapper.selectCountByCourseIdAndUserId(key);
        return signCount;
    }

    @Override
    public String signListToJson(Integer courseId, Integer userId, Integer page,Integer row,Boolean ownFlag) {
        List<Sign> signList=new ArrayList<Sign>();
        Integer signCount;
        Integer total;
        if(ownFlag){
            signList=selectSignByCourseIdAndUserIdAndStartAndRow(courseId,null,page,row);
            signCount=selectCountByCourseIdAndUserId(courseId,null);
        }else{
            signList=selectSignByCourseIdAndUserIdAndStartAndRow(courseId,userId,page,row);
            signCount=selectCountByCourseIdAndUserId(courseId,userId);
        }
        total=(int) Math.ceil((double)signCount/row);
        Page pageEntity=new Page();
        pageEntity.setPage(page);
        pageEntity.setRecords(signCount);
        pageEntity.setData(signList);
        pageEntity.setTotal(total);
        pageEntity.setRows(row);
        Gson json =new Gson();
        String jsonString=json.toJson(pageEntity);
        return jsonString;
    }
}
