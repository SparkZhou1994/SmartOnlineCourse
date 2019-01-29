package spark.course.entity.bo;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @ClassName UserBO
 * @Description TODO
 * @Author Spark
 * @Date 1/22/2019 5:08 PM
 * @Version 1.0
 **/
public class UserBO {
    private Long version;
    private Long versionPassword;
    private Integer userId;
    @NotBlank(message = "用户名不能为空")
    private String username;
    @Min(value = 0, message = "年龄必须大于0岁")
    @Max(value = 150, message = "年龄必须小于150岁")
    private Integer age;
    @NotBlank(message = "手机号不能为空")
    private String telphone;
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
    private String avatar;
    @NotBlank(message = "密码不能为空")
    private String encryptPassword;

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

    public String getEncryptPassword() {
        return encryptPassword;
    }

    public void setEncryptPassword(String encryptPassword) {
        this.encryptPassword = encryptPassword;
    }
}
