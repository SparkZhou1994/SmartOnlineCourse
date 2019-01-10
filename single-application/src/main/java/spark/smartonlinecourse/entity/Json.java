package spark.smartonlinecourse.entity;

/**
 * @ClassName Json
 * @Description TODO
 * @Author Spark
 * @Date 2018/10/9 11:11
 * @Version 1.0
 **/
public class Json {
    private Integer error;
    private Object message;

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Json{" +
                "error=" + error +
                ", message=" + message +
                '}';
    }
}
