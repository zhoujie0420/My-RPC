package com.rpc.client;

import com.rpc.entity.RpcRequest;
import com.rpc.entity.RpcResponse;
import lombok.Setter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName : RpcClientProxy  //类名
 * @Description :   //描述
 * @Author : dell //作者
 * @Date: 2023/5/25  15:35
 * 客户端方面，由于在客户端这一侧我们并没有接口的具体实现类，
 * 就没有办法直接生成实例对象。这时，我们可以通过动态代理的方式生成实例，
 * 并且调用方法时生成需要的RpcRequest对象并且发送给服务端。
 */

public class RpcClientProxy  implements InvocationHandler  {
    private String host;
    private int port;
    public RpcClientProxy(String host, int port) {
        this.host = host;
        this.port = port;
    }

    // 传递host和port来指明服务端的位置。并且使用getProxy()方法来生成代理对象。
    @SuppressWarnings("unchecked") // 告诉编译器同时忽略unchecked和deprecation的警告信息。
    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest = RpcRequest.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameters(args)
                .paramTypes(method.getParameterTypes())
                .build();
        RpcClient rpcClient = new RpcClient();
        return ((RpcResponse)rpcClient.sendRequest(rpcRequest,host,port)).getData();
    }
}
