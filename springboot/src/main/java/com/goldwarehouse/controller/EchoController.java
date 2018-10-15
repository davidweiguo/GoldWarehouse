package com.goldwarehouse.controller;

import com.goldwarehouse.bean.Echo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

/**
 * @author guo_d
 * @date 2018/10/15
 */
@Controller
public class EchoController {
    private static Logger logger = LoggerFactory.getLogger(EchoController.class);

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/echo")
    @SendToUser(destinations = "/queue/echoResponse", broadcast = false)
    public Echo getEchoResponse(String name) {
        logger.info("Server received: {}", name);
        Echo echo = new Echo();
        echo.setMessage("Good Morning, " + name);
        return echo;
    }
}
