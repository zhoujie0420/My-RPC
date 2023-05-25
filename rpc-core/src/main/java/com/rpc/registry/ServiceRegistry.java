package com.rpc.registry;


import com.rpc.exception.RpcException;

/**
 * 服务注册表通用接口
 * @author dell
 */
public interface ServiceRegistry {
    /**
     * 将一个服务注册进注册表
     * @param service
     * @param <T>
     */
    <T>void register (T service) throws RpcException;

    /**
     * 根据服务名称获取服务实体
     * @param serviceName
     * @return
     */
    Object getService(String serviceName) throws RpcException;
}
