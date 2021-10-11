package com.pcy.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pcy.constant.ErrorCodeMsg;
import com.pcy.domain.MailSendRecord;
import com.pcy.domain.VerificationCodeSendRecord;
import com.pcy.model.ResponseObject;
import com.pcy.service.VerificationCodeSendRecordService;
import com.pcy.vo.SmsSendRecordVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author PengChenyu
 * @since 2021-10-10 14:23:09
 */
@RestController
@RequestMapping("sms_mail/sms_record")
@Api(value = "sms_record", tags = "短信发送记录详情")
public class SmsSendRecordController {

    @Resource
    private VerificationCodeSendRecordService verificationCodeSendRecordService;

    @ApiOperation(value = "验证码短信发送记录条件查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页"),
            @ApiImplicitParam(name = "size", value = "每页大小"),
            @ApiImplicitParam(name = "mailTemplateVo", value = "查询条件")
    })
    @PostMapping("/vericode/page/current/{current}/size/{size}")
    public ResponseObject<Page<VerificationCodeSendRecord>> queryVeriCodeRecord(@PathVariable("current") int current, @PathVariable("size") int size, @RequestBody SmsSendRecordVo smsSendRecordVo) {
        Page<VerificationCodeSendRecord> sendRecordPage = verificationCodeSendRecordService.querySendRecordPage(current, size, smsSendRecordVo);
        if (sendRecordPage.getTotal() == 0) {
            return ResponseObject.failed(ErrorCodeMsg.QUERY_NULL_CODE, ErrorCodeMsg.QUERY_NULL_MESSAGE, null);
        }
        return ResponseObject.success(ErrorCodeMsg.QUERY_SUCCESS_CODE, ErrorCodeMsg.QUERY_SUCCESS_MESSAGE, sendRecordPage);

    }
}
