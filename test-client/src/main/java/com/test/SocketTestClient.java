package com.test;

import com.rpc.HelloObject;
import com.rpc.HelloService;
import com.rpc.RpcClientProxy;
import com.rpc.socket.client.SocketClient;


/**
 * 测试用消费者（客户端）
 */
public class SocketTestClient  {
    public static void main(String[] args) {
        SocketClient client = new SocketClient("127.0.0.1", 9000);
        RpcClientProxy proxy = new RpcClientProxy(client);
        HelloService helloService = proxy.getProxy(HelloService.class);
        HelloObject object  = new HelloObject(12, "This is a message");
        String res = helloService.hello(object);
        System.out.println(res);
    }
}
