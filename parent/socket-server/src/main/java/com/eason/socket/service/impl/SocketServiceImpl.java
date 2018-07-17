package com.eason.socket.service.impl;

import com.eason.api.exception.ServiceException;
import com.eason.api.zb.ISocketUrlService;
import com.eason.socket.model.SocketProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/socket")
public class SocketServiceImpl implements ISocketUrlService {

    @Autowired
    private SocketProperties socketProperties;

    @RequestMapping(value = "/getUrl",method = RequestMethod.GET)
    @Override
    public String getUrl(String roomId) throws ServiceException {
        return socketProperties.getHost()+":"+socketProperties.getPort();
    }
}
