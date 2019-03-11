package spark.course.web.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import spark.course.constants.CommonConstants;
import spark.course.error.BusinessException;
import spark.course.error.EmBusinessError;
import spark.course.util.FileUtil;

/**
 * @ClassName FileController
 * @Description TODO
 * @Author Spark
 * @Date 3/10/2019 10:23 PM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/upload")
@CrossOrigin
public class FileController {

    @PostMapping("/user")
    public String userAvatarUpload(MultipartFile file) throws BusinessException{
        String fileName = FileUtil.fileNameConvert(file);
        try {
            FileUtil.uploadFile(file, CommonConstants.User.FILE_PATH, fileName);
        } catch (Exception e) {
            throw new BusinessException(EmBusinessError.COURSE_WARE_UPLOAD_ERROR);
        }
        return fileName;
    }

    @PostMapping("/course")
    public String courseAvatarUpload(MultipartFile file) throws BusinessException{
        String fileName = FileUtil.fileNameConvert(file);
        try {
            FileUtil.uploadFile(file, CommonConstants.Course.FILE_PATH, fileName);
        } catch (Exception e) {
            throw new BusinessException(EmBusinessError.COURSE_WARE_UPLOAD_ERROR);
        }
        return fileName;
    }

    @PostMapping("/homework")
    public String homeworkAvatarUpload(MultipartFile file) throws BusinessException{
        String fileName = FileUtil.fileNameConvert(file);
        try {
            FileUtil.uploadFile(file, CommonConstants.Homework.FILE_PATH, fileName);
        } catch (Exception e) {
            throw new BusinessException(EmBusinessError.COURSE_WARE_UPLOAD_ERROR);
        }
        return fileName;
    }

    @PostMapping("/courseWare")
    public String courseWareAvatarUpload(MultipartFile file) throws BusinessException{
        String fileName = FileUtil.fileNameConvert(file);
        try {
            FileUtil.uploadFile(file, CommonConstants.CourseWare.FILE_PATH, fileName);
        } catch (Exception e) {
            throw new BusinessException(EmBusinessError.COURSE_WARE_UPLOAD_ERROR);
        }
        return fileName;
    }
}
