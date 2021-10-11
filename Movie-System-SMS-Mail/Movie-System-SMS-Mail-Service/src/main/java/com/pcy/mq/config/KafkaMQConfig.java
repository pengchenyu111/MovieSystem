package com.pcy.mq.config;

import com.pcy.mq.sink.MailSink;
import com.pcy.mq.sink.SmsSink;
import com.pcy.mq.source.MailSource;
import com.pcy.mq.source.SmsSource;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Configuration;

/**
 * RocketMQ配置，配置Source和Sink
 *
 * @author PengChenyu
 * @since 2021-07-10 16:14:33
 */
@Configuration
@EnableBinding({MailSource.class, SmsSource.class, SmsSink.class, MailSink.class})
public class KafkaMQConfig {
}
