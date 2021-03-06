package spark.course.dao;

import org.apache.ibatis.annotations.Param;
import spark.course.entity.dto.CourseWareDTO;

import java.util.List;

public interface CourseWareDTOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_ware
     *
     * @mbg.generated Sun Feb 03 20:08:58 CST 2019
     */
    int deleteByPrimaryKey(Integer courseWareId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_ware
     *
     * @mbg.generated Sun Feb 03 20:08:58 CST 2019
     */
    int insert(CourseWareDTO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_ware
     *
     * @mbg.generated Sun Feb 03 20:08:58 CST 2019
     */
    int insertSelective(CourseWareDTO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_ware
     *
     * @mbg.generated Sun Feb 03 20:08:58 CST 2019
     */
    CourseWareDTO selectByPrimaryKey(Integer courseWareId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_ware
     *
     * @mbg.generated Sun Feb 03 20:08:58 CST 2019
     */
    int updateByPrimaryKeyAndVersionSelective(CourseWareDTO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course_ware
     *
     * @mbg.generated Sun Feb 03 20:08:58 CST 2019
     */
    int updateByPrimaryKeyAndVersion(CourseWareDTO record);

    /**
    * @author Spark
    * @Description select max courseWareId
    * @Date 8:48 PM 2/3/2019
    * @Param []
    * @return java.lang.Integer
    **/
    Integer selectMaxCourseWareId();

    /**
    * @author Spark
    * @Description select max batch
    * @Date 8:48 PM 2/3/2019
    * @Param [courseId]
    * @return java.lang.Integer
    **/
    Integer selectMaxBatchByCourseId(Integer courseId);

    /**
    * @author Spark
    * @Description select CourseWareDTO List by courseId
    * @Date 8:48 PM 2/3/2019
    * @Param [courseId, start, size]
    * @return java.util.List<spark.course.entity.dto.CourseWareDTO>
    **/
    List<CourseWareDTO> selectByCourseId(@Param("courseId") Integer courseId,
                                             @Param("start") Integer start,
                                             @Param("size") Integer size);
}