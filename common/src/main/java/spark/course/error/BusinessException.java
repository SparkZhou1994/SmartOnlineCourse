package spark.course.error;

/**
 * @ClassName BusinessException
 * @Description 包装器业务异常类实现
 * @Author Spark
 * @Date 1/13/2019 12:00 AM
 * @Version 1.0
 **/
public class BusinessException extends Exception implements CommonError {

    private CommonError  commonError;
    /**
    * @author Spark
    * @Description 直接接收EmBusinessError的传参用于构造业务异常
    * @Date 2:10 PM 1/14/2019
    * @Param [commonError]
    * @return
    **/
    public BusinessException(CommonError commonError) {
        super();
        this.commonError = commonError;
    }

    /**
    * @author Spark
    * @Description
    * @Date 2:11 PM 1/14/2019
    * @Param [commonError, errMsg]
    * @return
    **/
    public BusinessException(CommonError commonError, String errMsg) {
        super();
        this.commonError = commonError;
        this.commonError.setErrMsg(errMsg);
    }

    @Override
    public Integer getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);
        return this;
    }
}
