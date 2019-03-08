package spark.course.entity.vo;

/**
 * @ClassName CourseVO
 * @Description TODO
 * @Author Spark
 * @Date 1/29/2019 11:21 AM
 * @Version 1.0
 **/
public class CourseVO {
    private Long version;
    private Long versionChooseCourse;
    private Integer chooseCourseId;
    private Integer courseId;
    private String courseName;
    private Integer userId;
    private Integer ownerUserId;
    private String ownerUsername;
    private String catalog;
    private String avatar;
    private Integer avgScore;
    private Integer score;
    private Integer start;
    private Integer size;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getVersionChooseCourse() {
        return versionChooseCourse;
    }

    public void setVersionChooseCourse(Long versionChooseCourse) {
        this.versionChooseCourse = versionChooseCourse;
    }

    public Integer getChooseCourseId() {
        return chooseCourseId;
    }

    public void setChooseCourseId(Integer chooseCourseId) {
        this.chooseCourseId = chooseCourseId;
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

    public Integer getOwnerUserId() {
        return ownerUserId;
    }

    public void setOwnerUserId(Integer ownerUserId) {
        ownerUserId = ownerUserId;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        ownerUsername = ownerUsername;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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
