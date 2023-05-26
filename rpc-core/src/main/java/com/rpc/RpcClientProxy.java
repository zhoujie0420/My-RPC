package com.rpc;

import com.rpc.entity.RpcRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger logger = LoggerFactory.getLogger(RpcClientProxy.class);
    private final RpcClient client;

    public RpcClientProxy(RpcClient client) {
        this.client = client;
    }

    // 传递host和port来指明服务端的位置。并且使用getProxy()方法来生成代理对象。
    @SuppressWarnings("unchecked") // 告诉编译器同时忽略unchecked和deprecation的警告信息。
    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.info("调用方法: {}#{}", method.getDeclaringClass().getName(), method.getName());
        RpcRequest rpcRequest = new RpcRequest(method.getDeclaringClass().getName(),
                method.getName(), args, method.getParameterTypes());
        return client.sendRequest(rpcRequest);
    }
}
