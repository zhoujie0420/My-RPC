package com.test;

import com.rpc.HelloObject;
import com.rpc.HelloService;
import com.rpc.RpcClient;
import com.rpc.RpcClientProxy;
import com.rpc.netty.NettyClient;

/**
 * 测试用Netty消费者
 * @author ziyang
 */
public class NettyTestClient {

    public static void main(String[] args) {
        RpcClient client = new NettyClient("127.0.0.1", 9999);
        RpcClientProxy rpcClientProxy = new RpcClientProxy(client);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "This is a message");
        String res = helloService.hello(object);
        System.out.println(res);

    }

}