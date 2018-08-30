package spark.smartonlinecourse.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Homework {
    private Integer homeworkId;
    private Integer chooseCourseId;
    private Integer courseId;
    private Integer userId;
    private String describe;
    private String title;
    private String attachment;
    private Integer batch;
    private LocalDateTime endTime;
    private LocalDateTime submitTime;
    private Character range;
    private Integer score;

    public Integer getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
    }

    public Integer getBatch() {
        return batch;
    }

    public void setBatch(Integer batch) {
        this.batch = batch;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Integer getChooseCourseId() {
        return chooseCourseId;
    }

    public void setChooseCourseId(Integer chooseCourseId) {
        this.chooseCourseId = chooseCourseId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(LocalDateTime submitTime) {
        this.submitTime = submitTime;
    }

    public Character getRange() {
        return range;
    }

    public void setRange(Character range) {
        this.range = range;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "homeworkId=" + homeworkId +
                ", chooseCourseId=" + chooseCourseId +
                ", courseId=" + courseId +
                ", userId=" + userId +
                ", describe='" + describe + '\'' +
                ", title='" + title + '\'' +
                ", attachment='" + attachment + '\'' +
                ", batch=" + batch +
                ", endTime=" + endTime +
                ", submitTime=" + submitTime +
                ", range=" + range +
                ", score=" + score +
                '}';
    }
}
