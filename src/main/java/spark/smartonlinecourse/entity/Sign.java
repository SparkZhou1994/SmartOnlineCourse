package spark.smartonlinecourse.entity;

import java.sql.Timestamp;

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
    private Integer batch;
    private String code;
    private Timestamp signTime;
    private Timestamp startTime;
    private Timestamp endTime;
    private Boolean range;

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

    public Timestamp getSignTime() {
        return signTime;
    }

    public void setSignTime(Timestamp signTime) {
        this.signTime = signTime;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Boolean getRange() {
        return range;
    }

    public void setRange(Boolean range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return "Sign{" +
                "signId=" + signId +
                ", chooseCourseId=" + chooseCourseId +
                ", batch=" + batch +
                ", code='" + code + '\'' +
                ", signTime=" + signTime +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", range=" + range +
                '}';
    }
}
