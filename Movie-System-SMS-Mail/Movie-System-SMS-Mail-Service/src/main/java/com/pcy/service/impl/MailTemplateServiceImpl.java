package com.pcy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pcy.vo.MailTemplateVo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcy.mapper.MailTemplateMapper;
import com.pcy.domain.MailTemplate;
import com.pcy.service.MailTemplateService;

/**
 * @author PengChenyu
 * @since 2021-10-09 20:31:45
 */
@Service
public class MailTemplateServiceImpl extends ServiceImpl<MailTemplateMapper, MailTemplate> implements MailTemplateService {

    @Resource
    MailTemplateMapper mailTemplateMapper;

    /**
     * 邮件模板分页查询
     *
     * @param mailTemplateVo 查询条件
     * @return
     */
    @Override
    public List<MailTemplate> queryMailTemplate(MailTemplateVo mailTemplateVo) {
        LambdaQueryWrapper<MailTemplate> queryWrapper = new LambdaQueryWrapper<MailTemplate>()
                .eq(mailTemplateVo.getId() != null, MailTemplate::getId, mailTemplateVo.getId())
                .like(!StringUtils.isEmpty(mailTemplateVo.getTemplateName()), MailTemplate::getTemplateName, mailTemplateVo.getTemplateName())
                .like(!StringUtils.isEmpty(mailTemplateVo.getTemplateType()), MailTemplate::getTemplateType, mailTemplateVo.getTemplateType())
                .eq(!StringUtils.isEmpty(mailTemplateVo.getStatus()), MailTemplate::getStatus, mailTemplateVo.getStatus());
        return mailTemplateMapper.selectList(queryWrapper);
    }
}
