package io.vdev.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class ServerRunnable implements Runnable {
    int port;
    NodeListener nodeListener;

    public ServerRunnable(int port, NodeListener nodeListener) {
        this.port = port;
        this.nodeListener = nodeListener;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandlerRunnable clientHandler = new ClientHandlerRunnable(clientSocket, nodeListener);
                ThreadExecutor.getInstance().execute(clientHandler);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
