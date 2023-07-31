package com.test;

import com.rpc.api.HelloObject;
import com.rpc.api.HelloService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class HelloServiceImpl implements HelloService {


    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);
    @Override
    public String hello(HelloObject object) {
        logger.info("get {}",object.getMessage());
        return "this is return = " + object.getId();
    }
}
