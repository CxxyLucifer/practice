package com.cxxy.practice.config.threadPool;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Author:liuhui
 * Description:
 * Date: 上午9:39 2018/1/23
 */
@Data
@ConfigurationProperties(prefix = "spring.task.pool")
public class TaskThreadPoolConfig {

    private int corePoolSize;

    private int maxPoolSize;

    private int keepAliveSeconds;

    private int queueCapacity;
}
