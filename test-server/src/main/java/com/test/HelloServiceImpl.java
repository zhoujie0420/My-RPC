package com.test;

import com.rpc.HelloObject;
import com.rpc.HelloService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @ClassName : HelloServiceImpl  //类名
 * @Description :   //描述
 * @Author : dell //作者
 * @Date: 2023/5/25  14:49
 */

public class HelloServiceImpl implements HelloService {


    /**
     * 优点：使用指定类初始化日志对象，在日志输出的时候，可以打印出日志信息所在类
     */
    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);
    @Override
    public String hello(HelloObject object) {
        logger.info("接收到：{}",object.getMessage());
        return "这是调用的返回值，id=" + object.getId();
    }
}
