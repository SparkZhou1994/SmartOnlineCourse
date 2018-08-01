package spark.smartonlinecourse.entity;

public class Homework {
    private Integer homeworkId;
    private Integer batch;
    private String describe;
    private String attachment;

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

    @Override
    public String toString() {
        return "Homework{" +
                "homeworkId=" + homeworkId +
                ", batch=" + batch +
                ", describe='" + describe + '\'' +
                ", attachment='" + attachment + '\'' +
                '}';
    }
}
