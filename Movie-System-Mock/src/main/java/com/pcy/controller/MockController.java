package com.pcy.controller;

import com.pcy.mock.log.RatingLogMock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author PengChenyu
 * @since 2021-10-06 16:27:37
 */

@RestController
@RequestMapping("movie-system/mock")
public class MockController {

    @Resource
    RatingLogMock ratingLogMock;

    @GetMapping("/start-rating-mock")
    public void startRatingLogMock() {
        ratingLogMock.startRatingMock();
    }

    @GetMapping("/stop-rating-mock")
    public void stopRatingLogMock() {
        ratingLogMock.stopRatingMock();
    }
}
