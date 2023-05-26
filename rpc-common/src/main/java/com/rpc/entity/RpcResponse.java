package com.rpc.entity;

import com.rpc.enumeration.ResponseCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName : RpcResponse  //类名
 * @Description :   //描述
 * @Author : dell //作者
 * @Date: 2023/5/25  15:07
 */

@Data
public class RpcResponse<T> implements Serializable {
    public RpcResponse() {}

    /**
     * 响应的状态码
     */
    private Integer statusCode;

    /**
     * 响应状态的补充信息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    public static <T> RpcResponse<T> success (T data){
        RpcResponse<T> response = new RpcResponse<>();
        response.setStatusCode(ResponseCode.SUCCESS.getCode());
        response.setData(data);
        return response;
    }

    public static <T> RpcResponse<T> fail (ResponseCode  code){
        RpcResponse<T> response = new RpcResponse<>();
        response.setStatusCode(code.getCode());
        response.setMessage(code.getMessage());
        return response;
    }
}
