package io.vdev.socket;

import io.vdev.util.P2PUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Date;

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
            int receivedLength;
            byte[] buffer = new byte[bufferLength];
            InputStream inputStream = clientSocket.getInputStream();
            if((receivedLength = inputStream.read(buffer, 0, bufferLength)) > -1) {
                byte[] decodedBytes = P2PUtil.decode(Arrays.copyOf(buffer, receivedLength));
                Sender sender = new Sender(clientSocket.getInetAddress().getHostName(),
                        clientSocket.getPort());
                Message msg = new Message(sender, decodedBytes);
                if(listener != null) {
                    listener.onMessage(msg);
                    listener.onCommStatus(true, "success: " + new Date().getTime());
                }
            }
            clientSocket.close();
        } catch (IOException e) {
            listener.onCommStatus(false, e.getMessage());
        }
    }
}
