package spark.smartonlinecourse.entity;

import java.time.LocalDate;

/**
 * @ClassName Message
 * @Description TODO
 * @Author Spark
 * @Date 2018/9/17 17:19
 * @Version 1.0
 **/
public class Message {
    private Integer messageId;
    private Integer chooseCourseId;
    private String content;
    private LocalDate publishDate;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
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

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }
}
