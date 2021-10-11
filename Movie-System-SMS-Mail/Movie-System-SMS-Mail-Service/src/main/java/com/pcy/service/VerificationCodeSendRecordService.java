package com.pcy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pcy.domain.VerificationCodeSendRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pcy.vo.SmsSendRecordVo;

/**
 * @author PengChenyu
 * @since 2021-10-09 20:31:45
 */
public interface VerificationCodeSendRecordService extends IService<VerificationCodeSendRecord> {

    /**
     * 验证码发送记录条件查询
     *
     * @param current         当前页
     * @param size            每页大小
     * @param smsSendRecordVo 查询条件
     * @return
     */
    Page<VerificationCodeSendRecord> querySendRecordPage(int current, int size, SmsSendRecordVo smsSendRecordVo);
}
