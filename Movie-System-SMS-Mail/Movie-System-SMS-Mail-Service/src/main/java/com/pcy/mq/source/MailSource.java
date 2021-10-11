package com.pcy.mq.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author PengChenyu
 * @since 2021-10-11 14:23:35
 */
public interface MailSource {

    public static final String MAIL_OUTPUT = "mail-output";

    @Output(MailSource.MAIL_OUTPUT)
    MessageChannel mailOutput();
}
