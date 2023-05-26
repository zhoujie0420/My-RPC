package com.test;

import com.rpc.HelloService;
import com.rpc.exception.RpcException;
import com.rpc.registry.DefaultServiceRegistry;
import com.rpc.registry.ServiceRegistry;
import com.rpc.server.RpcServer;

/**
 * @ClassName : TestServer  //类名
 * @Description :   //描述
 * @Author : dell //作者
 * @Date: 2023/5/25  16:21
 */

public class TestServer {
    public static void main(String[] args) throws RpcException {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        serviceRegistry.register(helloService);
        RpcServer rpcServer = new RpcServer(serviceRegistry);
        rpcServer.start(9000);

    }

}
