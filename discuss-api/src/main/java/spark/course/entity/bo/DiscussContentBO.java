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
    private Integer chooses;
    private Integer result;
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

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getChooses() {
        return chooses;
    }

    public void setChooses(Integer chooses) {
        this.chooses = chooses;
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
}
