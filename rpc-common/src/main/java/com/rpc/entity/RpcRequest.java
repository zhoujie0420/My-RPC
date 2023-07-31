package com.rpc.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
@Data
@Builder
public class RpcRequest implements Serializable {

    /**
     * 待调用接口名字
     */
    private String interfaceName;

    private String methodName;

    private Object[] parameters;

    private Class<?>[] paramTypes;
}
