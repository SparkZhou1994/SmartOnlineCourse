package spark.smartonlinecourse.entity;

/**
 * @ClassName CourseWare
 * @Description TODO
 * @Author Spark
 * @Date 2018/8/25 18:08
 * @Version 1.0
 **/
public class CourseWare {
    private Integer courseWareId;
    private Integer courseId;
    private String courseName;
    private String title;
    private String attachment;
    private Integer batch;

    public Integer getCourseWareId() {
        return courseWareId;
    }

    public void setCourseWareId(Integer courseWareId) {
        this.courseWareId = courseWareId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Integer getBatch() {
        return batch;
    }

    public void setBatch(Integer batch) {
        this.batch = batch;
    }

}
