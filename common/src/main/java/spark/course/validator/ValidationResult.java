package spark.course.validator;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ValidationResult
 * @Description TODO
 * @Author Spark
 * @Date 1/13/2019 10:23 PM
 * @Version 1.0
 **/
public class ValidationResult {
    /**
     * @author Spark
     * @Description 校验结果是否有错
     * @Date 2:20 PM 1/14/2019
     **/
    private boolean hasErrors = false;

    /**
     * @author Spark
     * @Description 存放错误信息的map
     * @Date 2:20 PM 1/14/2019
     **/
    private Map<String,String> errorMsgMap = new HashMap<>();

    public ValidationResult() {
    }

    public ValidationResult(boolean hasErrors, Map<String, String> errorMsgMap) {
        this.hasErrors = hasErrors;
        this.errorMsgMap = errorMsgMap;
    }

    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public Map<String, String> getErrorMsgMap() {
        return errorMsgMap;
    }

    public void setErrorMsgMap(Map<String, String> errorMsgMap) {
        this.errorMsgMap = errorMsgMap;
    }

    /**
     * @author Spark
     * @Description 实现通用的通过格式化字符串信息获取错误结果的msg方法
     * @Date 2:21 PM 1/14/2019
     * @Param []
     * @return java.lang.String
     **/
    public String getErrMsg() {
        return StringUtils.join(errorMsgMap.values().toArray(),",");
    }
}
