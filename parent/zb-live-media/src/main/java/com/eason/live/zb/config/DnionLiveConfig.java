package com.eason.live.zb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "platfrom.dnion")
public class DnionLiveConfig {
    private String producer;
    private String appName;
    private String line;
    private Map<String,String > pushUrl=new HashMap<>();
    private Map<String,String > playUrl=new HashMap<>();

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public Map<String, String> getPushUrl() {
        return pushUrl;
    }

    public void setPushUrl(Map<String, String> pushUrl) {
        this.pushUrl = pushUrl;
    }

    public Map<String, String> getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(Map<String, String> playUrl) {
        this.playUrl = playUrl;
    }
}
