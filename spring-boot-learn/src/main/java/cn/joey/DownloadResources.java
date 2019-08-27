package cn.joey;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbookFactory;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @auther liujiji
 * @date 2019/8/19 15:16
 */
@Controller("downloadResources")
@RequestMapping({"/download/resources"})
//@LoginStatusChecker(
//        required = false
//)
public class DownloadResources {
    public DownloadResources() {

    }

    @RequestMapping(
            method = {RequestMethod.GET},
            produces = {"*/*"}
    )
    @ResponseBody
    public void download(HttpServletRequest var1, HttpServletResponse var2, @RequestParam("path") String var3) throws Exception {
        if (var3 != null) {
            var3 = var3.endsWith("?") ? var3.substring(0, var3.length() - 1) : var3;
        }
        String contextPath = var1.getContextPath();
        String filePath = contextPath + "/" + var3;
        File file = new File(filePath);

        InputStream var5 = new FileInputStream(file);
        var2.setHeader("Pragma", "No-cache");
        var2.setHeader("Cache-Control", "max-age=180");
        String var6 = var3.substring(var3.lastIndexOf("/")+1, var3.length());
        var6 = var6.trim();
//        var2.setHeader("Content-disposition", "attachment; filename=" + Browser.resolve(var1).getEncodedFileName4Download(var6));
        var2.setHeader("Content-disposition", "attachment; filename="+var6);
        var6 = var6.toLowerCase();
        if (var6.endsWith(".pdf")) {
            var2.setContentType("application/pdf");
            var2.setHeader("extension", "pdf");
        } else if (var6.endsWith(".xls")) {
            var2.setContentType("application/x-excel");
            var2.setHeader("extension", "xls");
        } else if (var6.endsWith(".xlsx")) {
            var2.setContentType("application/vnd.ms-excel");
            var2.setHeader("extension", "xlsx");
        } else if (var6.endsWith(".doc")) {
            var2.setContentType("application/msword");
            var2.setHeader("extension", "doc");
        } else if (var6.endsWith(".svg")) {
            var2.setContentType("image/svg+xml");
            var2.setHeader("extension", "svg");
        } else if (var6.endsWith(".csv")) {
            var2.setContentType("application/octet-stream");
            var2.setHeader("extension", "csv");
        } else if (var6.endsWith(".txt")) {
            var2.setContentType("application/octet-stream");
            var2.setHeader("extension", "txt");
        } else {
            var2.setContentType("application/x-msdownload");
        }
        ServletOutputStream var7;
//        if(var6.endsWith(".xlsx")){
//            var7 = var2.getOutputStream();
//            Workbook workbook = HSSFWorkbookFactory.createWorkbook();
//            workbook.write(var7);
//            workbook.close();
//        }else if(var6.endsWith(".xls")){
//            var7 = var2.getOutputStream();
//            XSSFWorkbook workbook = XSSFWorkbookFactory.createWorkbook();
//            workbook.write(var7);
//            workbook.close();
//        }else{
            var7 = var2.getOutputStream();
            copyBinaryTo(var5, var7);
//        }
        var5.close();
        var7.flush();
        var7.close();
    }

    public void copyBinaryTo(InputStream var0, OutputStream var1){
        try {
            byte[] var2 = new byte[10240];

            int var3;
            while((var3 = var0.read(var2)) > 0) {
                var1.write(var2, 0, var3);
            }

            var1.flush();
        } catch (IOException var4) {
            ;
        }
    }
}
