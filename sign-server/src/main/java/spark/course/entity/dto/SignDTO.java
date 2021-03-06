package spark.course.entity.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class SignDTO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sign.sign_id
     *
     * @mbg.generated Fri Feb 01 09:45:56 CST 2019
     */
    private Integer signId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sign.version
     *
     * @mbg.generated Fri Feb 01 09:45:56 CST 2019
     */
    private Long version;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sign.choose_course_id
     *
     * @mbg.generated Fri Feb 01 09:45:56 CST 2019
     */
    private Integer chooseCourseId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sign.batch
     *
     * @mbg.generated Fri Feb 01 09:45:56 CST 2019
     */
    private Byte batch;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sign.code
     *
     * @mbg.generated Fri Feb 01 09:45:56 CST 2019
     */
    private String code;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sign.end_time
     *
     * @mbg.generated Fri Feb 01 09:45:56 CST 2019
     */
    private LocalDateTime endTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sign.sign_time
     *
     * @mbg.generated Fri Feb 01 09:45:56 CST 2019
     */
    private LocalDateTime signTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_sign.range
     *
     * @mbg.generated Fri Feb 01 09:45:56 CST 2019
     */
    private String range;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sign.sign_id
     *
     * @return the value of t_sign.sign_id
     *
     * @mbg.generated Fri Feb 01 09:45:56 CST 2019
     */
    public Integer getSignId() {
        return signId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sign.sign_id
     *
     * @param signId the value for t_sign.sign_id
     *
     * @mbg.generated Fri Feb 01 09:45:56 CST 2019
     */
    public void setSignId(Integer signId) {
        this.signId = signId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sign.version
     *
     * @return the value of t_sign.version
     *
     * @mbg.generated Fri Feb 01 09:45:56 CST 2019
     */
    public Long getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sign.version
     *
     * @param version the value for t_sign.version
     *
     * @mbg.generated Fri Feb 01 09:45:56 CST 2019
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sign.choose_course_id
     *
     * @return the value of t_sign.choose_course_id
     *
     * @mbg.generated Fri Feb 01 09:45:56 CST 2019
     */
    public Integer getChooseCourseId() {
        return chooseCourseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sign.choose_course_id
     *
     * @param chooseCourseId the value for t_sign.choose_course_id
     *
     * @mbg.generated Fri Feb 01 09:45:56 CST 2019
     */
    public void setChooseCourseId(Integer chooseCourseId) {
        this.chooseCourseId = chooseCourseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sign.batch
     *
     * @return the value of t_sign.batch
     *
     * @mbg.generated Fri Feb 01 09:45:56 CST 2019
     */
    public Byte getBatch() {
        return batch;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sign.batch
     *
     * @param batch the value for t_sign.batch
     *
     * @mbg.generated Fri Feb 01 09:45:56 CST 2019
     */
    public void setBatch(Byte batch) {
        this.batch = batch;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sign.code
     *
     * @return the value of t_sign.code
     *
     * @mbg.generated Fri Feb 01 09:45:56 CST 2019
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sign.code
     *
     * @param code the value for t_sign.code
     *
     * @mbg.generated Fri Feb 01 09:45:56 CST 2019
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sign.end_time
     *
     * @return the value of t_sign.end_time
     *
     * @mbg.generated Fri Feb 01 09:45:56 CST 2019
     */
    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sign.end_time
     *
     * @param endTime the value for t_sign.end_time
     *
     * @mbg.generated Fri Feb 01 09:45:56 CST 2019
     */
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sign.sign_time
     *
     * @return the value of t_sign.sign_time
     *
     * @mbg.generated Fri Feb 01 09:45:56 CST 2019
     */
    public LocalDateTime getSignTime() {
        return signTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sign.sign_time
     *
     * @param signTime the value for t_sign.sign_time
     *
     * @mbg.generated Fri Feb 01 09:45:56 CST 2019
     */
    public void setSignTime(LocalDateTime signTime) {
        this.signTime = signTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_sign.range
     *
     * @return the value of t_sign.range
     *
     * @mbg.generated Fri Feb 01 09:45:56 CST 2019
     */
    public String getRange() {
        return range;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_sign.range
     *
     * @param range the value for t_sign.range
     *
     * @mbg.generated Fri Feb 01 09:45:56 CST 2019
     */
    public void setRange(String range) {
        this.range = range == null ? null : range.trim();
    }
}