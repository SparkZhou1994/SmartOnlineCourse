package spark.course.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spark.course.constants.CommonConstants;
import spark.course.error.BusinessException;
import spark.course.error.EmBusinessError;
import spark.course.util.FileUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName FileController
 * @Description TODO
 * @Author Spark
 * @Date 3/10/2019 10:23 PM
 * @Version 1.0
 **/
@RestController
@CrossOrigin
public class FileController {

    @PostMapping("/upload/user")
    public String userAvatarUpload(MultipartFile file) throws BusinessException{
        String fileName = FileUtil.fileNameConvert(file);
        try {
            FileUtil.uploadFile(file, CommonConstants.User.FILE_PATH, fileName);
        } catch (Exception e) {
            throw new BusinessException(EmBusinessError.USER_AVATAR_UPLOAD_ERROR);
        }
        return fileName;
    }

    @PostMapping("/upload/course")
    public String courseAvatarUpload(MultipartFile file) throws BusinessException{
        String fileName = FileUtil.fileNameConvert(file);
        try {
            FileUtil.uploadFile(file, CommonConstants.Course.FILE_PATH, fileName);
        } catch (Exception e) {
            throw new BusinessException(EmBusinessError.COURSE_AVATAR_UPLOAD_ERROR);
        }
        return fileName;
    }

    @PostMapping("/upload/homework")
    public String homeworkAvatarUpload(MultipartFile file) throws BusinessException{
        String fileName = FileUtil.fileNameConvert(file);
        try {
            FileUtil.uploadFile(file, CommonConstants.Homework.FILE_PATH, fileName);
        } catch (Exception e) {
            throw new BusinessException(EmBusinessError.HOMEWORK_UPLOAD_ERROR);
        }
        return fileName;
    }

    @PostMapping("/upload/courseWare")
    public String courseWareAvatarUpload(MultipartFile file) throws BusinessException{
        String fileName = FileUtil.fileNameConvert(file);
        try {
            FileUtil.uploadFile(file, CommonConstants.CourseWare.FILE_PATH, fileName);
        } catch (Exception e) {
            throw new BusinessException(EmBusinessError.COURSE_WARE_UPLOAD_ERROR);
        }
        return fileName;
    }

    @GetMapping("/download/homework/{fileName}")
    public ResponseEntity<byte[]> homeworkDownload(@PathVariable("fileName") String fileName, HttpServletRequest request) {
        return FileUtil.downloadFile(fileName, CommonConstants.Homework.FILE_PATH, request);
    }

    @GetMapping("/download/courseWare/{fileName}")
    public ResponseEntity<byte[]> courseWareDownload(@PathVariable("fileName") String fileName, HttpServletRequest request) {
        return FileUtil.downloadFile(fileName, CommonConstants.CourseWare.FILE_PATH, request);
    }
}
