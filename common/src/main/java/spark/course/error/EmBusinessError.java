package spark.course.error;
/**
 * @author Spark
 * @Description
 * @Date 2:08 PM 1/14/2019
 * @Param
 * @return
**/
public enum EmBusinessError implements CommonError{
    /**
    * @author Spark
    * @Description 通用错误类型10001
    * @Date 2:11 PM 1/14/2019
    **/
    PARAMETER_VALIDATION_ERROR(10001,"参数不合法"),
    UNKNOWN_ERROR(10002,"未知错误"),
    SERVER_BUSY(10003,"服务器繁忙，请稍后重试"),
    /**
    * @author Spark
    * @Description 20000开头为用户信息相关错误定义
    * @Date 2:12 PM 1/14/2019
    **/
    USER_NOT_EXIST(20001,"用户不存在"),
    USER_PASSWORD_ERROR(20002,"用户名不存在或密码错误"),
    /**
     * @author Spark
     * @Description 30000开头为签到信息相关错误定义
     * @Date 2:12 PM 1/14/2019
     **/
    SIGN_CODE_ERROR(30001,"签到码错误"),
    /**
     * @author Spark
     * @Description 40000开头为作业信息相关错误定义
     * @Date 2:12 PM 1/14/2019
     **/
    HOMEWORK_UPLOAD_ERROR(40001,"作业文件上传失败"),
    /**
     * @author Spark
     * @Description 50000开头为课件信息相关错误定义
     * @Date 2:12 PM 1/14/2019
     **/
    COURSE_WARE_UPLOAD_ERROR(50001,"课件上传失败"),
    /**
     * @author Spark
     * @Description 60000开头为登录信息相关错误定义
     * @Date 2:12 PM 1/14/2019
     **/
    UNAUTHORIZED(60001,"需要身份验证"),
    AUTHORIZED_REDIRECT(60002,"验证跳转错误")
    ;

    private EmBusinessError(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    private Integer errCode;
    private String errMsg;

    @Override
    public Integer getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
