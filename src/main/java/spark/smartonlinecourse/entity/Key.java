package spark.smartonlinecourse.entity;

/**
 * @ClassName Key
 * @Description TODO
 * @Author Spark
 * @Date 2018/6/14 17:10
 * @Version 1.0
 **/
public class Key {
    private Integer userId;
    private Integer start;
    private Integer courseId;
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
