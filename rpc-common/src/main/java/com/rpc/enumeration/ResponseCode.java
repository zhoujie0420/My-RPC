package com.rpc.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.omg.PortableInterceptor.SUCCESSFUL;

/**
 * @ClassName : ResponseCode  //类名
 * @Description :   //描述
 * @Author : dell //作者
 * @Date: 2023/5/25  15:17
 */

@AllArgsConstructor
@Getter
public enum ResponseCode {

    SUCCESS(200,"调用方法成功"),
    FAIL(500,"调用方法失败"),
    NOT_FOUND_METHOD(500,"未找到指定方法"),
    NOT_FOUND_CLASS(500,"未找到指定类");

    private final int code;
    private final String message;


}
