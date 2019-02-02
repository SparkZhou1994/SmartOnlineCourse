package spark.course.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spark.course.DiscussServerApplication;
import spark.course.dao.DiscussDTOMapper;
import spark.course.entity.dto.DiscussDTO;

import java.time.LocalDateTime;

/**
 * @ClassName TestDiscussService
 * @Description TODO
 * @Author Spark
 * @Date 2/2/2019 9:39 AM
 * @Version 1.0
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DiscussServerApplication.class)
public class TestDiscussService {
    @Autowired
    DiscussService discussService;
    @Autowired
    DiscussDTOMapper discussDTOMapper;
    @Test
    public void testUpdate() {
        DiscussDTO discussBO = new DiscussDTO();
        discussBO.setVersion(Long.parseLong(Integer.toString(1)));
        discussBO.setDiscussId(1);
        discussBO.setChooseCourseId(7);
        discussBO.setTitle("Test3");
        discussBO.setDescribe("Test For Add3");
        discussBO.setVote("0");
        discussBO.setLastPublishTime(LocalDateTime.now());
        Integer result = discussDTOMapper.updateByPrimaryKeyAndVersionSelective(discussBO);
        System.out.print("++++++++++++++++++++++++++++");
        System.out.print(result);
        System.out.print("++++++++++++++++++++++++++++");
    }
}
