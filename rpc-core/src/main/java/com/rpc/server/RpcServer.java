package com.rpc.server;

import javafx.concurrent.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * 远程方法调用的提供者（服务端）
 * @author zhouj
 */
public class RpcServer {
    private final ExecutorService threadPool;
    private static final Logger logger = LoggerFactory.getLogger(RpcServer.class);


    public RpcServer() {
        BlockingQueue<Runnable> workingQueue  = new ArrayBlockingQueue<>(100);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        threadPool = new ThreadPoolExecutor(5, 50, 60, TimeUnit.SECONDS, workingQueue, threadFactory);
    }

    //这里简化了一下，RpcServer暂时只能注册一个接口，
    //即对外提供一个接口的调用服务，添加register方法，在注册完一个服务后立刻开始监听：
    public void register(Object service ,int port){
        try(ServerSocket serverSocket = new ServerSocket(port)){
            logger.info("服务器正在启动");
            Socket socket;
            while ((socket = serverSocket.accept())!= null){
                logger.info("客户端连接成功！ IP为："+ socket.getInetAddress());
                threadPool.execute(new WorkerThread(socket,service));
            }
        }catch (IOException e){
            logger.error("连接有错误",e);
        }
    }
}
