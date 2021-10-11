package com.pcy.mq.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author PengChenyu
 * @since 2021-10-11 14:23:08
 */
public interface SmsSource {

    public static final String SMS_OUTPUT = "sms-output";

    @Output(SmsSource.SMS_OUTPUT)
    MessageChannel smsOutput();
}
