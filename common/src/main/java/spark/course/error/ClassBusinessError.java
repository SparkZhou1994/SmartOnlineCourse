package spark.course.error;

/**
 * @ClassName ClassBusinessError
 * @Description TODO
 * @Author Spark
 * @Date 2/12/2019 10:22 PM
 * @Version 1.0
 **/
public class ClassBusinessError implements CommonError{
    private Integer errCode;
    private String errMsg;

    public ClassBusinessError(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public Integer getErrCode() {
        return errCode;
    }

    @Override
    public String getErrMsg() {
        return errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
