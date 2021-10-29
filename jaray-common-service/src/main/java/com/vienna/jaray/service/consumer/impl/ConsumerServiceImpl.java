package com.vienna.jaray.service.consumer.impl;

import com.vienna.jaray.entity.consumer.Consumer;
import com.vienna.jaray.mapper.consumer.ConsumerMapper;
import com.vienna.jaray.service.consumer.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Autowired
    private ConsumerMapper consumerMapper;

    @Override
    public int add(Consumer consumer) {
        int result = 0;
        result = consumerMapper.add(consumer);
        return result;
    }
}
