package spark.smartonlinecourse.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @ClassName FileUtil
 * @Description TODO
 * @Author Spark
 * @Date 2018/6/28 11:53
 * @Version 1.0
 **/
public class FileUtil {
    public static void uploadFile(MultipartFile file, String filePath, String fileName) throws Exception{
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        file.transferTo(new File(filePath + fileName));
    }
}
