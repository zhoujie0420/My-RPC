package com.rpc.socket.server;

import com.rpc.RpcServer;
import com.rpc.registry.ServiceRegistry;
import com.rpc.RequestHandler;
import com.rpc.server.RequestHandlerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * Socket 远程方法调用的提供者（服务端）
 *
 * @author zhouj
 */
public class SocketServer implements RpcServer {
    private static final Logger logger = LoggerFactory.getLogger(SocketServer.class);

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAXIMUM_POOL_SIZE = 50;
    private static final int KEEP_ALIVE_TIME = 60;
    private static final int BLOCKING_QUEUE_CAPACITY = 100;
    private final ExecutorService threadPool;
    private RequestHandler requestHandler = new RequestHandler();
    private final ServiceRegistry serviceRegistry;

    public SocketServer(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
        BlockingQueue<Runnable> workingQueue = new ArrayBlockingQueue<>(BLOCKING_QUEUE_CAPACITY);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, workingQueue, threadFactory);
    }

    //RpcServer 时需要传入一个已经注册好服务的 ServiceRegistry
    //而在每一个请求处理线程（RequestHandlerThread）中也就需要传入 ServiceRegistry 了
    // 这里把处理线程和处理逻辑分成了两个类：RequestHandlerThread 只是一个线程
    // 从ServiceRegistry 获取到提供服务的对象后，就会把 RpcRequest 和服务对象直接交给 RequestHandler 去处理
    // 反射等过程被放到了 RequestHandler 里。
    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("服务器正在启动");
            Socket socket;
            while ((socket = serverSocket.accept()) != null) {
                logger.info("消费者连接:{} :{}", socket.getInetAddress(),socket.getPort());
                threadPool.execute(new RequestHandlerThread(socket, requestHandler,serviceRegistry));
            }
            threadPool.shutdown();
        } catch (IOException e) {
            logger.error("服务器启动有错误", e);
        }
    }
}
