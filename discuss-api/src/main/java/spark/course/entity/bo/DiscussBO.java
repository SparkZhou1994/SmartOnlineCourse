package spark.course.entity.bo;

import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDateTime;

/**
 * @ClassName DiscussBO
 * @Description TODO
 * @Author Spark
 * @Date 1/30/2019 10:46 PM
 * @Version 1.0
 **/
public class DiscussBO {
    private Integer discussId;
    private Long version;
    private Integer chooseCourseId;
    @NotBlank(message = "标题不能为空")
    private String title;
    @NotBlank(message = "描述不能为空")
    private String describe;
    private LocalDateTime lastPublishTime;
    private String vote;
    private String choose1;
    private String choose2;
    private String choose3;
    private String choose4;

    public Integer getDiscussId() {
        return discussId;
    }

    public void setDiscussId(Integer discussId) {
        this.discussId = discussId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public LocalDateTime getLastPublishTime() {
        return lastPublishTime;
    }

    public void setLastPublishTime(LocalDateTime lastPublishTime) {
        this.lastPublishTime = lastPublishTime;
    }

    public String getVote() {
        return vote;
    }

    public void setVote(String vote) {
        this.vote = vote;
    }

    public String getChoose1() {
        return choose1;
    }

    public void setChoose1(String choose1) {
        this.choose1 = choose1;
    }

    public String getChoose2() {
        return choose2;
    }

    public void setChoose2(String choose2) {
        this.choose2 = choose2;
    }

    public String getChoose3() {
        return choose3;
    }

    public void setChoose3(String choose3) {
        this.choose3 = choose3;
    }

    public String getChoose4() {
        return choose4;
    }

    public void setChoose4(String choose4) {
        this.choose4 = choose4;
    }
}
