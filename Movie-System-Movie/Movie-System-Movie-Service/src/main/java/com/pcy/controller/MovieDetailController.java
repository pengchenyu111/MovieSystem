package com.pcy.controller;


import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.PageInfo;
import com.pcy.constant.ErrorCodeMsg;
import com.pcy.domain.MovieDetail;
import com.pcy.entity.vo.ElasticSearchVo;
import com.pcy.entity.vo.MovieDetailSearchRequest;
import com.pcy.model.ResponseObject;
import com.pcy.service.MovieDetailService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (MovieDetail)表控制层
 *
 * @author PengChenyu
 * @since 2020-12-21 21:41:53
 */
@Api(value = "/movieDetail", tags = "movieDetail")
@RestController
@RequestMapping("api/movie/movieDetail")
public class MovieDetailController {
    /**
     * 服务对象
     */
    @Resource
    private MovieDetailService movieDetailService;

    /**
     * 通过主键查询单条数据
     *
     * @param doubanId 主键
     * @return 单条数据
     */
    @ApiOperation(value = "主键查询", notes = "通过主键查询单条数据")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "doubanId", value = "豆瓣id", required = true, dataType = "Integer")
    })
    @GetMapping("/{doubanId}")
    public ResponseObject<MovieDetail> selectOne(@PathVariable("doubanId") Integer doubanId) {
        MovieDetail movieDetail = this.movieDetailService.getById(doubanId);
        if (movieDetail == null) {
            return ResponseObject.failed(ErrorCodeMsg.QUERY_SUCCESS_CODE, ErrorCodeMsg.QUERY_NULL_MESSAGE, null);
        }
        return ResponseObject.success(ErrorCodeMsg.QUERY_SUCCESS_CODE, ErrorCodeMsg.QUERY_SUCCESS_MESSAGE, movieDetail);
    }


    /**
     * 分页获取电影详情
     *
     * @param pageNum  当前页
     * @param pageSize 每页多少数据
     * @return 分页数据
     */
    @ApiOperation(value = "分页获取电影详情")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "当前页", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页的数量", required = true, dataType = "int")
    })
    @GetMapping("/page/{pageNum}/{pageSize}")
    public ResponseObject<PageInfo<MovieDetail>> queryPageMovie(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        PageInfo<MovieDetail> movieDetailPageInfo = movieDetailService.queryPageMovie(pageNum, pageSize);
        if (movieDetailPageInfo.getTotal() == 0L) {
            return ResponseObject.failed(ErrorCodeMsg.QUERY_SUCCESS_CODE, ErrorCodeMsg.QUERY_NULL_MESSAGE, null);
        }
        return ResponseObject.success(ErrorCodeMsg.QUERY_SUCCESS_CODE, ErrorCodeMsg.QUERY_SUCCESS_MESSAGE, movieDetailPageInfo);
    }


    /**
     * 分页查询
     *
     * @param pageNum     当前页
     * @param pageSize    每页多少数据
     * @param movieDetail 查询条件
     * @return 分页数据
     */
    @ApiOperation(value = "分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "当前页", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页的数量", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "movieDetail", value = "查询条件", required = true, dataType = "MovieDetail")
    })
    @PostMapping("/page/{pageNum}/{pageSize}")
    public ResponseObject<PageInfo<MovieDetail>> queryPage(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize, @RequestBody MovieDetail movieDetail) {
        PageInfo<MovieDetail> movieDetailPageInfo = movieDetailService.queryPage(pageNum, pageSize, movieDetail);
        if (movieDetailPageInfo.getTotal() == 0L) {
            return ResponseObject.failed(ErrorCodeMsg.QUERY_SUCCESS_CODE, ErrorCodeMsg.QUERY_NULL_MESSAGE, null);
        }
        return ResponseObject.success(ErrorCodeMsg.QUERY_SUCCESS_CODE, ErrorCodeMsg.QUERY_SUCCESS_MESSAGE, movieDetailPageInfo);
    }


    /**
     * 搜索，电影名/导演/演员
     *
     * @param pageNum  当前页
     * @param pageSize 每页多少数据
     * @param map      keyword 用户搜索的关键字
     * @return 分页数据
     */
    @ApiOperation(value = "分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "当前页", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页的数量", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "map", value = "用户搜索的关键字", required = true, dataType = "Map")
    })
    @PostMapping("/search/{pageNum}/{pageSize}")
    public ResponseObject<ElasticSearchVo<MovieDetail>> searchMovie(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize, @RequestBody Map<String, String> map) {
        String keyword = map.get("keyword");
        ElasticSearchVo<MovieDetail> movieDetailElasticSearchVo = movieDetailService.searchMovie(keyword, pageNum, pageSize);
        if (movieDetailElasticSearchVo.getTotal() == 0) {
            return ResponseObject.failed(ErrorCodeMsg.QUERY_SUCCESS_CODE, ErrorCodeMsg.QUERY_NULL_MESSAGE, null);
        }
        return ResponseObject.success(ErrorCodeMsg.QUERY_SUCCESS_CODE, ErrorCodeMsg.QUERY_SUCCESS_MESSAGE, movieDetailElasticSearchVo);
    }


    /**
     * 类豆瓣标签搜索
     * 基于ES
     *
     * @param movieDetailSearchRequest 请求条件实体
     * @return ES内电影数据
     */
    @ApiOperation(value = "类豆瓣标签搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "movieDetailSearchRequest", value = "doubanId", required = true, dataType = "MovieDetailSearchRequest")
    })
    @PostMapping("/searchByTags")
    public ResponseObject<ElasticSearchVo<MovieDetail>> searchByTags(@RequestBody MovieDetailSearchRequest movieDetailSearchRequest) {
        ElasticSearchVo<MovieDetail> result = movieDetailService.searchByTags(movieDetailSearchRequest);
        if (CollectionUtil.isEmpty(result.getResultList())) {
            return ResponseObject.failed(ErrorCodeMsg.QUERY_SUCCESS_CODE, ErrorCodeMsg.QUERY_NULL_MESSAGE, null);
        }
        return ResponseObject.success(ErrorCodeMsg.QUERY_SUCCESS_CODE, ErrorCodeMsg.QUERY_SUCCESS_MESSAGE, result);
    }

}