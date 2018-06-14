package spark.smartonlinecourse.entity;

import java.util.ArrayList;

public class User {
    private Integer userId;

    private String userName;

    private String password;

    private String tel;

    private String email;

    private Byte credit;

    private String avatar;

    private ArrayList<ChooseCourse> chooseCourseList;

    public ArrayList<ChooseCourse> getChooseCourseList() {
        return chooseCourseList;
    }

    public void setChooseCourseList(ArrayList<ChooseCourse> chooseCourseList) {
        this.chooseCourseList = chooseCourseList;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Byte getCredit() {
        return credit;
    }

    public void setCredit(Byte credit) {
        this.credit = credit;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public User(String password, String email) {
        this.password = password;
        this.email = email;
    }
    public User(){

    }
}