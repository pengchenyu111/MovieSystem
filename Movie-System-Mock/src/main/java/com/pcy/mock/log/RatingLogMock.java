package com.pcy.mock.log;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pcy.domain.MovieUserRating;
import com.pcy.service.MovieDetailService;
import com.pcy.service.UserGeneralService;
import com.pcy.util.IdWorkerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 用户评分模拟工具
 *
 * @author PengChenyu
 * @since 2021-10-06 13:58:05
 */
@Slf4j
@Component
public class RatingLogMock {

    public static final int PER_GENERATE_RECORD_NUM = 10000;
    public static final int THREAD_NUM = 10;

    @Resource
    private MovieDetailService movieDetailService;
    @Resource
    private UserGeneralService userGeneralService;


    ThreadFactory threadFactory = new CustomizableThreadFactory("rating-mock-thread-pool-");
    ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(THREAD_NUM, threadFactory);

    IdWorkerUtil idWorkerUtil = new IdWorkerUtil();
    ObjectMapper objectMapper = new ObjectMapper();


    public void startRatingMock() {
        List<Integer> movieIdList = movieDetailService.getAllMovieIdList();
        List<Integer> userIdList = userGeneralService.getAllUserId();

        Random random = new Random();

        // 每次写入10000条评分数据
        Runnable ratingLogGenerateTask = () -> {
            for (int i = 0; i < PER_GENERATE_RECORD_NUM; i++) {
                MovieUserRating ratingRecord = MovieUserRating.builder()
                        .reviewId(String.valueOf(idWorkerUtil.nextId()))
                        .doubanId(random.nextInt(movieIdList.size()))
                        .userId(random.nextInt(userIdList.size()))
                        .userMovieRating((double) (random.nextInt(5) + 1))
                        .userMovieRatingTime(DateUtil.now())
                        .build();
                try {
                    System.out.println(objectMapper.writeValueAsString(ratingRecord));
                    log.info(objectMapper.writeValueAsString(ratingRecord));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        };

        // 线程一次执行的时间小于间隔时间，使用scheduleAtFixedRate方法
        // 每秒钟写入10000条数据，间隔1秒
        scheduled.scheduleAtFixedRate(ratingLogGenerateTask, 0, 1000, TimeUnit.MILLISECONDS);
    }

    public void stopRatingMock() {
        scheduled.shutdown();
        // 设定最大重试次数
        try {
            // 等待 60 s
            if (!scheduled.awaitTermination(60, TimeUnit.SECONDS)) {
                // 调用 shutdownNow 取消正在执行的任务
                scheduled.shutdownNow();
                // 再次等待 60 s
                if (!scheduled.awaitTermination(60, TimeUnit.SECONDS)) {
                    System.out.println("线程池任务未正常执行结束");
                }
            }
        } catch (InterruptedException ie) {
            scheduled.shutdownNow();
        }
    }
}
