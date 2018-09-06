package spark.smartonlinecourse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spark.smartonlinecourse.dao.DiscussMapper;
import spark.smartonlinecourse.entity.Discuss;
import spark.smartonlinecourse.entity.Key;
import spark.smartonlinecourse.service.DiscussService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DiscussServiceImpl
 * @Description TODO
 * @Author Spark
 * @Date 2018/9/6 10:46
 * @Version 1.0
 **/
@Service
public class DiscussServiceImpl implements DiscussService {

    @Autowired
    DiscussMapper discussMapper;

    @Override
    public List<Discuss> selectByCourseIdAndPage(Integer courseId, Integer page) {
        Key key=new Key();
        key.setCourseId(courseId);
        Integer start=page*10;
        key.setStart(start);
        List<Discuss> discussList=new ArrayList<Discuss>();
        discussList=discussMapper.selectByCourseIdAndStart(key);
        for(Discuss discuss:discussList){
            discuss.setLastPublishTimeString(discuss.getLastPublishTime().toString());
            if(discuss.getVote().equals('1')){
                discuss.setVoteString("投票");
            }else{
                discuss.setVoteString("讨论");
            }
        }
        return discussList;
    }

    @Override
    public Boolean insertDiscuss(Discuss discuss) {
        discuss.setLastPublishTime(LocalDateTime.now());
        if("投票".equals(discuss.getVoteString())){
            discuss.setVote('1');
        }else{
            discuss.setVote('0');
        }
        Integer status=discussMapper.insertDiscuss(discuss);
        if(status==1){
            return Boolean.TRUE;
        }else{
            return Boolean.FALSE;
        }
    }

    @Override
    public Boolean updateDiscuss(Integer discussId) {
        Discuss discuss=new Discuss();
        discuss.setDiscussId(discussId);
        discuss.setLastPublishTime(LocalDateTime.now());
        Integer status=discussMapper.updateDiscuss(discuss);
        if(status==1){
            return Boolean.TRUE;
        }else{
            return Boolean.FALSE;
        }
    }

    @Override
    public Integer selectCountByCourseId(Integer courseId) {
        return discussMapper.selectCountByCourseId(courseId);
    }

}
