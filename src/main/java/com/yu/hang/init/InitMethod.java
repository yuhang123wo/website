package com.yu.hang.init;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.yu.hang.core.service.DepService;
import com.yu.hang.util.redis.JedisTemplate;

/**
 * 此处做项目的一些初始化工作
 * 
 * @author yuhang
 * @Date 2017年8月8日
 * @desc
 */
@Component
public class InitMethod {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private JedisTemplate jedisTemplate;
	@Resource
	private DepService depService;

	public void initAll() {
		logger.info("初始化信息完成.....");
	}

}
