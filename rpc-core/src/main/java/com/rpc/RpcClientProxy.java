package com.rpc;

import com.rpc.entity.RpcRequest;
import com.rpc.entity.RpcResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


/**
 * Rpc 客户端动态代理
 */
public class RpcClientProxy implements InvocationHandler {
    private String host;
    private int port;
    public RpcClientProxy(String host , int port){
        this.host = host;
        this.port = port;
    }
    

    //此注解抑制编译器产生警告
    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> clazz){
        // 创建代理对象
        return (T) Proxy.newProxyInstance(clazz.getClassLoader() , new Class<?>[]{clazz},this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // client to service  class
        RpcRequest build = RpcRequest.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameters(args)
                .paramTypes(method.getParameterTypes())
                .build();

        RpcClient rpcClient = new RpcClient();
        return ((RpcResponse)rpcClient.sendRequest(build,host,port)).getData();
    }
}
