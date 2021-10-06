package com.pcy.service;

import com.pcy.domain.UserGeneral;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 
 * @author PengChenyu
 * @since 2021-10-06 16:19:15
 */
public interface UserGeneralService extends IService<UserGeneral>{

    /**
     * 获取所有用户id
     * @return
     */
    List<Integer> getAllUserId();

}
