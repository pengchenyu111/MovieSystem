package com.pcy.mq.listener;

import com.pcy.mq.sink.SmsSink;
import com.pcy.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 短息消息监听器
 *
 * @author PengChenyu
 * @since 2021-07-12 15:07:30
 */
@Service
@Slf4j
public class SmsListener {

    @Resource
    private SmsService smsService;

    /**
     * 接收验证码短信消息
     *
     * @param message
     * @throws IOException
     */
    @StreamListener(value = SmsSink.SMS_INPUT)
    public void receiveVeriCodeMessage(Message<String> message) throws IOException {
        String phoneNumber = message.getPayload();
        String txid = (String) message.getHeaders().get("TRANSACTION_ID");
        try {
            smsService.sendVerificationTo(phoneNumber, txid);
            log.info("发送给用户 => {} 短信，该消息已被消费", phoneNumber);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("用户{}验证码短信发送失败", phoneNumber);
        }
    }
}
