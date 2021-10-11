package com.pcy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pcy.domain.MailSendRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author PengChenyu
 * @since 2021-10-09 20:31:45
 */
@Mapper
public interface MailSendRecordMapper extends BaseMapper<MailSendRecord> {
}