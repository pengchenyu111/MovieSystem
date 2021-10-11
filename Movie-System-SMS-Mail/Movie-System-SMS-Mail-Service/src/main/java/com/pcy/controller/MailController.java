package com.pcy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pcy.constant.ErrorCodeMsg;
import com.pcy.model.ResponseObject;
import com.pcy.mq.source.MailSource;
import com.pcy.service.MailTemplateService;
import com.pcy.vo.MailMessageVo;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author PengChenyu
 * @since 2021-07-10 14:26:27
 */
@RestController
@RequestMapping("mail")
public class MailController {

    @Resource
    private MailTemplateService mailTemplateService;

    @Resource
    private KafkaTemplate kafkaTemplate;

    @Resource
    private MailSource mailSource;

    @PostMapping("/send")
    public ResponseObject<Boolean> send(@RequestBody MailMessageVo mailMessageVo) throws Exception {
        // 下面这些代码应当放在某个服务的service里
        // 这里为了测试方面就直接写在controller里
        mailMessageVo.setMailHTMLBody(mailTemplateService.getById(1).getTemplateBody());
        ObjectMapper objectMapper = new ObjectMapper();
        String mailMessageStr = objectMapper.writeValueAsString(mailMessageVo);
        String txId = UUID.randomUUID().toString();
        Message<String> message = MessageBuilder.withPayload(mailMessageStr).setHeader("TRANSACTION_ID", txId).build();
        boolean flag = mailSource.mailOutput().send(message);
        //ListenableFuture listenableFuture = kafkaTemplate.send("mail-topic", mailMessageStr);
        return ResponseObject.success(ErrorCodeMsg.OK_CODE, ErrorCodeMsg.OK_MESSAGE, flag);
    }
}
