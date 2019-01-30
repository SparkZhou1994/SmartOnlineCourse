package spark.course.entity.dto;

import java.util.Date;

public class DiscussContentDTO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_discuss_content.discuss_content_id
     *
     * @mbg.generated Wed Jan 30 22:53:03 CST 2019
     */
    private Integer discussContentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_discuss_content.version
     *
     * @mbg.generated Wed Jan 30 22:53:03 CST 2019
     */
    private Long version;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_discuss_content.discuss_id
     *
     * @mbg.generated Wed Jan 30 22:53:03 CST 2019
     */
    private Integer discussId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_discuss_content.user_id
     *
     * @mbg.generated Wed Jan 30 22:53:03 CST 2019
     */
    private Integer userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_discuss_content.content
     *
     * @mbg.generated Wed Jan 30 22:53:03 CST 2019
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_discuss_content.publish_time
     *
     * @mbg.generated Wed Jan 30 22:53:03 CST 2019
     */
    private Date publishTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_discuss_content.choose
     *
     * @mbg.generated Wed Jan 30 22:53:03 CST 2019
     */
    private String choose;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_discuss_content.discuss_content_id
     *
     * @return the value of t_discuss_content.discuss_content_id
     *
     * @mbg.generated Wed Jan 30 22:53:03 CST 2019
     */
    public Integer getDiscussContentId() {
        return discussContentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_discuss_content.discuss_content_id
     *
     * @param discussContentId the value for t_discuss_content.discuss_content_id
     *
     * @mbg.generated Wed Jan 30 22:53:03 CST 2019
     */
    public void setDiscussContentId(Integer discussContentId) {
        this.discussContentId = discussContentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_discuss_content.version
     *
     * @return the value of t_discuss_content.version
     *
     * @mbg.generated Wed Jan 30 22:53:03 CST 2019
     */
    public Long getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_discuss_content.version
     *
     * @param version the value for t_discuss_content.version
     *
     * @mbg.generated Wed Jan 30 22:53:03 CST 2019
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_discuss_content.discuss_id
     *
     * @return the value of t_discuss_content.discuss_id
     *
     * @mbg.generated Wed Jan 30 22:53:03 CST 2019
     */
    public Integer getDiscussId() {
        return discussId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_discuss_content.discuss_id
     *
     * @param discussId the value for t_discuss_content.discuss_id
     *
     * @mbg.generated Wed Jan 30 22:53:03 CST 2019
     */
    public void setDiscussId(Integer discussId) {
        this.discussId = discussId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_discuss_content.user_id
     *
     * @return the value of t_discuss_content.user_id
     *
     * @mbg.generated Wed Jan 30 22:53:03 CST 2019
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_discuss_content.user_id
     *
     * @param userId the value for t_discuss_content.user_id
     *
     * @mbg.generated Wed Jan 30 22:53:03 CST 2019
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_discuss_content.content
     *
     * @return the value of t_discuss_content.content
     *
     * @mbg.generated Wed Jan 30 22:53:03 CST 2019
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_discuss_content.content
     *
     * @param content the value for t_discuss_content.content
     *
     * @mbg.generated Wed Jan 30 22:53:03 CST 2019
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_discuss_content.publish_time
     *
     * @return the value of t_discuss_content.publish_time
     *
     * @mbg.generated Wed Jan 30 22:53:03 CST 2019
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_discuss_content.publish_time
     *
     * @param publishTime the value for t_discuss_content.publish_time
     *
     * @mbg.generated Wed Jan 30 22:53:03 CST 2019
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_discuss_content.choose
     *
     * @return the value of t_discuss_content.choose
     *
     * @mbg.generated Wed Jan 30 22:53:03 CST 2019
     */
    public String getChoose() {
        return choose;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_discuss_content.choose
     *
     * @param choose the value for t_discuss_content.choose
     *
     * @mbg.generated Wed Jan 30 22:53:03 CST 2019
     */
    public void setChoose(String choose) {
        this.choose = choose == null ? null : choose.trim();
    }
}