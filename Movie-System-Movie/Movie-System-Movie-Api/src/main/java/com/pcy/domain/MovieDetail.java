package com.pcy.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author PengChenyu
 * @since 2021-10-08 17:42:56
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "movie_detail")
public class MovieDetail implements Serializable {
    /**
     * 豆瓣id
     */
    @TableId(value = "douban_id", type = IdType.INPUT)
    private Integer doubanId;

    /**
     * 电影名
     */
    @TableField(value = "title")
    private String title;

    /**
     * 电影简介
     */
    @TableField(value = "brief_instruction")
    private String briefInstruction;

    /**
     * 导演列表,/分割，注意两边有空格
     */
    @TableField(value = "directors")
    private String directors;

    /**
     * 编剧列表,/分割，注意两边有空格
     */
    @TableField(value = "screenwriters")
    private String screenwriters;

    /**
     * 演员列表,/分割，注意两边有空格
     */
    @TableField(value = "casts")
    private String casts;

    /**
     * 类型列表,/分割，注意两边有空格
     */
    @TableField(value = "types")
    private String types;

    /**
     * 制片国家/地区
     */
    @TableField(value = "production_country_area")
    private String productionCountryArea;

    /**
     * 语言
     */
    @TableField(value = "language")
    private String language;

    /**
     * 上映日期列表,/分割，注意两边有空格
     */
    @TableField(value = "publish_date")
    private String publishDate;

    /**
     * 片长
     */
    @TableField(value = "runtime")
    private String runtime;

    /**
     * 评分分数，10分制
     */
    @TableField(value = "rating_score")
    private Double ratingScore;

    /**
     * 评分星级，5星制
     */
    @TableField(value = "rating_star")
    private Integer ratingStar;

    /**
     * 评分人数
     */
    @TableField(value = "rating_num")
    private Integer ratingNum;

    /**
     * 评5星占比
     */
    @TableField(value = "rating_5_star_weight")
    private String rating5StarWeight;

    /**
     * 评4星占比
     */
    @TableField(value = "rating_4_star_weight")
    private String rating4StarWeight;

    /**
     * 评3星占比
     */
    @TableField(value = "rating_3_star_weight")
    private String rating3StarWeight;

    /**
     * 评2星占比
     */
    @TableField(value = "rating_2_star_weight")
    private String rating2StarWeight;

    /**
     * 评1星占比
     */
    @TableField(value = "rating_1_star_weight")
    private String rating1StarWeight;

    /**
     * 好于其他类型影片占比，列表
     */
    @TableField(value = "better_than")
    private String betterThan;

    /**
     * 豆瓣电影链接
     */
    @TableField(value = "douban_url")
    private String doubanUrl;

    /**
     * 电影海报链接
     */
    @TableField(value = "cover_url")
    private String coverUrl;

    /**
     * IMDb链接
     */
    @TableField(value = "imdb_url")
    private String imdbUrl;

    /**
     * 电影图片列表，逗号分割
     */
    @TableField(value = "img_list")
    private String imgList;

    private static final long serialVersionUID = 1L;
}