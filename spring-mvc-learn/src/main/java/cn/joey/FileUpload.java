package cn.joey;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class FileUpload {

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public String uploadFile(HttpServletRequest request,@RequestParam("file") MultipartFile file) throws Exception{
//        MultipartHttpServletRequest multipartHttpServletRequest =(MultipartHttpServletRequest)request;
//        MultipartFile file = multipartHttpServletRequest.getFile("file");

        String fileName = file.getName();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        FileOutputStream fos=new FileOutputStream(request.getSession().getServletContext().getRealPath("/")
                +"upload/"+sdf.format(new Date())+fileName.substring(fileName.lastIndexOf('.')));
        fos.write(file.getBytes());
        fos.flush();
        fos.close();
        return "上传成功";
    }
}
