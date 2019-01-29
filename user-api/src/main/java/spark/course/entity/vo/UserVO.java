package spark.course.entity.vo;

/**
 * @ClassName UserVO
 * @Description TODO
 * @Author Spark
 * @Date 1/28/2019 4:23 PM
 * @Version 1.0
 **/
public class UserVO {
    private Long version;
    private Long versionPassword;
    private Integer userId;
    private String username;
    private Integer age;
    private String telphone;
    private String email;
    private String avatar;
    /*private List<ChooseCourse> chooseCourseList;*/

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getVersionPassword() {
        return versionPassword;
    }

    public void setVersionPassword(Long versionPassword) {
        this.versionPassword = versionPassword;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
