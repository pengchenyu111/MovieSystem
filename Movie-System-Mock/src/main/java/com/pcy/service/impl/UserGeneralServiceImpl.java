package com.pcy.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcy.mapper.UserGeneralMapper;
import com.pcy.domain.UserGeneral;
import com.pcy.service.UserGeneralService;
/**
 * 
 * @author PengChenyu
 * @since 2021-10-06 16:19:15
 */
@Service
public class UserGeneralServiceImpl extends ServiceImpl<UserGeneralMapper, UserGeneral> implements UserGeneralService{

    @Resource
    UserGeneralMapper userGeneralMapper;
    /**
     * 获取所有用户id
     *
     * @return
     */
    @Override
    public List<Integer> getAllUserId() {
        return userGeneralMapper.selectAllUserId();
    }
}
