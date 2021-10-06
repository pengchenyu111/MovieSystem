package com.pcy.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author PengChenyu
 * @since 2021-10-06 16:41:04
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "movie_user_rating")
public class MovieUserRating implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    @TableId(value = "review_id", type = IdType.INPUT)
    private String reviewId;

    /**
     * 豆瓣id
     */
    @TableField(value = "douban_id")
    private Integer doubanId;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 用户评分
     */
    @TableField(value = "user_movie_rating")
    private Double userMovieRating;

    /**
     * 评分时间
     */
    @TableField(value = "user_movie_rating_time")
    private String userMovieRatingTime;

}