package spark.course.entity.bo;

import java.time.LocalDate;

/**
 * @ClassName MessageBO
 * @Description TODO
 * @Author Spark
 * @Date 2/4/2019 11:21 AM
 * @Version 1.0
 **/
public class MessageBO {
    private Integer messageId;
    private Long version;
    private Integer chooseCourseId;
    private String content;
    private LocalDate publishData;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Integer getChooseCourseId() {
        return chooseCourseId;
    }

    public void setChooseCourseId(Integer chooseCourseId) {
        this.chooseCourseId = chooseCourseId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getPublishData() {
        return publishData;
    }

    public void setPublishData(LocalDate publishData) {
        this.publishData = publishData;
    }
}
