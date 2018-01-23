package com.cxxy.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncTask {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Async("msgTaskAsyncPool")
    public void doTask1(int i) throws Exception{
        logger.info("Task"+i+" started.");
    }
}
