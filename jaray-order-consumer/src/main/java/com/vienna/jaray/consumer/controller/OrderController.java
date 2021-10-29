package com.vienna.jaray.consumer.controller;

import com.vienna.jaray.entity.consumer.Consumer;
import com.vienna.jaray.feign.producer.ProducerFeignService;
import com.vienna.jaray.service.consumer.ConsumerService;
import com.vienna.jaray.utils.common.HttpStatus;
import com.vienna.jaray.utils.common.ResponseResult;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
	
	@Autowired
	private ConsumerService consumerService;
	
	@Autowired
	private ProducerFeignService producerFeignService;

	@GlobalTransactional(rollbackFor = Exception.class)
	@GetMapping("/hello/say/{name}")
	public ResponseResult sayHello(@PathVariable(value="name") String name) throws Exception {
		Consumer consumer = new Consumer();
		consumer.setCname("1-" + name);
		consumerService.add(consumer);
		ResponseResult responseResult = producerFeignService.sayHello(name);
		if (HttpStatus.BUSINESS_EXCEPTION.getStatusCode() == responseResult.getCode()) {
			throw new RuntimeException(responseResult.getMsg());
		}
		return responseResult;
	}

}
