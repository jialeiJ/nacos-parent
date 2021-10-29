package com.vienna.jaray.feign.consumer;

import com.vienna.jaray.utils.common.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 熔断会导致事务不会回滚
 */

@Component
@FeignClient(value = "jaray-order-consumer")
// @FeignClient(value = "jaray-order-consumer", fallback = ConsumerFeignServiceHystric.class)
public interface ConsumerFeignService {
	
	@GetMapping("/hello/say2/{name}")
	public ResponseResult sayHello2(@PathVariable(value="name") String name);

}
