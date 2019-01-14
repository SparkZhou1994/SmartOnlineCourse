package spark.course.validator;

import org.springframework.beans.factory.InitializingBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @ClassName ValidatorImpl
 * @Description TODO
 * @Author Spark
 * @Date 1/14/2019 9:53 AM
 * @Version 1.0
 **/
public class ValidatorImpl implements InitializingBean {
    private Validator validator;
    /**
    * @author Spark
    * @Description 实现校验方法并返回校验结果
    * @Date 2:21 PM 1/14/2019
    * @Param [bean]
    * @return spark.course.validator.ValidationResult
    **/
    public ValidationResult validate(Object bean) {
        ValidationResult result = new ValidationResult();
        Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(bean);
        if (constraintViolationSet.size() > 0 ) {
            result.setHasErrors(true);
            constraintViolationSet.forEach(constraintViolation->{
                String errMsg = constraintViolation.getMessage();
                String propertyName = constraintViolation.getPropertyPath().toString();
                result.getErrorMsgMap().put(propertyName, errMsg);
            });
        }
        return result;
    }
    /**
    * @author Spark
    * @Description 将hibernate validator通过工厂的初始化方法使其实例化
    * @Date 2:22 PM 1/14/2019
    * @Param []
    * @return void
    **/
    @Override
    public void afterPropertiesSet() throws Exception {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
