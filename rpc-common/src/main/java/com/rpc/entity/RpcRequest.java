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
@Builder //Builder 使用创建者模式又叫建造者模式。简单来说，
         // 就是一步步创建一个对象，它对用户屏蔽了里面构建的细节，但却可以精细地控制对象的构造过程。

public class RpcRequest implements Serializable {

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
