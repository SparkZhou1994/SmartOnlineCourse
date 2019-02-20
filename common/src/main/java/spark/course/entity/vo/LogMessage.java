package spark.course.entity.vo;

import spark.course.util.DateUtil;

import java.time.LocalDateTime;

/**
 * @ClassName LogMessage
 * @Description TODO
 * @Author Spark
 * @Date 2/15/2019 10:08 PM
 * @Version 1.0
 **/
public class LogMessage {
   // insert update delete
    private String type;
    // User Course
    private Object dataType;
    // UserData CourseData
    private Object data;
    private String operationTime;

    public LogMessage() {
    }

    public LogMessage(String type, Object dataType, Object data) {
        this.type = type;
        this.dataType = dataType;
        this.data = data;
        this.operationTime = DateUtil.convertFromLocalDateTime(LocalDateTime.now());
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getDataType() {
        return dataType;
    }

    public void setDataType(Object dataType) {
        this.dataType = dataType;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }
}
