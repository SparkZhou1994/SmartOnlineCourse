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
    private Integer batch;
    private String code;
    private LocalDateTime signTime;
    private LocalDateTime endTime;
    private Character range;

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

    @Override
    public String toString() {
        return "Sign{" +
                "signId=" + signId +
                ", chooseCourseId=" + chooseCourseId +
                ", batch=" + batch +
                ", code='" + code + '\'' +
                ", signTime=" + signTime +
                ", endTime=" + endTime +
                ", range=" + range +
                '}';
    }
}
