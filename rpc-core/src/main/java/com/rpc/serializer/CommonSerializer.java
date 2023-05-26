package com.rpc.serializer;



/**
 * @ClassName : CommonSerializer  //类名
 * @Description :   //通用的序列化反序列化接口
 * @Author : dell //作者
 * @Date: 2023/5/26  15:20
 */

public interface CommonSerializer {
    byte[] serialize (Object object);
    Object deserialize(byte[] bytes,Class<?> clazz);

    int getCode();
    static CommonSerializer getByCode(int code){
        switch (code){
            case 0:
                return new KryoSerializer();
            case 1:
                return new JsonSerializer();
            default:
                return null;

        }
    }
}
