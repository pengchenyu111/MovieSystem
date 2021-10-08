package com.pcy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pcy.entity.vo.ElasticSearchVo;
import com.pcy.entity.vo.MovieDetailSearchRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcy.mapper.MovieDetailMapper;
import com.pcy.domain.MovieDetail;
import com.pcy.service.MovieDetailService;

/**
 * @author PengChenyu
 * @since 2021-10-08 17:42:56
 */
@Slf4j
@Service
public class MovieDetailServiceImpl extends ServiceImpl<MovieDetailMapper, MovieDetail> implements MovieDetailService {

    /**
     * 电影搜索的字段：电影名，演员，导演
     */
    private final String[] SEARCH_MOVIE_FIELDS = {"title", "casts", "directors"};
    /**
     * 搜索的索引
     */
    private final String SEARCH_INDEX = "movie_detail";
    /**
     * 搜索超时
     */
    private final Long SEARCH_TIMEOUT = 1L;

    @Resource
    MovieDetailMapper movieDetailMapper;

    @Resource
    BaseElasticSearchService baseElasticSearchService;


    /**
     * 分页获取电影详情
     *
     * @param pageNum  当前页
     * @param pageSize 每页多少数据
     * @return 分页数据
     */
    @Override
    public PageInfo<MovieDetail> queryPageMovie(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<MovieDetail> movieDetailList = movieDetailMapper.selectList(null);
        return new PageInfo<>(movieDetailList);
    }

    /**
     * 分页查询
     *
     * @param pageNum     当前页
     * @param pageSize    每页多少数据
     * @param movieDetail 查询条件
     * @return 分页数据
     */
    @Override
    public PageInfo<MovieDetail> queryPage(int pageNum, int pageSize, MovieDetail movieDetail) {
        PageHelper.startPage(pageNum, pageSize);
        LambdaQueryWrapper<MovieDetail> queryWrapper = new LambdaQueryWrapper<MovieDetail>()
                .eq(movieDetail.getDoubanId() != null, MovieDetail::getDoubanId, movieDetail.getDoubanId())
                .like(!StringUtils.isEmpty(movieDetail.getTitle()), MovieDetail::getTitle, movieDetail.getTitle())
                .like(!StringUtils.isEmpty(movieDetail.getDirectors()), MovieDetail::getDirectors, movieDetail.getDirectors())
                .like(!StringUtils.isEmpty(movieDetail.getScreenwriters()), MovieDetail::getScreenwriters, movieDetail.getScreenwriters())
                .like(!StringUtils.isEmpty(movieDetail.getCasts()), MovieDetail::getCasts, movieDetail.getCasts())
                .like(!StringUtils.isEmpty(movieDetail.getTypes()), MovieDetail::getTitle, movieDetail.getTitle())
                .like(!StringUtils.isEmpty(movieDetail.getProductionCountryArea()), MovieDetail::getProductionCountryArea, movieDetail.getProductionCountryArea())
                .like(!StringUtils.isEmpty(movieDetail.getLanguage()), MovieDetail::getLanguage, movieDetail.getLanguage())
                .like(!StringUtils.isEmpty(movieDetail.getPublishDate()), MovieDetail::getPublishDate, movieDetail.getPublishDate())
                .like(!StringUtils.isEmpty(movieDetail.getRuntime()), MovieDetail::getRuntime, movieDetail.getRuntime())
                .le(movieDetail.getRatingScore() != null, MovieDetail::getRatingScore, movieDetail.getRatingScore())
                .eq(movieDetail.getRatingNum() != null, MovieDetail::getRatingNum, movieDetail.getRatingNum());
        List<MovieDetail> movieDetailList = movieDetailMapper.selectList(queryWrapper);
        return new PageInfo<>(movieDetailList);
    }

    /**
     * 电影搜索
     *
     * @param keyword  搜索关键词
     * @param pageNum  第几页
     * @param pageSize 每页大小
     * @return ElasticSearchVo<MovieDetail>
     */
    @Override
    public ElasticSearchVo<MovieDetail> searchMovie(String keyword, int pageNum, int pageSize) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 多字段查询
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(keyword, this.SEARCH_MOVIE_FIELDS);
        searchSourceBuilder.query(multiMatchQueryBuilder);
        // 设置分页
        searchSourceBuilder.from(pageNum);
        searchSourceBuilder.size(pageSize);
        // 设置超时
        searchSourceBuilder.timeout(TimeValue.timeValueSeconds(SEARCH_TIMEOUT));
        // 开始搜索
        ElasticSearchVo<MovieDetail> result = new ElasticSearchVo<>();
        try {
            result = baseElasticSearchService.search(this.SEARCH_INDEX, searchSourceBuilder, MovieDetail.class);
        } catch (IOException e) {
            log.error("搜索出错:" + e.getMessage());
        }
        result.setPageNum(pageNum);
        result.setPageSize(pageSize);
        return result;
    }

    /**
     * 类豆瓣标签搜索
     * 这里返回的total不是实际的总数，具体应该以response.getResultList().size()为准
     *
     * @param movieDetailSearchRequest 请求条件实体
     * @return ES内电影数据
     */
    @Override
    public ElasticSearchVo<MovieDetail> searchByTags(MovieDetailSearchRequest movieDetailSearchRequest) {
        // 设置查询条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.matchQuery("types", movieDetailSearchRequest.getTypes()))
                .must(QueryBuilders.matchQuery("productionCountryArea", movieDetailSearchRequest.getProductionCountryArea()))
                .must(QueryBuilders.matchQuery("language", movieDetailSearchRequest.getLanguage()))
                .must(QueryBuilders.rangeQuery("ratingScore")
                        .gte(movieDetailSearchRequest.getRatingScoreLowerBound())
                        .lte(movieDetailSearchRequest.getRatingScoreUpperBound()));
        searchSourceBuilder.query(boolQueryBuilder);
        // 设置分页
        searchSourceBuilder.from(movieDetailSearchRequest.getPageNum() * movieDetailSearchRequest.getPageSize());
        searchSourceBuilder.size(movieDetailSearchRequest.getPageSize());
        // 发起请求并开始处理结果
        ElasticSearchVo<MovieDetail> result = new ElasticSearchVo<>();
        try {
            result = baseElasticSearchService.search(this.SEARCH_INDEX, searchSourceBuilder, MovieDetail.class);
        } catch (IOException e) {
            log.error("搜索出错:" + e.getMessage());
        }
        result.setPageNum(movieDetailSearchRequest.getPageNum());
        result.setPageSize(movieDetailSearchRequest.getPageSize());
        log.info("[ES标签搜索]===>" + result);
        return result;
    }
}
