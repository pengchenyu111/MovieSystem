package com.pcy.service.impl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcy.mapper.MovieDetailMapper;
import com.pcy.domain.MovieDetail;
import com.pcy.service.MovieDetailService;

/**
 * @author PengChenyu
 * @since 2021-10-06 14:58:37
 */
@Service
public class MovieDetailServiceImpl extends ServiceImpl<MovieDetailMapper, MovieDetail> implements MovieDetailService {

    @Resource
    MovieDetailMapper movieDetailMapper;

    /**
     * 获取所有的电影id
     *
     * @return
     */
    @Override
    public List<Integer> getAllMovieIdList() {
        return movieDetailMapper.selectAllMovieId();
    }
}

