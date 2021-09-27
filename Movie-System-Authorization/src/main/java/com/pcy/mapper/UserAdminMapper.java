package com.pcy.mapper;

import com.pcy.domain.UserAdmin;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author PengChenyu
 * @since 2021-06-30 15:55:44
 */
@Mapper
public interface UserAdminMapper {

    /**
     * 通过 id 去找 username
     *
     * @param id
     * @return
     */
    String selectUsernameById(String id);

    /**
     * 通过 username 去找管理员的全部信息
     *
     * @param username
     * @return
     */
    UserAdmin selectSysUserByUsername(String username);
}