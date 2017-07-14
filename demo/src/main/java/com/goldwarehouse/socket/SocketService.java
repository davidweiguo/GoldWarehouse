package com.goldwarehouse.socket;

import com.goldwarehouse.util.SocketMessageUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by guo_d on 2017/2/21.
 */
public class SocketService implements Runnable {
    private ServerSocket serverSocket;
    private volatile boolean keepProcessing = true;

    public SocketService(int port, int millisecondsTimeout) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(millisecondsTimeout);
    }

    @Override
    public void run() {
        System.out.println("Server Starting");
        while (keepProcessing) {
            try {
                System.out.println("accepting client");
                Socket socket = serverSocket.accept();
                System.out.println("got client");
                process(socket);
            } catch (Exception e) {
            }
        }
    }

    private void handle(Exception e) {
        if (!(e instanceof SocketException)) {
            e.printStackTrace();
        }
    }

    public void stopProcessing() {
        keepProcessing = false;
        closeIgnoringException(serverSocket);
    }

    private void process(Socket socket) {
        if (socket == null) {
            return;
        }
        try {
            System.out.println("Server: getting message");
            String message = SocketMessageUtils.getMessage(socket);
            System.out.println("Server - got message: " + message);
            if (message.equalsIgnoreCase("Stop")) {
                stopProcessing();
            }
            Thread.sleep(5000);
            System.out.println("Server: sending reply: " + message);
            SocketMessageUtils.sendMessage(socket, "Processed:" + message);
            System.out.println("Server: sent");
            closeIgnoringException(socket);
        } catch (IOException e) {
            handle(e);
        } catch (InterruptedException e) {
            handle(e);
        }
    }

    private void closeIgnoringException(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
            }
        }
    }

    private void closeIgnoringException(ServerSocket serverSocket) {
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
            }
        }
    }

    public static void main(String[] args) {
        try {
            Thread serverThread = new Thread(new SocketService(3232, 1000));
            serverThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}