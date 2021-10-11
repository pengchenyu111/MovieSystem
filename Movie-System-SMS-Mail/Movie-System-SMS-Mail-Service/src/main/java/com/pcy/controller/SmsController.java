package com.pcy.controller;

import com.pcy.constant.ErrorCodeMsg;
import com.pcy.model.ResponseObject;
import com.pcy.mq.source.SmsSource;
import com.pcy.service.SmsService;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author PengChenyu
 * @since 2021-07-08 11:45:16
 */
@RestController
@RequestMapping("sms")
public class SmsController {

    @Resource
    private SmsSource smsSource;


    @GetMapping("/send/vericode/{phoneNumber}")
    public ResponseObject<Boolean> send(@PathVariable("phoneNumber") String phoneNumber) throws Exception {
        String txId = UUID.randomUUID().toString();
        Message<String> message = MessageBuilder.withPayload(phoneNumber)
                .setHeader("TRANSACTION_ID", txId)
                .build();
        boolean flag = smsSource.smsOutput().send(message);
        return ResponseObject.success(ErrorCodeMsg.OK_CODE, ErrorCodeMsg.OK_MESSAGE, flag);
    }

}
