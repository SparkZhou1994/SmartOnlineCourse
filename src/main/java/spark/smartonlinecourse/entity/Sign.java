package spark.smartonlinecourse.entity;

/**
 * @ClassName Sign
 * @Description TODO
 * @Author Spark
 * @Date 2018/7/23 20:50
 * @Version 1.0
 **/
public class Sign {
    private Integer signId;
    private Integer batch;
    private String code;

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

    @Override
    public String toString() {
        return "Sign{" +
                "signId=" + signId +
                ", batch=" + batch +
                ", code='" + code + '\'' +
                '}';
    }
}
