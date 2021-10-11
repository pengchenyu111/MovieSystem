package com.pcy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pcy.domain.MailSendRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pcy.vo.MailSendRecordVo;

/**
 * @author PengChenyu
 * @since 2021-10-09 20:31:45
 */
public interface MailSendRecordService extends IService<MailSendRecord> {


    /**
     * 邮件发送记录分页查询
     *
     * @param current          当前页
     * @param size             每页大小
     * @param mailSendRecordVo 查询条件
     * @return
     */
    Page<MailSendRecord> queryMailSendRecordPage(int current, int size, MailSendRecordVo mailSendRecordVo);
}
