package com.vienna.jaray.service.producer;

import com.vienna.jaray.entity.producer.Producer;

public interface ProducerService {

    /**
     * 添加信息
     * @param producer 对象
     * @return 添加结果
     */
    int add(Producer producer);
}
