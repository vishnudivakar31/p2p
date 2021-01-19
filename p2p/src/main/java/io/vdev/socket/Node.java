package io.vdev.socket;

public class Node {
    private Integer port = null;
    private NodeListener listener = null;

    public Node(Integer port, NodeListener listener) {
        this.port = port;
        this.listener = listener;
        ServerRunnable serverRunnable = new ServerRunnable(port, listener);
        ThreadExecutor.getInstance().execute(serverRunnable);
    }


    public void sendToNode(Message msg) {
        SendRunnable sender = new SendRunnable(msg, listener);
        ThreadExecutor.getInstance().execute(sender);
    }

}
