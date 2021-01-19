package io.vdev.socket;

import io.vdev.util.P2PUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

class SendRunnable implements Runnable{

    private Message message;
    private NodeListener nodeListener;

    public SendRunnable(Message message, NodeListener nodeListener) {
        this.message = message;
        this.nodeListener = nodeListener;
    }

    @Override
    public void run() {
        try {
            Socket clientSocket = new Socket(message.getSender().getIp(),
                    message.getSender().getPort());
            OutputStream out = clientSocket.getOutputStream();
            out.write(P2PUtil.encode(message.getDataBytes()));
            nodeListener.onCommStatus(true, "success: " + new Date().getTime());
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
            nodeListener.onCommStatus(false, e.getMessage());
        }
    }
}
