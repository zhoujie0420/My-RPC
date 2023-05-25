package com.rpc.registry;

import com.rpc.enumeration.RpcError;
import com.rpc.exception.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName : DefaultServiceRegistry  //类名
 * @Description :   //我们将服务名与提供服务的对象的对应关系保存在一个 ConcurrentHashMap 中，
 * 并且使用一个 Set 来保存当前有哪些对象已经被注册。在注册服务时，默认采用这个对象实现的接口的完整类名作为服务名，
 * 例如某个对象 A 实现了接口 X 和 Y，那么将 A 注册进去后，会有两个服务名 X 和 Y 对应于 A 对象。
 * 这种处理方式也就说明了某个接口只能有一个对象提供服务。
 * @Author : dell //作者
 * @Date: 2023/5/25  17:09
 */


public class DefaultServiceRegistry implements ServiceRegistry{
    private static final Logger logger = LoggerFactory.getLogger(DefaultServiceRegistry.class);

    private final Map<String, Object> serviceMap = new ConcurrentHashMap<>();
    private final Set<String> registeredService = ConcurrentHashMap.newKeySet();
    @Override
    public synchronized <T> void register(T service) throws RpcException {
        String serviceName = service.getClass().getCanonicalName();
        if(registeredService.contains(serviceName)) return;
        registeredService.add(serviceName);
        Class<?>[] interfaces = service.getClass().getInterfaces();
        if(interfaces.length == 0) {
            throw new RpcException(RpcError.SERVICE_NOT_IMPLEMENT_ANY_INTERFACE);
        }
        for(Class<?> i : interfaces) {
            serviceMap.put(i.getCanonicalName(), service);
        }
        logger.info("向接口: {} 注册服务: {}", interfaces, serviceName);
    }


    @Override
    public Object getService(String serviceName) throws RpcException {
        Object service = serviceMap.get(serviceName);
        if(service == null){
            throw new RpcException(RpcError.SERVICE_NOT_FOUND);
        }
        return service;
    }
}
