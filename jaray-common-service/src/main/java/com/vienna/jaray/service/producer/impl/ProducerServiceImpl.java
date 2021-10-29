package com.vienna.jaray.service.producer.impl;

import com.vienna.jaray.entity.producer.Producer;
import com.vienna.jaray.mapper.producer.ProducerMapper;
import com.vienna.jaray.service.producer.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducerServiceImpl implements ProducerService {
    @Autowired
    private ProducerMapper producerMapper;
    @Override
    public int add(Producer producer) {
        int result = 0;
        result = producerMapper.add(producer);
        return result;
    }
}
