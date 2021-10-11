package com.pcy.mq.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pcy.mq.sink.MailSink;
import com.pcy.service.MailService;
import com.pcy.vo.MailMessageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 邮件消息监听器
 *
 * @author PengChenyu
 * @since 2021-07-11 10:26:43
 */
@Service
@Slf4j
public class MailListener {

    @Resource
    private MailService mailService;

    /**
     * 接收邮件事务消息
     *
     * @param message
     * @throws IOException
     */
    @StreamListener(value = MailSink.MAIL_INPUT)
    public void receiveMailMessage(Message<String> message) throws IOException {
        String txid = (String) message.getHeaders().get("TRANSACTION_ID");
        ObjectMapper objectMapper = new ObjectMapper();
        MailMessageVo mailMessage = objectMapper.readValue(message.getPayload(), MailMessageVo.class);
        log.info("MailMessage===============>{}", mailMessage.toString());
        log.info("txid===============>{}", txid);
        try {
            mailService.singleSendMailTo(mailMessage, txid);
            log.info("邮件目标用户{},该消息已被消费", mailMessage.getToAddress());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("邮件目标用户{},消息已消费，但邮件发送失败", mailMessage.getToAddress());
        }
    }
}
