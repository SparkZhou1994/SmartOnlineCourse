package spark.course.entity.vo;

import java.time.LocalDateTime;

/**
 * @ClassName DiscussContentVO
 * @Description TODO
 * @Author Spark
 * @Date 1/31/2019 3:09 PM
 * @Version 1.0
 **/
public class DiscussContentVO {
    private Integer discussContentId;
    private Long version;
    private Integer discussId;
    private Integer userId;
    private String username;
    private String content;
    private String publishTime;
    private String choose;
    private Integer start;
    private Integer size;
    private Integer choose1;
    private Integer choose2;
    private Integer choose3;
    private Integer choose4;
    private Integer total;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
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

    public Integer getChoose1() {
        return choose1;
    }

    public void setChoose1(Integer choose1) {
        this.choose1 = choose1;
    }

    public Integer getChoose2() {
        return choose2;
    }

    public void setChoose2(Integer choose2) {
        this.choose2 = choose2;
    }

    public Integer getChoose3() {
        return choose3;
    }

    public void setChoose3(Integer choose3) {
        this.choose3 = choose3;
    }

    public Integer getChoose4() {
        return choose4;
    }

    public void setChoose4(Integer choose4) {
        this.choose4 = choose4;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
