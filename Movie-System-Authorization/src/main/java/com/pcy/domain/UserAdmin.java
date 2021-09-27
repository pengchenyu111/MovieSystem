package com.pcy.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author PengChenyu
 * @since 2021-06-30 15:55:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAdmin implements Serializable {
    private static final long serialVersionUID = 7569690583918739230L;

    private Long id;

    private String username;

    private String password;

    private String fullname;

    private String mobile;

    private String email;

    private Byte status;

    private Long createBy;

    private Long modifyBy;

    private Date created;

    private Date lastUpdateTime;
}