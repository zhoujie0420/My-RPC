package com.test;

import com.rpc.HelloService;
import com.rpc.exception.RpcException;
import com.rpc.netty.NettyServer;
import com.rpc.registry.DefaultServiceRegistry;
import com.rpc.registry.ServiceRegistry;;



/**
 * 测试用Netty服务提供者（服务端）
 * @author ziyang
 */
public class NettyTestServer {

    public static void main(String[] args) throws RpcException {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry registry = new DefaultServiceRegistry();
        registry.register(helloService);
        NettyServer server = new NettyServer();
        server.start(9999);
    }

}