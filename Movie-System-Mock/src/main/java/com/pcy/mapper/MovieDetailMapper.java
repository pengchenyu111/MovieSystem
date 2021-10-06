package com.pcy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pcy.domain.MovieDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author PengChenyu
 * @since 2021-10-06 14:59:07
 */
@Mapper
public interface MovieDetailMapper extends BaseMapper<MovieDetail> {
    /**
     * 获取所有电影id
     *
     * @return
     */
    List<Integer> selectAllMovieId();
}