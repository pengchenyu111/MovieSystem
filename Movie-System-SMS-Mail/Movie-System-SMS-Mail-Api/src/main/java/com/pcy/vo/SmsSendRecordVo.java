package com.pcy.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2021-10-10 14:29:51
 */
@ApiModel(value = "com-pcy-vo-SmsSendRecordVo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmsSendRecordVo implements Serializable {

    /**
     * 验证码短信id
     */
    @ApiModelProperty(value = "验证码短信id")
    private Long id;

    /**
     * 发送目标电话号码
     */
    @ApiModelProperty(value = "发送目标电话号码")
    private String phoneNumber;

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

    private static final long serialVersionUID = 1L;
}
