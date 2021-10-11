package com.pcy.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author PengChenyu
 * @since 2021-07-12 20:40:46
 */
@ApiModel(value = "com-pcy-vo-MailSendRecordVo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailSendRecordVo implements Serializable {

    private static final long serialVersionUID = 4240842383661646147L;

    /**
     * 邮件id
     */
    @ApiModelProperty(value = "邮件id")
    private Long id;

    /**
     * 发信地址
     */
    @ApiModelProperty(value = "发信地址")
    private String accountName;

    /**
     * 目标收件人地址
     */
    @ApiModelProperty(value = "目标收件人地址")
    private String toAddress;

    /**
     * 邮件主题
     */
    @ApiModelProperty(value = "邮件主题")
    private String subject;

    /**
     * 邮件标签
     */
    @ApiModelProperty(value = "邮件标签")
    private String tagName;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    /**
     * 发送是否成功标志，1成功，0失败
     */
    @ApiModelProperty(value = "发送是否成功标志，1成功，0失败")
    private String successFlag;

    /**
     * 阿里云请求id
     */
    @ApiModelProperty(value = "阿里云请求id")
    private String requestId;


}
