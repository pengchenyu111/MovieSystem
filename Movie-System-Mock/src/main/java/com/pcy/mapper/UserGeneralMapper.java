package com.pcy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pcy.domain.UserGeneral;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * @author PengChenyu
 * @since 2021-10-06 16:19:15
 */
@Mapper
public interface UserGeneralMapper extends BaseMapper<UserGeneral> {

    /**
     * 获取所有用户id
     * @return
     */
    List<Integer> selectAllUserId();
}