package com.vienna.jaray.producer.controller;

import com.vienna.jaray.entity.producer.Producer;
import com.vienna.jaray.feign.consumer.ConsumerFeignService;
import com.vienna.jaray.mapper.producer.ProducerMapper;
import com.vienna.jaray.config.PropConfig;
import com.vienna.jaray.service.producer.ProducerService;
import com.vienna.jaray.utils.common.HttpStatus;
import com.vienna.jaray.utils.common.ResponseResult;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderProducerController {
	
	@Value("${server.port}")
	private String port;

	@Autowired
	private ConsumerFeignService consumerFeignService;
	
	@Autowired
	private ProducerService producerService;
	
	@Autowired
	private PropConfig propConfig;
	
	@GetMapping("/hello/say/{name}")
	public ResponseResult sayHello(@PathVariable(value="name") String name) {
		Producer producer = new Producer();
		producer.setPname(name);
		producerService.add(producer);
//		try {
//			Thread.sleep(30000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		int i = 1/0;
		String msg = "Hello "+ name + " " + port + " " + propConfig.getName();
		return ResponseResult.success().add("msg", msg);
	}

	@GetMapping("/hello/say2/{name}")
	@GlobalTransactional
	public ResponseResult sayHello2(@PathVariable(value="name") String name) {
		Producer producer = new Producer();
		producer.setPname("2-" + name);
		producerService.add(producer);
		ResponseResult responseResult = consumerFeignService.sayHello2(name);
		if (HttpStatus.BUSINESS_EXCEPTION.getStatusCode() == responseResult.getCode()) {
			throw new RuntimeException(responseResult.getMsg());
		}
		return responseResult;
	}
}
