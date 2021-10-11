package com.pcy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pcy.domain.MailSendRecord;
import com.pcy.vo.SmsSendRecordVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcy.mapper.VerificationCodeSendRecordMapper;
import com.pcy.domain.VerificationCodeSendRecord;
import com.pcy.service.VerificationCodeSendRecordService;

/**
 * @author PengChenyu
 * @since 2021-10-09 20:31:45
 */
@Service
public class VerificationCodeSendRecordServiceImpl extends ServiceImpl<VerificationCodeSendRecordMapper, VerificationCodeSendRecord> implements VerificationCodeSendRecordService {

    @Resource
    private VerificationCodeSendRecordMapper verificationCodeSendRecordMapper;

    /**
     * 验证码发送记录条件查询
     *
     * @param current         当前页
     * @param size            每页大小
     * @param smsSendRecordVo 查询条件
     * @return
     */
    @Override
    public Page<VerificationCodeSendRecord> querySendRecordPage(int current, int size, SmsSendRecordVo smsSendRecordVo) {
        Page<VerificationCodeSendRecord> page = new Page<>(current, size);
        LambdaQueryWrapper<VerificationCodeSendRecord> queryWrapper = new LambdaQueryWrapper<VerificationCodeSendRecord>()
                .eq(smsSendRecordVo.getId() != null, VerificationCodeSendRecord::getId, smsSendRecordVo.getId())
                .like(!StringUtils.isEmpty(smsSendRecordVo.getPhoneNumber()), VerificationCodeSendRecord::getPhoneNumber, smsSendRecordVo.getPhoneNumber())
                .ge(smsSendRecordVo.getStartTime() != null, VerificationCodeSendRecord::getSendTime, smsSendRecordVo.getStartTime())
                .le(smsSendRecordVo.getEndTime() != null, VerificationCodeSendRecord::getSendTime, smsSendRecordVo.getEndTime())
                .eq(!StringUtils.isEmpty(smsSendRecordVo.getSuccessFlag()), VerificationCodeSendRecord::getSuccessFlag, smsSendRecordVo.getSuccessFlag());
        return verificationCodeSendRecordMapper.selectPage(page, queryWrapper);
    }
}
