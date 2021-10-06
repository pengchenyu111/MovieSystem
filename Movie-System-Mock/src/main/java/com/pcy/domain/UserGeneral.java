package com.pcy.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author PengChenyu
 * @since 2021-10-06 16:19:15
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user_general")
public class UserGeneral implements Serializable {
    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户名
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 用户唯一名字标志，短评上没有id，以此做唯一标识
     */
    @TableField(value = "user_unique_name")
    private String userUniqueName;

    /**
     * 用户账户
     */
    @TableField(value = "account")
    private String account;

    /**
     * 账号密码，加密
     */
    @TableField(value = "password")
    private String password;

    /**
     * 用户邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 用户联系电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 用户性别
     */
    @TableField(value = "sex")
    private String sex;

    /**
     * 用户生日
     */
    @TableField(value = "birth")
    private String birth;

    /**
     * 用户年龄
     */
    @TableField(value = "age")
    private Integer age;

    /**
     * 用户职业
     */
    @TableField(value = "profession")
    private String profession;

    /**
     * 用户头像url
     */
    @TableField(value = "user_head_portrait_url")
    private String userHeadPortraitUrl;

    /**
     * 用户豆瓣主页链接
     */
    @TableField(value = "user_url")
    private String userUrl;

    private static final long serialVersionUID = 1L;

    public static final String COL_USER_ID = "user_id";

    public static final String COL_USER_NAME = "user_name";

    public static final String COL_USER_UNIQUE_NAME = "user_unique_name";

    public static final String COL_ACCOUNT = "account";

    public static final String COL_PASSWORD = "password";

    public static final String COL_EMAIL = "email";

    public static final String COL_PHONE = "phone";

    public static final String COL_SEX = "sex";

    public static final String COL_BIRTH = "birth";

    public static final String COL_AGE = "age";

    public static final String COL_PROFESSION = "profession";

    public static final String COL_USER_HEAD_PORTRAIT_URL = "user_head_portrait_url";

    public static final String COL_USER_URL = "user_url";
}