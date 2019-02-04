package spark.course.dao;

import org.apache.ibatis.annotations.Param;
import spark.course.entity.dto.MessageDTO;
import java.util.List;

public interface MessageDTOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_message
     *
     * @mbg.generated Mon Feb 04 10:55:53 CST 2019
     */
    Integer deleteByPrimaryKey(Integer messageId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_message
     *
     * @mbg.generated Mon Feb 04 10:55:53 CST 2019
     */
    Integer insert(MessageDTO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_message
     *
     * @mbg.generated Mon Feb 04 10:55:53 CST 2019
     */
    Integer insertSelective(MessageDTO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_message
     *
     * @mbg.generated Mon Feb 04 10:55:53 CST 2019
     */
    MessageDTO selectByPrimaryKey(Integer messageId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_message
     *
     * @mbg.generated Mon Feb 04 10:55:53 CST 2019
     */
    Integer updateByPrimaryKeyAndVersionSelective(MessageDTO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_message
     *
     * @mbg.generated Mon Feb 04 10:55:53 CST 2019
     */
    Integer updateByPrimaryKeyAndVersion(MessageDTO record);

    /**
    * @author Spark
    * @Description select max messageId
    * @Date 11:14 AM 2/4/2019
    * @Param []
    * @return java.lang.Integer
    **/
    Integer selectMaxMessageId();

    /**
    * @author Spark
    * @Description select MessageDTO list by chooseCurseId
    * @Date 11:14 AM 2/4/2019
    * @Param [chooseCourseId, start, size]
    * @return java.util.List<spark.course.entity.dto.MessageDTO>
    **/
    List<MessageDTO> selectByChooseCourseId(@Param("chooseCourseId") Integer chooseCourseId,
                                            @Param("start") Integer start,
                                            @Param("size") Integer size);
}