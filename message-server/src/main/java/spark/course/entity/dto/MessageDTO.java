package spark.course.entity.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class MessageDTO {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_message.message_id
     *
     * @mbg.generated Mon Feb 04 10:55:53 CST 2019
     */
    private Integer messageId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_message.version
     *
     * @mbg.generated Mon Feb 04 10:55:53 CST 2019
     */
    private Long version;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_message.choose_course_id
     *
     * @mbg.generated Mon Feb 04 10:55:53 CST 2019
     */
    private Integer chooseCourseId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_message.content
     *
     * @mbg.generated Mon Feb 04 10:55:53 CST 2019
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_message.publish_data
     *
     * @mbg.generated Mon Feb 04 10:55:53 CST 2019
     */
    private LocalDate publishData;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_message.message_id
     *
     * @return the value of t_message.message_id
     *
     * @mbg.generated Mon Feb 04 10:55:53 CST 2019
     */
    public Integer getMessageId() {
        return messageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_message.message_id
     *
     * @param messageId the value for t_message.message_id
     *
     * @mbg.generated Mon Feb 04 10:55:53 CST 2019
     */
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_message.version
     *
     * @return the value of t_message.version
     *
     * @mbg.generated Mon Feb 04 10:55:53 CST 2019
     */
    public Long getVersion() {
        return version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_message.version
     *
     * @param version the value for t_message.version
     *
     * @mbg.generated Mon Feb 04 10:55:53 CST 2019
     */
    public void setVersion(Long version) {
        this.version = version;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_message.choose_course_id
     *
     * @return the value of t_message.choose_course_id
     *
     * @mbg.generated Mon Feb 04 10:55:53 CST 2019
     */
    public Integer getChooseCourseId() {
        return chooseCourseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_message.choose_course_id
     *
     * @param chooseCourseId the value for t_message.choose_course_id
     *
     * @mbg.generated Mon Feb 04 10:55:53 CST 2019
     */
    public void setChooseCourseId(Integer chooseCourseId) {
        this.chooseCourseId = chooseCourseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_message.content
     *
     * @return the value of t_message.content
     *
     * @mbg.generated Mon Feb 04 10:55:53 CST 2019
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_message.content
     *
     * @param content the value for t_message.content
     *
     * @mbg.generated Mon Feb 04 10:55:53 CST 2019
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_message.publish_data
     *
     * @return the value of t_message.publish_data
     *
     * @mbg.generated Mon Feb 04 10:55:53 CST 2019
     */
    public LocalDate getPublishData() {
        return publishData;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_message.publish_data
     *
     * @param publishData the value for t_message.publish_data
     *
     * @mbg.generated Mon Feb 04 10:55:53 CST 2019
     */
    public void setPublishData(LocalDate publishData) {
        this.publishData = publishData;
    }
}