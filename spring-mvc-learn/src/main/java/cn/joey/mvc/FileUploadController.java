package cn.joey;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FileUploadController {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadFile(HttpServletRequest request, Model model, @RequestParam("file") MultipartFile[] files) throws Exception {
        System.out.println("打印路径：request.getContextPath==" + request.getContextPath());
        System.out.println("打印路径：request.getServletPath==" + request.getServletPath());
        System.out.println("打印路径：request.getServletContext==" + request.getServletContext());
        String uploadRootPath = request.getServletContext().getRealPath("/") + "upload";
        System.out.println("uploadRootPath=" + uploadRootPath);
        File uploadRootDir = new File(uploadRootPath);
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
        List<File> uploadFiles = new ArrayList<File>();
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            String name = file.getOriginalFilename();
            System.out.println("Client File name is " + name);

            if (name != null && name.length() > 0) {
                try {
                    byte[] bytes = file.getBytes();
                    File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + name);
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                    stream.write(bytes);
                    stream.close();
                    uploadFiles.add(serverFile);
                    System.out.println("Write file:" + serverFile);
                } catch (Exception e) {
                    System.out.println("Error Write file:" + name);
                }

            }
        }
        model.addAttribute("uploadFiles", uploadFiles);
        return "uploadResult";
    }
}
