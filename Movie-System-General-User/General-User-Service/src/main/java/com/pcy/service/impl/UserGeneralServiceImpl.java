package com.pcy.service.impl;

import com.pcy.domain.UserGeneral;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcy.mapper.UserGeneralMapper;
import com.pcy.service.UserGeneralService;

/**
 * @author PengChenyu
 * @since 2021-07-01 20:43:24
 */
@Service
public class UserGeneralServiceImpl extends ServiceImpl<UserGeneralMapper, UserGeneral> implements UserGeneralService {

}
