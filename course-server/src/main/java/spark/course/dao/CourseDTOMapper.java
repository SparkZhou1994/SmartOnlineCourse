package spark.course.dao;

import org.apache.ibatis.annotations.Param;
import spark.course.entity.dto.CourseDTO;

import java.util.List;

public interface CourseDTOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course
     *
     * @mbg.generated Mon Jan 28 17:30:48 CST 2019
     */
    Integer deleteByPrimaryKey(Integer courseId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course
     *
     * @mbg.generated Mon Jan 28 17:30:48 CST 2019
     */
    Integer insert(CourseDTO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course
     *
     * @mbg.generated Mon Jan 28 17:30:48 CST 2019
     */
    Integer insertSelective(CourseDTO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course
     *
     * @mbg.generated Mon Jan 28 17:30:48 CST 2019
     */
    CourseDTO selectByPrimaryKey(Integer courseId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course
     *
     * @mbg.generated Mon Jan 28 17:30:48 CST 2019
     */
    Integer updateByPrimaryKeyAndVersionSelective(CourseDTO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_course
     *
     * @mbg.generated Mon Jan 28 17:30:48 CST 2019
     */
    Integer updateByPrimaryKeyAndVersion(CourseDTO record);

    /**
    * @author Spark
    * @Description selectByCourseName
    * @Date 10:24 AM 1/29/2019
    * @Param [courseName]
    * @return java.util.List<spark.course.entity.dto.CourseDTO>
    **/
    List<CourseDTO> selectCourseByCourseName(@Param("courseName") String courseName,
                                             @Param("start") Integer start,
                                             @Param("size") Integer size);

    /**
    * @author Spark
    * @Description selectSortByScore
    * @Date 10:24 AM 1/29/2019
    * @Param [start, size]
    * @return java.util.List<spark.course.entity.dto.CourseDTO>
    **/
    List<CourseDTO> selectCourseSortByScore(@Param("start") Integer start,
                                            @Param("size") Integer size);

    /**
    * @author Spark
    * @Description get the max courseID
    * @Date 3:55 PM 1/30/2019
    * @Param []
    * @return int
    **/
    Integer selectMaxCourseId();
}