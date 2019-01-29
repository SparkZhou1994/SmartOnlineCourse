package spark.course.entity.bo;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @ClassName CourseBO
 * @Description TODO
 * @Author Spark
 * @Date 1/29/2019 9:35 AM
 * @Version 1.0
 **/
public class CourseBO {
    private Long version;
    private Integer courseId;
    @NotBlank(message = "课程名不能为空")
    private String courseName;
    private Integer userId;
    @NotBlank(message = "课程类别不能为空")
    private String catalog;
    private String avatar;
    private Integer avgScore;
    private Integer start;
    private Integer size;

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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Integer avgScore) {
        this.avgScore = avgScore;
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
