package com.eason.socket.service.impl;

import com.eason.api.exception.ServiceException;
import com.eason.api.zb.ISocketUrlService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/socket")
public class SocketServiceImpl implements ISocketUrlService {

    @RequestMapping(value = "/getUrl",method = RequestMethod.GET)
    @Override
    public String getUrl(String roomId) throws ServiceException {
        return null;
    }
}
