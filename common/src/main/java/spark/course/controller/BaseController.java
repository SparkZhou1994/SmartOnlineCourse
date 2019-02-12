package spark.course.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import spark.course.error.BusinessException;
import spark.course.error.EmBusinessError;
import spark.course.response.CommonReturnType;
import spark.course.validator.ValidationResult;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName BaseController
 * @Description TODO
 * @Author Spark
 * @Date 1/13/2019 12:25 AM
 * @Version 1.0
 **/
public class BaseController {
    /**
    * @author Spark
    * @Description 定义exceptionhandler解决未被controller层吸收的exception,spring钩子
    * @Date 2:23 PM 1/14/2019
    * @Param [request, ex]
    * @return java.lang.Object
    **/
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object businessHandlerException(HttpServletRequest request, Exception ex) {
        Map<String,Object> responseData = new HashMap<>();
        if (ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException)ex;
            responseData.put("errCode", businessException.getErrCode());
            responseData.put("errMsg", businessException.getErrMsg());

        }else {
            responseData.put("errCode", EmBusinessError.UNKNOWN_ERROR.getErrCode());
            responseData.put("errMsg", EmBusinessError.UNKNOWN_ERROR.getErrMsg());
        }
        return CommonReturnType.create(responseData, "fail");
    }
}
