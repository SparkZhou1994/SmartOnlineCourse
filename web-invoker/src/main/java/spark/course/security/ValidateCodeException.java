package spark.course.security;


import org.springframework.security.core.AuthenticationException;

/**
 * @ClassName ValidateCodeException
 * @Description TODO
 * @Author Spark
 * @Date 2/10/2019 6:40 PM
 * @Version 1.0
 **/
public class ValidateCodeException extends AuthenticationException {
    private static final long serialVersionUID = -7285211528095468156L;
    public ValidateCodeException(String msg) {
        super(msg);
    }
}
