package io.vdev.socket;

public class Node {
    private static Node singleInstance = null;
    private Integer port = null;

    private Node() {
    }

    public static Node getInstance() {
        if (singleInstance == null) {
            singleInstance = new Node();
        }
        return singleInstance;
    }

    public void start(int port, NodeListener nodeListener) {
        if (this.port == null) {
            this.port = port;
            ServerRunnable serverRunnable = new ServerRunnable(port, nodeListener);
            ThreadExecutor.getInstance().execute(serverRunnable);
        }
    }

}
