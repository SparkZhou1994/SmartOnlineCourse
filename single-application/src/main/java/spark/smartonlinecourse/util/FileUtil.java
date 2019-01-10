package spark.smartonlinecourse.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;

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
    public static byte[] getBytes(File file){
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public static ResponseEntity<byte[]> downloadFile(String fileName, String filePath, HttpServletRequest request){
        File file= new File(filePath,fileName);
        String agent = (String)request.getHeader("USER-AGENT");
        byte[] buff=null;
        if(agent != null && agent.toLowerCase().indexOf("firefox") > 0)
        {
            try {
                fileName = "=?UTF-8?B?" + (new String(Base64.encodeBase64(fileName.getBytes("UTF-8")))) + "?=";
            } catch (UnsupportedEncodingException e) {

            }
        }
        else
        {
            try {
                fileName=URLEncoder.encode(fileName, "UTF-8");
            } catch (UnsupportedEncodingException e) {

            }
        }
        if(!file.isFile()){
            return null;
        }
        @SuppressWarnings("resource")
        InputStream input= null;
        try {
            input = new FileInputStream(file);
            buff=new byte[input.available()]; // 获取文件大小
            input.read(buff) ;
        } catch (Exception e) {

        }
        HttpHeaders headers=new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename="+fileName);
        //headers.add("Content-Type","text/plain");
        HttpStatus status=HttpStatus.OK;
        ResponseEntity<byte[]> entity=new ResponseEntity<byte[]>(buff,headers,status);
        return  entity;
    }
}
