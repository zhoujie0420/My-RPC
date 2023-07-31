package com.rpc.entity;

import com.rpc.enumeration.ResponseCode;
import lombok.Data;

import java.io.Serializable;


@Data
public class RpcResponse<T> implements Serializable {

    private Integer statusCode;

    private String message;

    private T data;


    /**
     * success return class
     */
    public static <T> RpcResponse<T> success(T data){
        RpcResponse<T> res = new RpcResponse<>();
        res.setStatusCode(ResponseCode.SUCCESS.getCode());
        res.setData(data);
        return res;
    }


    /**
     * fail return class
     */
    public static <T> RpcResponse<T> fail(ResponseCode code){
        RpcResponse<T> res = new RpcResponse<>();
        res.setStatusCode(code.getCode());
        res.setMessage(code.getMessage());
        return res;
    }
}
