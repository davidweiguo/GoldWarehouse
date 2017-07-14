package com.goldwarehouse.socket;

import com.goldwarehouse.util.SocketMessageUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by guo_d on 2017/2/21.
 */
public class SocketClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), 3232);
            System.out.println("Client: sending message");
            SocketMessageUtils.sendMessage(socket, "Hello World");
            String replyMessage = SocketMessageUtils.getMessage(socket);
            System.out.println("Client: received message, " + replyMessage);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
