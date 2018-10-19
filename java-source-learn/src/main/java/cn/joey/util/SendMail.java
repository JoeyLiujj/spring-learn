package cn.joey.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ImportResource;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author liujiji
 */
public class SendMail {

    private static Logger log = LoggerFactory.getLogger(SendMail.class);
    private static final ResourceBundle rb=ResourceBundle.getBundle("application");
    private static String host = rb.getString("sendMail.host");
    private static String sender = rb.getString("sendMail.send");
    private static String password = rb.getString("sendMail.password");
    private static String typeName =rb.getString("sendMail.typeName");

    public  static boolean sendMail(String receiver, String ccUser,String secreter,String subject, String contents)  {
        Transport transport =null;
        try {
            log.info("sender:"+sender);
            String name = sender.substring(0, sender.indexOf('@'));
            Properties props = new Properties();
            props.setProperty("mail.smtp.auth", "true");
            //设置传输协议
            props.setProperty("mail.transport.protocol", "smtp");
            //设置发件人的SMTP服务器地址
            props.setProperty("mail.smtp.host", host);
            //2、创建定义整个应用程序所需的环境信息的 Session 对象
            Session session = Session.getInstance(props);
            //设置调试信息在控制台打印出来
//            session.setDebug(true);
            //3、创建邮件的实例对象
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(sender, typeName));
            /*
             * 设置收件人地址（可以增加多个收件人、抄送、密送），即下面这一行代码书写多行
             * MimeMessage.RecipientType.TO:发送
             * MimeMessage.RecipientType.CC：抄送
             * MimeMessage.RecipientType.BCC：密送
             */
            mimeMessage.setRecipients(MimeMessage.RecipientType.TO,InternetAddress.parse(receiver));
            if (null!=ccUser &&!"".equals(ccUser)) {
                mimeMessage.setRecipients(MimeMessage.RecipientType.CC,InternetAddress.parse(ccUser));
            }
            if (null!=secreter&&!"".equals(secreter)) {
                mimeMessage.setRecipients(MimeMessage.RecipientType.BCC,InternetAddress.parse(secreter));
            }
            //设置主题
            mimeMessage.setSubject(subject,"utf-8");

            mimeMessage.setContent(contents,"text/html;charset=UTF-8");

            //设置邮件的发送时间,默认立即发送
            mimeMessage.setSentDate(new Date());

            //4、根据session对象获取邮件传输对象Transport
            transport = session.getTransport();
            //设置发件人的账户名和密码
            transport.connect(sender,name,password);
            //发送邮件，并发送到所有收件人地址，message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
            //如果只想发送给指定的人，可以如下写法
            //transport.sendMessage(msg, new Address[]{new InternetAddress("xxx@qq.com")});
            return true;
        } catch (Exception e) {
            log.error("发送普通邮件（" + receiver + "）异常",e.getMessage());
        }finally {
            //5、关闭邮件连接
            if (transport != null) {
                try {
                    transport.close();
                } catch (MessagingException e) {
                    log.error("关闭发送普通邮件（" + receiver + "）连接异常",e.getMessage());
                }
            }
        }
        return false;
    }
    /**
     * @param receiver	收件人
     * @param subject	主题
     * @param contents	内容
     * @return	true-成功 false-失败
     */
     public static boolean sendEmail(String receiver, String ccUser,String subject, String contents){
         Transport transport = null;
         try{
            log.info("sender:"+sender);
            String name = sender.substring(0, sender.indexOf('@'));
            Properties props = new Properties();
            //Setup mail server
            //设置smtp主机
            props.put("mail.smtp.host", host);
            //使用smtp身份验证
            props.put("mail.smtp.auth", "true");
            //Get session
            Session session = Session.getDefaultInstance(props, null);
            //Define message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender, typeName));
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
            // 设置多个抄送地址
            if(null != ccUser && !ccUser.isEmpty()){
                InternetAddress[] internetAddressCC = InternetAddress.parse(ccUser);
                message.setRecipients(Message.RecipientType.CC, internetAddressCC);
            }
            message.setSubject(subject);
            message.setText(contents, "UTF-8");
            message.saveChanges();
            //Send message
            transport = session.getTransport("smtp");
            log.info("******正在连接" + host);
            transport.connect(host,name, password);
            log.info("******正在发送给" + receiver);
            transport.sendMessage(message, message.getAllRecipients());
            log.info("******邮件发送成功");
            return true;
        }catch(Exception e){
            log.info("发送普通邮件（" + receiver + "）异常", e);
            return false;
        }finally {
            //5、关闭邮件连接
            if (transport != null) {
                try {
                    transport.close();
                } catch (MessagingException e) {
                    log.error("关闭发送普通邮件（" + receiver + "）连接异常",e.getMessage());
                }
            }
        }
    }
    /**
     * 发送普通邮件
     * @param receiver	收件人
     * @param ccUser    抄送人
     * @param subject	主题
     * @param contents	内容
     * @param filePath 附件路径
     * @return	true-成功 false-失败
     */
    public static boolean sendEmail(String receiver,String ccUser, String subject, String contents, String filePath){
        Transport transport = null;
        try{
            String name = sender.substring(0, sender.indexOf('@'));
            Properties props = new Properties();
            //Setup mail server
            //设置smtp主机
            props.put("mail.smtp.host", host);
            //使用smtp身份验证
            props.put("mail.smtp.auth", "true");
            //Get session
            Session session = Session.getDefaultInstance(props, null);
            //Define message
            Message message = new MimeMessage(session);
            //整封邮件的MINE消息体
            //混合的组合关系
            MimeMultipart msgMultipart = new MimeMultipart("mixed");
            message.setFrom(new InternetAddress(sender, typeName));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
            // 设置多个抄送地址
            if(null != ccUser && !ccUser.isEmpty()){
                InternetAddress[] internetAddressCC = InternetAddress.parse(ccUser);
                message.setRecipients(Message.RecipientType.CC, internetAddressCC);
            }
            log.info("contents=" + contents);
            BodyPart contentPart = new MimeBodyPart();
//          设置文本为html 文本
            contentPart.setContent(contents, "text/html;charset=UTF-8");
            message.setSubject(subject);
            msgMultipart.addBodyPart(contentPart);
            message.setSubject(subject);
            //设置邮件的MINE消息体
            message.setContent(msgMultipart);
            if (filePath != "" && filePath != null) {
                BodyPart attachment = new MimeBodyPart();
                DataSource source = new FileDataSource(filePath);
                attachment.setDataHandler(new DataHandler(source));
                attachment.setFileName(MimeUtility.encodeWord(new File(filePath).getName()));
                msgMultipart.addBodyPart(attachment);
            }
            message.saveChanges();
            //Send message
            transport = session.getTransport("smtp");
            log.info("******正在连接" + host);
            transport.connect(host, name, password);
            log.info("******正在发送给" + receiver);
            transport.sendMessage(message, message.getAllRecipients());
            log.info("******邮件发送成功");
            return true;
        }catch(Exception e){
            log.info("发送普通邮件（" + receiver + "）异常", e);
            return false;
        }finally {
            if(transport!=null){
                try {
                    transport.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 发送普通邮件
     * @param receiver	收件人
     * @param subject	主题
     * @param contents	内容
     * @param filePath 附件路径
     * @return	true-成功 false-失败
     */
    public static boolean sendEmailNoSecreter(String receiver, String subject, String contents, String filePath){
        try{
            String name = sender.substring(0, sender.indexOf('@'));
            Properties props = new Properties();
            //Setup mail server
            props.put("mail.smtp.host", host);
            //使用smtp身份验证
            props.put("mail.smtp.auth", "true");
            //Get session
            Session session = Session.getDefaultInstance(props, null);
            //Define message
            Message message = new MimeMessage(session);
            //整封邮件的MINE消息体
            //混合的组合关系
            MimeMultipart msgMultipart = new MimeMultipart("mixed");
            message.setFrom(new InternetAddress(sender, typeName));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
            log.info("contents=" + contents);
            BodyPart contentPart = new MimeBodyPart();
//          设置文本为html 文本
            contentPart.setContent(contents, "text/html;charset=UTF-8");
            message.setSubject(subject);
            msgMultipart.addBodyPart(contentPart);
            message.setSubject(subject);
            //设置邮件的MINE消息体
            message.setContent(msgMultipart);
            if (filePath != "" && filePath != null) {
                BodyPart attachment = new MimeBodyPart();
                DataSource source = new FileDataSource(filePath);
                attachment.setDataHandler(new DataHandler(source));
                attachment.setFileName(MimeUtility.encodeWord(new File(filePath).getName()));
                msgMultipart.addBodyPart(attachment);
            }
            message.saveChanges();
            //Send message
            Transport transport = session.getTransport("smtp");
            log.info("******正在连接" + host);
            transport.connect(host, name, password);
            log.info("******正在发送给" + receiver);
            transport.sendMessage(message, message.getAllRecipients());
            log.info("******邮件发送成功");
            return true;
        }catch(Exception e){
            log.info("发送普通邮件（" + receiver + "）异常", e);
            return false;
        }
    }

    /**
     * 发送普通邮件
     * @param receiver	收件人
     * @param subject	主题
     * @param contents	内容
     * @return	true-成功 false-失败
     */
    public static boolean sendEmail(String receiver, String subject, String contents, List<String> filePaths){
        try{
            String name = sender.substring(0, sender.indexOf('@'));
            Properties props = new Properties();
            //Setup mail server
            //设置smtp主机
            props.put("mail.smtp.host", host);
            //使用smtp身份验证
            props.put("mail.smtp.auth", "true");
            //Get session
            Session session = Session.getDefaultInstance(props, null);
            //Define message
            Message message = new MimeMessage(session);
            //整封邮件的MINE消息体
            //混合的组合关系
            MimeMultipart msgMultipart = new MimeMultipart("mixed");
            message.setFrom(new InternetAddress(sender, typeName));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
            log.info("contents=" + contents);
            BodyPart contentPart = new MimeBodyPart();
//          设置文本为html 文本
            contentPart.setContent(contents, "text/html;charset=UTF-8");
            message.setSubject(subject);
            msgMultipart.addBodyPart(contentPart);
            message.setSubject(subject);
            //设置邮件的MINE消息体
            message.setContent(msgMultipart);
            if (filePaths.size()>0) {
                for(String filePath: filePaths){
                    File file = new File(filePath);
                    if(file.exists()){
                        BodyPart attachment = new MimeBodyPart();
                        DataSource source = new FileDataSource(filePath);
                        attachment.setDataHandler(new DataHandler(source));
                        attachment.setFileName(MimeUtility.encodeWord(file.getName()));
                        msgMultipart.addBodyPart(attachment);
                    }
                }
            }
            message.saveChanges();
            //Send message
            Transport transport = session.getTransport("smtp");
            log.info("******正在连接" + host);
            transport.connect(host, name, password);
            log.info("******正在发送给" + receiver);
            transport.sendMessage(message, message.getAllRecipients());
            log.info("******邮件发送成功");
            return true;
        }catch(Exception e){
            log.info("发送普通邮件（" + receiver + "）异常", e);
            return false;
        }
    }
}

