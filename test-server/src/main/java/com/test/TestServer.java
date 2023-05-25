package com.test;

import com.rpc.HelloService;
import com.rpc.server.RpcServer;

/**
 * @ClassName : TestServer  //类名
 * @Description :   //描述
 * @Author : dell //作者
 * @Date: 2023/5/25  16:21
 */

public class TestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.register(helloService, 9000);
    }

}
