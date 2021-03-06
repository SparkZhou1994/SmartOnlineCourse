package spark.course.dao;

import org.apache.ibatis.annotations.Param;
import spark.course.entity.bo.DiscussContentBO;
import spark.course.entity.dto.DiscussContentDTO;

import java.util.List;

public interface DiscussContentDTOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_discuss_content
     *
     * @mbg.generated Wed Jan 30 22:53:03 CST 2019
     */
    Integer deleteByPrimaryKey(Integer discussContentId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_discuss_content
     *
     * @mbg.generated Wed Jan 30 22:53:03 CST 2019
     */
    Integer insert(DiscussContentDTO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_discuss_content
     *
     * @mbg.generated Wed Jan 30 22:53:03 CST 2019
     */
    Integer insertSelective(DiscussContentDTO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_discuss_content
     *
     * @mbg.generated Wed Jan 30 22:53:03 CST 2019
     */
    DiscussContentDTO selectByPrimaryKey(Integer discussContentId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_discuss_content
     *
     * @mbg.generated Wed Jan 30 22:53:03 CST 2019
     */
    Integer updateByPrimaryKeyAndVersionSelective(DiscussContentDTO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_discuss_content
     *
     * @mbg.generated Wed Jan 30 22:53:03 CST 2019
     */
    Integer updateByPrimaryKeyAndVersion(DiscussContentDTO record);

    /**
    * @author Spark
    * @Description get the max discusssContentId
    * @Date 9:34 AM 1/31/2019
    * @Param []
    * @return int
    **/
    Integer selectMaxDiscussContentId();

    List<DiscussContentDTO> selectByDiscussId(@Param("discussId") Integer discussId,
                                              @Param("start") Integer start,
                                              @Param("size") Integer size);

    List<DiscussContentBO> selectVoteResultByDiscussId(Integer discussId);
}