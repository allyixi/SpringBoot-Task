package com.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class TaskApplicationTests {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Test
    void sendEmail() {
        //首先创建简单邮件消息对象
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        //对邮件消息对象进行设置
        //标题
        simpleMailMessage.setSubject("这是一个标题");
        //内容
        simpleMailMessage.setText("这是邮件内容");
        //发送对象,可发送多个逗号分隔
        simpleMailMessage.setTo("420897014@qq.com");
        //发送者
        //必须与配置文件中的发送者(username)一致，否则报550 vaild user
        simpleMailMessage.setFrom("foot80@163.com");
        javaMailSender.send(simpleMailMessage);
    }

    @Test
    void sendEmail02() {
        //创建一个复杂的消息邮件对象
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            //通过MimeMessageHelper生成复杂消息邮件，true为文件上传
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            //对邮件消息对象进行设置
            //标题
            mimeMessageHelper.setSubject("这是一个标题");
            //内容
            //可使用html对消息内容进行修饰,但是要传入第二个参数true
            mimeMessageHelper.setText("<b style='color:red' >这是邮件内容</b>",true);
            //上传文件
            mimeMessageHelper.addAttachment("backup.sql", new File("/Users/lixinyu/Downloads/backup.sql"));
            //发送对象,可发送多个逗号分隔
            mimeMessageHelper.setTo("420897014@qq.com");
            //发送者
            //必须与配置文件中的发送者(username)一致，否则报550 vaild user
            mimeMessageHelper.setFrom("foot80@163.com");
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }

}
