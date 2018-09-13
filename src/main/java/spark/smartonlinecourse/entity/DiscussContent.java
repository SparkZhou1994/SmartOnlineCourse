package spark.smartonlinecourse.entity;

import java.time.LocalDateTime;

/**
 * @ClassName DiscussContent
 * @Description TODO
 * @Author Spark
 * @Date 2018/9/4 20:07
 * @Version 1.0
 **/
public class DiscussContent {
    private Integer discussContentId;
    private Integer discussId;
    private Integer userId;
    private String userName;
    private String content;
    private LocalDateTime publishTime;
    private String publishTimeString;
    private Character choose;

    public Integer getDiscussContentId() {
        return discussContentId;
    }

    public void setDiscussContentId(Integer discussContentId) {
        this.discussContentId = discussContentId;
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

    public Character getChoose() {
        return choose;
    }

    public void setChoose(Character choose) {
        this.choose = choose;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPublishTimeString() {
        return publishTimeString;
    }

    public void setPublishTimeString(String publishTimeString) {
        this.publishTimeString = publishTimeString;
    }
}
