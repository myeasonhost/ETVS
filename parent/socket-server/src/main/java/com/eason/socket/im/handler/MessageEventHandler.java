package com.eason.socket.im.handler;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.eason.socket.im.dao.ClientInfoRepository;
import com.eason.socket.im.po.ClientInfo;
import com.eason.socket.im.po.Room;
import com.eason.socket.im.protocol.MessageInfo;
import com.eason.socket.im.protocol.UserEnterRoomInfo;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Component
public class MessageEventHandler {
    private final SocketIOServer server;

    @Autowired
    private ClientInfoRepository clientInfoRepository;

    @Autowired
    public MessageEventHandler(SocketIOServer server) {
        this.server = server;
    }

    //添加connect事件，当客户端发起连接时调用，本文中将clientid与sessionid存入数据库
    //方便后面发送消息时查找到对应的目标client,
    @OnConnect
    public void onConnect(SocketIOClient client) {
        String sessionId = client.getSessionId().toString();
        client.set("sessionId", sessionId);
        client.set("mostSignificantBits",client.getSessionId().getMostSignificantBits());
        client.set("leastSignificantBits",client.getSessionId().getLeastSignificantBits());
        client.set("connectionTime", DateFormatUtils.format(new Date(),DateFormatUtils.ISO_DATETIME_FORMAT.getPattern()));
        String token=client.getHandshakeData().getSingleUrlParam("token");
        client.set("token",token);
        System.out.println(sessionId+" connecting..."+client.getAllRooms());

    }

    //添加@OnDisconnect事件，客户端断开连接时调用，刷新客户端信息
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {
        String sessionId = client.getSessionId().toString();
        System.out.println(sessionId+" disconnecting..."+client.get("sessionId"));
        client.del("sessionId");
        client.del("mostSignificantBits");
        client.del("leastSignificantBits");
        client.del("connectionTime");
        client.del("token");
    }

    @OnEvent(value = "login")
    public String login(SocketIOClient client, AckRequest request) {
        System.out.println("登陆成功");
        server.getClient(new UUID(client.getSessionId().getMostSignificantBits(),client.getSessionId().getLeastSignificantBits()))
                .sendEvent("login", "{'aaa':'bbb'}");
        return "登陆成功";
    }

    @OnEvent(value = "createRoom")
    public void createRoom(SocketIOClient client, AckRequest request, Room room) {
        SocketIONamespace chatNamespace=server.addNamespace("/"+room.getRoomName());
        MessageInfo sendData = new MessageInfo();
        sendData.setMsgContent("聊天室创建成功");
        chatNamespace.getBroadcastOperations().sendEvent("sendMsg",sendData);
    }

    @OnEvent(value = "enterRoomMsg")
    public void enterRoomMsg(SocketIOClient client, AckRequest request, UserEnterRoomInfo userEnterRoomInfo) {
        client.joinRoom(userEnterRoomInfo.getRoomId());
//        SocketIONamespace chatNamespace=server.addNamespace("/"+room.getRoomName());
//        MessageInfo sendData = new MessageInfo();
//        sendData.setMsgContent("聊天室创建成功");
//        chatNamespace.getBroadcastOperations().sendEvent("sendMsg",sendData);
    }

    @OnEvent(value = "destoryRoom")
    public void destoryRoom(SocketIOClient client, AckRequest request, Room room) {
        MessageInfo sendData = new MessageInfo();
        sendData.setMsgContent("聊天室已经关闭");
        client.getNamespace().getBroadcastOperations().sendEvent("sendMsg",sendData);
        server.removeNamespace("/"+room.getRoomName());
    }

    //消息接收入口，当接收到消息后，查找发送目标客户端，并且向该客户端发送消息，且给自己发送消息
    @OnEvent(value = "sendMsg")
    public void onEvent(SocketIOClient client, AckRequest request, MessageInfo data) {
//        String targetClientId = data.getTargetClientId();
//        ClientInfo clientInfo = clientInfoRepository.findClientByclientid(targetClientId);
//        List<ClientInfo> clientInfoList=clientInfoRepository.findAll();
//        clientInfoList.forEach(clientInfo -> {
//            if (clientInfo != null && clientInfo.getConnected() != 0) {
//                UUID uuid = new UUID(clientInfo.getMostsignbits(), clientInfo.getLeastsignbits());
//                System.out.println(uuid.toString());
//                MessageInfo sendData = new MessageInfo();
//                sendData.setSourceClientId(data.getSourceClientId());
//                sendData.setTargetClientId(clientInfo.getClientid());
//                sendData.setMsgType("chat");
//                sendData.setMsgContent(data.getMsgContent());
////                client.sendEvent("messageevent", sendData);
//                if (server.getClient(uuid)!=null){
////                    server.getClient(uuid).sendEvent("sendMsg", sendData);
//                }
//                server.getBroadcastOperations().sendEvent("sendMsg", sendData);
//            }
//        });
        MessageInfo sendData = new MessageInfo();
        sendData.setMsgContent(data.getMsgContent());
        server.getBroadcastOperations().sendEvent("sendMsg", sendData);
        System.out.println("namespace==="+client.getNamespace().getName());
    }
}