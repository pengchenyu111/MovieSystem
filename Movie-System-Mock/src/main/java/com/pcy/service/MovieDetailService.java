package com.pcy.service;

import com.pcy.domain.MovieDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PengChenyu
 * @since 2021-10-06 14:58:37
 */
public interface MovieDetailService extends IService<MovieDetail> {

    /**
     * 获取所有的电影id
     * @return
     */
    List<Integer> getAllMovieIdList();


}

