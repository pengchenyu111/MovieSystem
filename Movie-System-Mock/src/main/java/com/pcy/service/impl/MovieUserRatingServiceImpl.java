package com.pcy.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pcy.domain.MovieUserRating;
import com.pcy.mapper.MovieUserRatingMapper;
import com.pcy.service.MovieUserRatingService;
/**
 * 
 * @author PengChenyu
 * @since 2021-10-06 16:41:04
 */
@Service
public class MovieUserRatingServiceImpl extends ServiceImpl<MovieUserRatingMapper, MovieUserRating> implements MovieUserRatingService{

}
