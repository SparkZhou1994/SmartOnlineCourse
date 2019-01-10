package spark.smartonlinecourse.entity;

import java.time.LocalDateTime;

/**
 * @ClassName Sign
 * @Description TODO
 * @Author Spark
 * @Date 2018/7/23 20:50
 * @Version 1.0
 **/
public class Sign {
    private Integer signId;
    private Integer chooseCourseId;
    private String userName;
    private Integer batch;
    private String code;
    private LocalDateTime signTime;
    private String signTimeString;
    private LocalDateTime endTime;
    private String endTimeString;
    private Character range;
    private String status;

    public Integer getSignId() {
        return signId;
    }

    public void setSignId(Integer signId) {
        this.signId = signId;
    }

    public Integer getBatch() {
        return batch;
    }

    public void setBatch(Integer batch) {
        this.batch = batch;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getChooseCourseId() {
        return chooseCourseId;
    }

    public void setChooseCourseId(Integer chooseCourseId) {
        this.chooseCourseId = chooseCourseId;
    }

    public Character getRange() {
        return range;
    }

    public void setRange(Character range) {
        this.range = range;
    }

    public LocalDateTime getSignTime() {
        return signTime;
    }

    public void setSignTime(LocalDateTime signTime) {
        this.signTime = signTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSignTimeString() {
        return signTimeString;
    }

    public void setSignTimeString(String signTimeString) {
        this.signTimeString = signTimeString;
    }

    public String getEndTimeString() {
        return endTimeString;
    }

    public void setEndTimeString(String endTimeString) {
        this.endTimeString = endTimeString;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override

    public String toString() {
        return "Sign{" +
                "signId=" + signId +
                ", chooseCourseId=" + chooseCourseId +
                ", batch=" + batch +
                ", code='" + code + '\'' +
                ", signTime=" + signTime +
                ", signTimeString='" + signTimeString + '\'' +
                ", endTime=" + endTime +
                ", endTimeString='" + endTimeString + '\'' +
                ", range=" + range +
                ", status='" + status + '\'' +
                '}';
    }
}
