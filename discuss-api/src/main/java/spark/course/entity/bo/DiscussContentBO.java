package spark.course.entity.bo;

import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDateTime;

/**
 * @ClassName DiscussContentBO
 * @Description TODO
 * @Author Spark
 * @Date 1/31/2019 3:08 PM
 * @Version 1.0
 **/
public class DiscussContentBO {
    private Integer discussContentId;
    private Long version;
    private Integer discussId;
    private Integer userId;
    private String content;
    private LocalDateTime publishTime;
    private String choose;
    private Integer start;
    private Integer size;

    public Integer getDiscussContentId() {
        return discussContentId;
    }

    public void setDiscussContentId(Integer discussContentId) {
        this.discussContentId = discussContentId;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Integer getDiscussId() {
        return discussId;
    }

    public void setDiscussId(Integer discussId) {
        this.discussId = discussId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(LocalDateTime publishTime) {
        this.publishTime = publishTime;
    }

    public String getChoose() {
        return choose;
    }

    public void setChoose(String choose) {
        this.choose = choose;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
