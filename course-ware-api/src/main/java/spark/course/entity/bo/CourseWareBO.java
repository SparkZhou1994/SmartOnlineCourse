package spark.course.entity.bo;

/**
 * @ClassName CourseWareBO
 * @Description TODO
 * @Author Spark
 * @Date 2/3/2019 8:10 PM
 * @Version 1.0
 **/
public class CourseWareBO {
    private Integer courseWareId;
    private Long version;
    private Integer courseId;
    private String title;
    private String attachment;
    private Integer batch;
    private Integer start;
    private Integer size;

    public Integer getCourseWareId() {
        return courseWareId;
    }

    public void setCourseWareId(Integer courseWareId) {
        this.courseWareId = courseWareId;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
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
