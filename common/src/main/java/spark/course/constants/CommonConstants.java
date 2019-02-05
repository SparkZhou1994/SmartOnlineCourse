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
    public final class Discuss {
        public static final String VOTE_0 = "讨论";
        public static final String VOTE_1 = "投票";
        private Discuss() {}
    }
    public final class Sign {
        public static final String RANGE_0 = "超时签到";
        public static  final String RANGE_1 = "按时签到";
        public static  final String RANGE_NULL = "未签到";
    }
    public final class Homework {
        public static final String RANGE_0 = "超时提交";
        public static  final String RANGE_1 = "按时提交";
        public static  final String RANGE_NULL = "未提交";
        public static final String FILE_PATH = "E://SmartOnlineCourse//homework";
    }
    public final class CourseWare {
        public static final String FILE_PATH = "E://SmartOnlineCourse//courseWare";
    }

}
