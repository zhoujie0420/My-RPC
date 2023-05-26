package com.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName : HelloObject  //类名
 * @Description :   //描述
 * @Author : dell //作者
 * @Date: 2023/5/25  14:38
 */

/**
 * 注意这个对象需要实现Serializable接口，因为它需要在调用过程中从客户端传递给服务端。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HelloObject implements Serializable {
    private Integer id;
    private String message;
}
