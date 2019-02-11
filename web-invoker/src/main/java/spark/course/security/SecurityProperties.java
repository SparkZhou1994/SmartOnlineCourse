package spark.course.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import spark.course.security.browser.BrowserProperties;
import spark.course.security.validate.ValidateCodeProperties;

/**
 * @ClassName SecurityProperties
 * @Description TODO
 * @Author Spark
 * @Date 2/5/2019 8:53 PM
 * @Version 1.0
 **/
@ConfigurationProperties(prefix = "spark.security")
public class SecurityProperties {
    private BrowserProperties browser = new BrowserProperties();
    private ValidateCodeProperties code = new ValidateCodeProperties();
    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }
}
