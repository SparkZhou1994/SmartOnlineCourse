package spark.course.entity.dto;

public class ChooseCourseDTO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_choose_course.choose_course_id
     *
     * @mbg.generated Tue Jan 29 15:40:07 CST 2019
     */
    private Integer chooseCourseId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_choose_course.version
     *
     * @mbg.generated Tue Jan 29 15:40:07 CST 2019
     */
    private Long version;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_choose_course.course_id
     *
     * @mbg.generated Tue Jan 29 15:40:07 CST 2019
     */
    private Integer courseId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_choose_course.user_id
     *
     * @mbg.generated Tue Jan 29 15:40:07 CST 2019
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_choose_course.score
     *
     * @mbg.generated Tue Jan 29 15:40:07 CST 2019
     */
    private Byte score;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_choose_course.choose_course_id
     *
     * @return the value of t_choose_course.choose_course_id
     *
     * @mbg.generated Tue Jan 29 15:40:07 CST 2019
     */
    public Integer getChooseCourseId() {
        return chooseCourseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_choose_course.choose_course_id
     *
     * @param chooseCourseId the value for t_choose_course.choose_course_id
     *
     * @mbg.generated Tue Jan 29 15:40:07 CST 2019
     */
    public void setChooseCourseId(Integer chooseCourseId) {
        this.chooseCourseId = chooseCourseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_choose_course.version
     *
     * @return the value of t_choose_course.version
     *
     * @mbg.generated Tue Jan 29 15:40:07 CST 2019
     */
    public Long getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_choose_course.version
     *
     * @param version the value for t_choose_course.version
     *
     * @mbg.generated Tue Jan 29 15:40:07 CST 2019
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_choose_course.course_id
     *
     * @return the value of t_choose_course.course_id
     *
     * @mbg.generated Tue Jan 29 15:40:07 CST 2019
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_choose_course.course_id
     *
     * @param courseId the value for t_choose_course.course_id
     *
     * @mbg.generated Tue Jan 29 15:40:07 CST 2019
     */
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_choose_course.user_id
     *
     * @return the value of t_choose_course.user_id
     *
     * @mbg.generated Tue Jan 29 15:40:07 CST 2019
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_choose_course.user_id
     *
     * @param userId the value for t_choose_course.user_id
     *
     * @mbg.generated Tue Jan 29 15:40:07 CST 2019
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_choose_course.score
     *
     * @return the value of t_choose_course.score
     *
     * @mbg.generated Tue Jan 29 15:40:07 CST 2019
     */
    public Byte getScore() {
        return score;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_choose_course.score
     *
     * @param score the value for t_choose_course.score
     *
     * @mbg.generated Tue Jan 29 15:40:07 CST 2019
     */
    public void setScore(Byte score) {
        this.score = score;
    }
}