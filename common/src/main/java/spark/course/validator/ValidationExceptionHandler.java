package spark.course.validator;

import org.springframework.http.HttpStatus;
import org.springframework.validation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ValidationExceptionHandler
 * @Description TODO
 * @Author Spark
 * @Date 2/12/2019 4:53 PM
 * @Version 1.0
 **/
public class ValidationExceptionHandler {
    @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object validationHandlerException(Exception exception) {
        BindingResult bindResult = null;
        Map<String,String> errorMsgMap = new HashMap<>();
        if (exception instanceof BindException) {
            bindResult = ((BindException) exception).getBindingResult();
        } else if (exception instanceof MethodArgumentNotValidException) {
            bindResult = ((MethodArgumentNotValidException) exception).getBindingResult();
        }
        if (bindResult != null && bindResult.hasErrors()) {
            List<FieldError> errorsList = bindResult.getFieldErrors();
            for (FieldError error:errorsList) {
                errorMsgMap.put(error.getField(), error.getDefaultMessage());
            }
        }
        return new ValidationResult(false,errorMsgMap);
    }
}
