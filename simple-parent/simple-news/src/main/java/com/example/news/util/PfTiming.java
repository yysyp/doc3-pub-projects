package com.example.news.util;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class PfTiming {

    long start;

    private PfTiming() {
        this.reset();
    }

    public static PfTiming start() {
        return new PfTiming();
    }

    public PfTiming reset() {
        this.start = System.nanoTime();
        return this;
    }

    public long time() {
        long end = System.nanoTime();
        return end - start;
    }

    public long time(TimeUnit timeUnit) {
        return timeUnit.convert(time(), TimeUnit.NANOSECONDS);
    }

    public long timeMs() {
        return time(TimeUnit.MILLISECONDS);
    }

}
