package spark.smartonlinecourse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spark.smartonlinecourse.dao.DiscussContentMapper;
import spark.smartonlinecourse.dao.DiscussMapper;
import spark.smartonlinecourse.entity.DiscussContent;
import spark.smartonlinecourse.entity.Key;
import spark.smartonlinecourse.service.DiscussContentService;
import spark.smartonlinecourse.service.DiscussService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * @ClassName DiscussContentServiceImpl
 * @Description TODO
 * @Author Spark
 * @Date 2018/9/6 11:17
 * @Version 1.0
 **/
@Service
public class DiscussContentServiceImpl implements DiscussContentService {

    @Autowired
    DiscussContentMapper discussContentMapper;

    @Autowired
    DiscussService discussService;

    @Override
    public List<DiscussContent> selectByDiscussIdAndPage(Integer discussId, Integer page) {
        Key key=new Key();
        key.setDiscussId(discussId);
        Integer start=page*10;
        key.setStart(start);
        List<DiscussContent> discussContentList=discussContentMapper.selectByDiscussIdAndStart(key);
        return discussContentList;
    }

    @Override
    public Boolean insertDiscussContent(DiscussContent discussContent) {
        discussContent.setPublishTime(LocalDateTime.now());
        Integer status=discussContentMapper.insertDiscussContent(discussContent);
        Boolean updateStatus=discussService.updateDiscuss(discussContent.getDiscussId());
        if(status==1){
            if(updateStatus){
                return  true;
            }
            return false;
        }else {
            return false;
        }
    }

    @Override
    public Integer selectAllCountByDiscussId(Integer discussId) {
        return discussContentMapper.selectAllCountByDiscussId(discussId);
    }

    @Override
    public Integer selectChooseCountByDiscussIdAndChoose(Integer discussId, String choose) {
        DiscussContent discussContent=new DiscussContent();
        discussContent.setDiscussId(discussId);
        discussContent.setChoose(choose);
        return discussContentMapper.selectChooseCountByDiscussIdAndChoose(discussContent);
    }
}
