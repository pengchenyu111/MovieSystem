package com.pcy.service;

import com.pcy.domain.MailTemplate;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pcy.vo.MailTemplateVo;

import java.util.List;

/**
 * @author PengChenyu
 * @since 2021-10-09 20:31:45
 */
public interface MailTemplateService extends IService<MailTemplate> {


    /**
     * 邮件模板分页查询
     *
     * @param mailTemplateVo 查询条件
     * @return
     */
    List<MailTemplate> queryMailTemplate(MailTemplateVo mailTemplateVo);
}
