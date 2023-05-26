package com.rpc.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName : RpcRequest  //类名
 * @Description :   //描述
 * @Author : dell //作者
 * @Date: 2023/5/25  15:03
 */

@Data
@AllArgsConstructor
public class RpcRequest implements Serializable {
    public RpcRequest() {}

    /**
     * 调用接口的名称
     */
    private String interfaceName;

    /**
     * 调用方法的名称
     */
    private String methodName;

    /**
     * 调用方法的参数
     */
    private Object[] parameters;

    /**
     * 调用方法的参数类型
     */
    private Class<?>[] paramTypes;
}
