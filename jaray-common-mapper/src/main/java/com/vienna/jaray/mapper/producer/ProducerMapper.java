package com.vienna.jaray.mapper.producer;

import com.vienna.jaray.entity.producer.Producer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProducerMapper {
	
	/**
	 * 添加信息
	 * @param producer 对象
	 * @return 添加结果
	 */
	int add(@Param("producer") Producer producer);

}
