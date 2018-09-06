package spark.smartonlinecourse.entity;

import java.time.LocalDateTime;

/**
 * @ClassName Discuss
 * @Description TODO
 * @Author Spark
 * @Date 2018/9/4 20:04
 * @Version 1.0
 **/
public class Discuss {
    private Integer discussId;
    private Integer courseId;
    private Integer useId;
    private String userName;
    private String title;
    private String describe;
    private LocalDateTime lastPublishTime;
    private String lastPublishTimeString;
    private Character vote;
    private String voteString;
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

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getLastPublishTime() {
        return lastPublishTime;
    }

    public void setLastPublishTime(LocalDateTime lastPublishTime) {
        this.lastPublishTime = lastPublishTime;
    }

    public Character getVote() {
        return vote;
    }

    public void setVote(Character vote) {
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

    public String getLastPublishTimeString() {
        return lastPublishTimeString;
    }

    public void setLastPublishTimeString(String lastPublishTimeString) {
        this.lastPublishTimeString = lastPublishTimeString;
    }

    public String getVoteString() {
        return voteString;
    }

    public void setVoteString(String voteString) {
        this.voteString = voteString;
    }

    public Integer getUseId() {
        return useId;
    }

    public void setUseId(Integer useId) {
        this.useId = useId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
