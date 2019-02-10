package spark.course.security;

/**
 * @ClassName BrowserProperties
 * @Description TODO
 * @Author Spark
 * @Date 2/5/2019 8:54 PM
 * @Version 1.0
 **/
public class BrowserProperties {
    private String loginPage = "/LoginAndRegister.html";
    private LoginType loginType = LoginType.JSON;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}
