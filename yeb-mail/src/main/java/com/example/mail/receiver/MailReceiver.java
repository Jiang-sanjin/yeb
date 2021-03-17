package com.example.mail.receiver;

import com.example.server.pojo.Employee;
import com.example.server.pojo.MailConstants;
import com.rabbitmq.client.Channel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;

/**
 * 消息接收者
 *
 * @author
 */
@Component
public class MailReceiver {

    //日志
    public static final Logger logger = LoggerFactory.getLogger(MailReceiver.class);

    //引入邮件发送
    @Autowired
    private JavaMailSender javaMailSender;
    //引入邮件配置
    @Autowired
    private MailProperties mailProperties;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 邮件发送
     */
    @RabbitListener(queues = MailConstants.MAIL_QUEUE_NAME)
    public void handler(Message message, Channel channel) {  //Channel  信道

        Employee employee = (Employee) message.getPayload();  //获取员工类
        System.out.println("MailReceiver:  employee = " + employee);
        MessageHeaders headers = message.getHeaders();
        long tag = (long) headers.get(AmqpHeaders.DELIVERY_TAG);   //获取消息序号   手动确认时返回
        System.out.println("tag = " + tag);
        String msgId = (String) headers.get("spring_returned_message_correlation");   //获取msgId
        System.out.println("msgId = " + msgId);
        //使用hash
        HashOperations hash = redisTemplate.opsForHash();

        try {
            //  hash.entries
            if (hash.entries("mail_log").containsKey(msgId)) {
                //redis中包含key，说明消息已经被消费
                logger.info("消息已经被消费========>{}", msgId);
                /**
                 * 手动确认消息
                 * tag:消息序号
                 * multiple:是否多条
                 */
                channel.basicAck(tag, false);
                return;
            }
            MimeMessage msg = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg);
            // 发件人
            helper.setFrom(mailProperties.getUsername());
            // 收件人
            helper.setTo(employee.getEmail());
            // 主题
            helper.setSubject("入职邮件");
            // 发送日期
            helper.setSentDate(new Date());
            // 邮件内容
            Context context = new Context();
            context.setVariable("name", employee.getName());
            context.setVariable("posName", employee.getPosition().getName());
            context.setVariable("joblevelName", employee.getJoblevel().getName());
            context.setVariable("departmentName", employee.getDepartment().getName());
            String mail = templateEngine.process("mail", context);
            helper.setText(mail, true);
            //发送邮件
            javaMailSender.send(msg);
            logger.info("邮件发送成功");

            //将消息id存入redis
            hash.put("mail_log", msgId, "OK");
            System.out.println("MailReceiver: redis---> msgId = " + msgId);
            //手动确认消息
            channel.basicAck(tag, false);
        } catch (Exception e) {
            try {
                /**
                 *手动确认消息
                 * tag:消息序号
                 * requeue:是否退回队列
                 */
                channel.basicNack(tag, false, true);
            } catch (IOException ioException) {
                //ioException.printStackTrace();
                logger.error("消息确认失败=====>{}", ioException.getMessage());
            }
            logger.error("MailReceiver + 邮件发送失败========{}", e.getMessage());
        }
    }

/*
    // 设置监听
    @RabbitListener(queues = MailConstants.MAIL_QUEUE_NAME)
    public void handler(Employee employee) {
        // 创建消息
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            // 发件人
            helper.setFrom(mailProperties.getUsername());
            // 收件人
            helper.setTo(employee.getEmail());
            // 主题
            helper.setSubject("入职邮件");
            // 发送日期
            helper.setSentDate(new Date());
            // 邮件内容
            Context context = new Context();
            context.setVariable("name", employee.getName());
            context.setVariable("posName", employee.getPosition().getName());
            context.setVariable("joblevelName", employee.getJoblevel().getName());
            context.setVariable("departmentName", employee.getDepartment().getName());
            String mail = templateEngine.process("mail", context);
            helper.setText(mail, true);  //开启html
            // 发送邮件
            javaMailSender.send(message);
        } catch (MessagingException e) {
            logger.error("MailReceiver + 邮件发送失败========{}", e.getMessage());
        }
    }
    */

}
