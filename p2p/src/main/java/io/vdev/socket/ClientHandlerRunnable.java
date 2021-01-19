package io.vdev.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

class ClientHandlerRunnable implements Runnable {

    private Socket clientSocket;
    private NodeListener listener;
    final int bufferLength = 65535;

    public ClientHandlerRunnable(Socket clientSocket, NodeListener listener) {
        this.clientSocket = clientSocket;
        this.listener = listener;
    }

    @Override
    public void run() {
        try {
            byte[] buffer = new byte[bufferLength];
            InputStream inputStream = clientSocket.getInputStream();
            if((inputStream.read(buffer, 0, bufferLength)) > -1) {
                Sender sender = new Sender(clientSocket.getInetAddress().getHostAddress(),
                        clientSocket.getPort());
                Message msg = new Message(sender, buffer);
                if(listener != null) {
                    listener.onMessage(msg);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
