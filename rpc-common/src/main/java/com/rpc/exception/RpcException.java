package com.rpc.exception;

import com.rpc.enumeration.RpcError;

/**
 * @ClassName : RpcException  //类名
 * @Description :   //描述
 * @Author : dell //作者
 * @Date: 2023/5/25  17:14
 */

public class RpcException extends  Exception{
    public RpcException(RpcError error,String detail){
        super(error.getMessage() + ":" +detail );
    }

    public RpcException(String message, Throwable cause) {
        super(message, cause);
    }
    public RpcException(RpcError error) {
        super(error.getMessage());
    }
}
