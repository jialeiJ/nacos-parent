package com.vienna.jaray.mapper.consumer;

import com.vienna.jaray.entity.consumer.Consumer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ConsumerMapper {
	
	/**
	 * 添加信息
	 * @param consumer 对象
	 * @return 添加结果
	 */
	int add(@Param("consumer") Consumer consumer);

}
