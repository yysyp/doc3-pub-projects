package com.example.news.buzz.service.impl;


import com.example.news.exception.ApiBaseException;
import com.example.news.exception.CodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

@Slf4j
@Service
public class RetryService {

    @Autowired
    private ThreadService threadService;

    @Retryable(value = {ArithmeticException.class},
            maxAttemptsExpression = "${retry.maxAttempts}",
            backoff = @Backoff(delayExpression = "${retry.delay}",
                    maxDelayExpression = "${retry.maxDelay}",
                    multiplierExpression = "${retry.multiplier}"))
    public BigDecimal divide(BigDecimal d1, BigDecimal d2) {
        boolean bool = new Random().nextBoolean();
        log.info("SN doing divide, d1={}, d2={}, bool={}", d1, d2, bool);
        if (bool) {
            BigDecimal result = d1.divide(d2, RoundingMode.HALF_UP);
            long millionSecons = RandomUtils.nextLong(1000, 12000);
            log.info("SN thread going to sleep for millionSeconds={}", millionSecons);
            try {
                Thread.sleep(millionSecons);
            } catch (InterruptedException e) {
                log.info("Thread sleep interrupted, e={}", e.getMessage());
            }
            log.info("SN divide success, millionSecons={} result={}", millionSecons, result);
            return result;
        } else {
            //To throw RuntimeException
            return d1.divide(new BigDecimal("0"));
        }
    }

    @Recover
    public BigDecimal divideRecover(BigDecimal d1, BigDecimal d2) {
        throw new ApiBaseException(CodeEnum.INTERNAL_SERVER_ERROR, true, "Bad luck!");
    }

}
