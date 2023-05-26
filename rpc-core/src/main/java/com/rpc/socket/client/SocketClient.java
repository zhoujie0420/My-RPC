package com.rpc.socket.client;

import com.rpc.RpcClient;
import com.rpc.entity.RpcRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;


/**
 * Socket 远程方法调用的消费者（客户端）
 * @author dell
 */

public class SocketClient implements RpcClient {
    private static final Logger logger = LoggerFactory.getLogger(SocketClient.class);

    private final String host;
    private final int port;
    public SocketClient(String host ,int port){
        this.host = host;
        this.port = port;
    }
    public Object sendRequest(RpcRequest rpcRequest){
        try (Socket socket = new Socket(host, port)){
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();
            return objectInputStream.readObject();
        }catch (IOException | ClassNotFoundException e){
            logger.error("调用时有错误：" + e);
            return null;
        }
    }
}
