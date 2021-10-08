package com.pcy.service;

import com.github.pagehelper.PageInfo;
import com.pcy.domain.MovieDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pcy.entity.vo.ElasticSearchVo;
import com.pcy.entity.vo.MovieDetailSearchRequest;

/**
 * @author PengChenyu
 * @since 2021-10-08 17:42:56
 */
public interface MovieDetailService extends IService<MovieDetail> {

    /**
     * 分页获取电影详情
     *
     * @param pageNum  当前页
     * @param pageSize 每页多少数据
     * @return 分页数据
     */
    PageInfo<MovieDetail> queryPageMovie(int pageNum, int pageSize);

    /**
     * 分页查询
     *
     * @param pageNum     当前页
     * @param pageSize    每页多少数据
     * @param movieDetail 查询条件
     * @return 分页数据
     */
    PageInfo<MovieDetail> queryPage(int pageNum, int pageSize, MovieDetail movieDetail);


    /**
     * 电影搜索
     *
     * @param keyword  搜索关键词
     * @param pageNum  第几页
     * @param pageSize 每页大小
     * @return ElasticSearchVo<MovieDetail>
     */
    ElasticSearchVo<MovieDetail> searchMovie(String keyword, int pageNum, int pageSize);


    /**
     * 类豆瓣标签搜索
     *
     * @param movieDetailSearchRequest 请求条件实体
     * @return ES内电影数据
     */
    ElasticSearchVo<MovieDetail> searchByTags(MovieDetailSearchRequest movieDetailSearchRequest);


}
