package spark.course.constants;

/**
 * @ClassName CommonConstants
 * @Description TODO
 * @Author Spark
 * @Date 1/14/2019 1:58 PM
 * @Version 1.0
 **/
public final class CommonConstants {
    private CommonConstants() {}
    public final class BaseController {
        public static final String CONTENT_TYPE_FORMED = "application/x-www-form-urlencoded";
        public static final String CONTENT_TYPE_JSON = "application/json";
        private BaseController() {}
    }
    public final class Pagination {
        public static final String DEFAULT_PAGE_SIZE = "20";
        private Pagination() {}
    }
}
