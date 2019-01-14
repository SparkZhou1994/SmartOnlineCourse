package spark.course.response;

/**
 * @ClassName CommonReturnType
 * @Description TODO
 * @Author Spark
 * @Date 1/12/2019 11:32 PM
 * @Version 1.0
 **/
public class CommonReturnType {
    /**
    * @author Spark
    * @Description 表明对应请求的返回处理结果"success"或"fail"
    * @Date 2:19 PM 1/14/2019
    **/
    private String status;

    /**
    * @author Spark
    * @Description 若status=success,则data内返回前端需要的json数据
     * 若status=fail,则data内使用通用的错误码格式
    * @Date 2:19 PM 1/14/2019
    **/
    private Object data;

    /**
    * @author Spark
    * @Description 定义一个通用的创建方法
    * @Date 2:20 PM 1/14/2019
    * @Param [result]
    * @return spark.course.response.CommonReturnType
    **/
    public static CommonReturnType create(Object result) {
        return CommonReturnType.create(result,"success");
    }

    public static CommonReturnType create(Object result, String status) {
        CommonReturnType type = new CommonReturnType();
        type.setStatus(status);
        type.setData(result);
        return  type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
