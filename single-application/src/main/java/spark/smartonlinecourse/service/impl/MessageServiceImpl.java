package spark.smartonlinecourse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spark.smartonlinecourse.dao.*;
import spark.smartonlinecourse.entity.Key;
import spark.smartonlinecourse.entity.Message;
import spark.smartonlinecourse.service.MessageService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MessageService
 * @Description TODO
 * @Author Spark
 * @Date 2018/9/17 17:37
 * @Version 1.0
 **/
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    ChooseCourseMapper chooseCourseMapper;

    @Autowired
    HomeworkMapper homeworkMapper;

    @Autowired
    CourseWareMapper courseWareMapper;

    @Autowired
    SignMapper signMapper;

    @Autowired
    MessageMapper messageMapper;

    @Override
    public Boolean homeworkMessage() {
        List<Key> homeworkNotSubmitList=homeworkMapper.selectChooseCourseIdAndCount();
        if(homeworkNotSubmitList.isEmpty()){
            return Boolean.TRUE;
        }
        List<Message> messageList=new ArrayList<Message>();
        for(Key homeworkNotSubmit : homeworkNotSubmitList){
            Integer chooseCourseId=homeworkNotSubmit.getChooseCourseId();
            Integer count=homeworkNotSubmit.getCount();
            Message message=new Message();
            message.setChooseCourseId(chooseCourseId);
            message.setContent("您有"+count+"个作业未提交");
            message.setPublishDate(LocalDate.now());
            messageList.add(message);
        }
        Integer status=messageMapper.insertMessage(messageList);
        if(messageList.size()==status){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean signMessage() {
        List<Key> signNotSubmitList=signMapper.selectChooseCourseIdAndCount();
        if(signNotSubmitList.isEmpty()){
            return Boolean.TRUE;
        }
        List<Message> messageList=new ArrayList<Message>();
        for(Key signNotSubmit : signNotSubmitList){
            Integer chooseCourseId=signNotSubmit.getChooseCourseId();
            Integer count=signNotSubmit.getCount();
            Message message=new Message();
            message.setChooseCourseId(chooseCourseId);
            message.setContent("您有"+count+"个签到未提交");
            message.setPublishDate(LocalDate.now());
            messageList.add(message);
        }
        Integer status=messageMapper.insertMessage(messageList);
        if(messageList.size()==status){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public List<Message> selectByChooseCouseId(Integer chooseCourseId) {
        List<Message> messageList=messageMapper.selectByChooseCouseId(chooseCourseId);
        return messageList;
    }

    @Override
    public List<Message> selectByUserId(Integer userId) {
        Key key=new Key();
        key.setUserId(userId);
        List<Integer> chooseCourseIdList=chooseCourseMapper.selectChooseCourseId(key);
        List<Message> messageListAll=new ArrayList<Message>();
        for(Integer chooseCourseId : chooseCourseIdList){
            List<Message> messageList=messageMapper.selectByChooseCouseId(chooseCourseId);
            messageListAll.addAll(messageList);
        }
        return messageListAll;
    }

    @Override
    public Boolean courseWareMessage() {
        List<Key> newCourseWareList=courseWareMapper.selectChooseCourseIdAndCount();
        List<Message> messageList=new ArrayList<Message>();
        for(Key newCourseWare : newCourseWareList){
            Integer chooseCourseId=newCourseWare.getChooseCourseId();
            Integer count=newCourseWare.getCount();
            Message message=new Message();
            message.setChooseCourseId(chooseCourseId);
            message.setContent("您有"+count+"个的课件");
            message.setPublishDate(LocalDate.now());
            messageList.add(message);
        }
        Integer status=messageMapper.insertMessage(messageList);
        if(messageList.size()==status){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}