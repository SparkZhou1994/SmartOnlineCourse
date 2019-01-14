package spark.course.error;

/**
 * @ClassName CommonError
 * @Description TODO
 * @Author Spark
 * @Date 1/12/2019 11:48 PM
 * @Version 1.0
 **/
public interface CommonError {
    public Integer getErrCode();
    public String getErrMsg();
    public CommonError setErrMsg(String errMsg);
}
