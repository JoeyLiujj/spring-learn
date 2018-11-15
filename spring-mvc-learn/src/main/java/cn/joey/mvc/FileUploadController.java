package cn.joey.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping("/checkPath")
    public String checkPath(HttpServletRequest request, Model model) throws Exception{
        if(10==10){
            throw new CustomException("我来抛个异常玩");
        }
        model.addAttribute("contextPath",request.getContextPath());
        model.addAttribute("servletPath",request.getServletPath());
        model.addAttribute("method",request.getMethod());
        model.addAttribute("pathTranslated",request.getPathTranslated());
        model.addAttribute("serverName",request.getServerName());
        model.addAttribute("requestURI",request.getRequestURI());
        model.addAttribute("requestURL",request.getRequestURL());
        model.addAttribute("contentType",request.getContentType());
        model.addAttribute("pathInfo",request.getPathInfo());
        model.addAttribute("localAddr",request.getLocalAddr());
        model.addAttribute("localName",request.getLocalName());
        model.addAttribute("localPort",request.getLocalPort());
        model.addAttribute("scheme",request.getScheme());
        model.addAttribute("remoteAddr",request.getRemoteAddr());
        model.addAttribute("remoteHost",request.getRemoteHost());
        model.addAttribute("remotePort",request.getRemotePort());
        return "requestPath.jsp";
    }


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadFile(HttpServletRequest request, Model model, @RequestParam("file") MultipartFile[] files) throws Exception {

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

        return "uploadResult.jsp";
    }
}
