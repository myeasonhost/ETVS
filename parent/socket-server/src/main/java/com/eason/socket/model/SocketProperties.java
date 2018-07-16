package com.eason.socket.model;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "socket.server")
public class SocketProperties {

    private String host;
    private Integer port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}