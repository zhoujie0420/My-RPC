package com.rpc.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName : PackageType  //类名
 * @Description :   //描述
 * @Author : dell //作者
 * @Date: 2023/5/26  11:20
 */

@AllArgsConstructor
@Getter
public enum PackageType {

    REQUEST_PACK(0),
    RESPONSE_PACK(1);

    private final int code;

}