package com.vienna.jaray.feign.producer;

import com.vienna.jaray.utils.common.ResponseResult;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class ProducerFeignServiceHystric implements ProducerFeignService {

	@Override
	@GetMapping(value = "/hello/say/{name}")
	public ResponseResult sayHello(@PathVariable(value="name") String name) {
		return ResponseResult.fail().add("msg", "Hello " +name+", This messge send failed ");
	}

}
