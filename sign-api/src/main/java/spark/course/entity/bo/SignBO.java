package spark.course.entity.bo;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDateTime;

/**
 * @ClassName SignBO
 * @Description TODO
 * @Author Spark
 * @Date 2/1/2019 9:50 AM
 * @Version 1.0
 **/
public class SignBO {
    private Integer signId;
    private Long version;
    private Integer chooseCourseId;
    private Integer batch;
    @NotBlank(message = "签到码不能为空")
    private String code;
    private Integer expireTime;
    private LocalDateTime endTime;
    private LocalDateTime signTime;
    private String range;
    private Integer start;
    private Integer size;

    public Integer getSignId() {
        return signId;
    }

    public void setSignId(Integer signId) {
        this.signId = signId;
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

    public Integer getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Integer expireTime) {
        this.expireTime = expireTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getSignTime() {
        return signTime;
    }

    public void setSignTime(LocalDateTime signTime) {
        this.signTime = signTime;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
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
