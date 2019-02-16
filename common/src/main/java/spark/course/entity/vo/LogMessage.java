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
    private String dataJson;
    //operation time
    private String operationTime;

    public LogMessage() {
    }

    public LogMessage(String type, Object dataType, String dataJson) {
        this.type = type;
        this.dataType = dataType;
        this.dataJson = dataJson;
        this.operationTime = DateUtil.convertFromLocalDateTime(LocalDateTime.now());
    }

    public LogMessage(String type, Object dataType, String dataJson, String operationTime) {
        this.type = type;
        this.dataType = dataType;
        this.dataJson = dataJson;
        this.operationTime = operationTime;
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

    public Object getDataJson() {
        return dataJson;
    }

    public void setData(String dataJson) {
        this.dataJson = dataJson;
    }

    public String getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(String operationTime) {
        this.operationTime = operationTime;
    }

    @Override
    public String toString() {
        return "LogMessage{" +
                "type='" + type + '\'' +
                ", dataType=" + dataType +
                ", data=" + dataJson +
                ", operationTime='" + operationTime + '\'' +
                '}';
    }
}
