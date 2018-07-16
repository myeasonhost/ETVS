package com.eason.socket;

import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import com.corundumstudio.socketio.store.RedissonStoreFactory;
import com.eason.socket.model.RedissonProperties;
import com.eason.socket.model.SocketProperties;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class SocketServerApplication {
    private static Logger logger = LoggerFactory.getLogger(SocketServerApplication.class);

    @Autowired
    private RedissonProperties redissonProperties;

    @Autowired
    private SocketProperties socketProperties;

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration config = new Configuration();
        config.setHostname(socketProperties.getHost());
        config.setPort(socketProperties.getPort());

        //该处可以用来进行身份验证
        config.setAuthorizationListener(new AuthorizationListener() {
            @Override
            public boolean isAuthorized(HandshakeData data) {
                //http://localhost:8081?username=test&password=test
                //例如果使用上面的链接进行connect，可以使用如下代码获取用户密码信息，本文不做身份验证
//              String username = data.getSingleUrlParam("username");
//              String password = data.getSingleUrlParam("password");
                return true;
            }
        });
        Config redissonConfig = new Config();
        //redissonConfig.useSingleServer().setAddress(SINGLE_SERVER);
//        redissonConfig.useClusterServers().addNodeAddress("redis://192.168.0.109:6379");
        redissonConfig.useSingleServer().setAddress(redissonProperties.getAddress());
        redissonConfig.useSingleServer().setPassword(redissonProperties.getPassword());
        RedissonClient redisson = Redisson.create(redissonConfig);
        config.setStoreFactory(new RedissonStoreFactory(redisson));

        final SocketIOServer server = new SocketIOServer(config);

        return server;
    }

    @Bean
    public SpringAnnotationScanner springAnnotationScanner(SocketIOServer socketServer) {
        return new SpringAnnotationScanner(socketServer);
    }

    public static void main(String[] args) {
        SpringApplication.run(SocketServerApplication.class, args);
    }
}
