package com.vienna.jaray.feign.producer;

import com.vienna.jaray.utils.common.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 熔断会导致事务不会回滚
 */

@Component
@FeignClient(value = "jaray-order-producer")
// @FeignClient(value = "jaray-order-producer", fallback = ProducerFeignServiceHystric.class)
public interface ProducerFeignService {
	
	@GetMapping("/hello/say/{name}")
	public ResponseResult sayHello(@PathVariable(value="name") String name);

}
