package com.vienna.jaray.service.consumer;

import com.vienna.jaray.entity.consumer.Consumer;

public interface ConsumerService {

    /**
     * 添加信息
     * @param consumer 对象
     * @return 添加结果
     */
    int add(Consumer consumer);
}
