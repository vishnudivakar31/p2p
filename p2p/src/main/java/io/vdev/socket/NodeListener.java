package io.vdev.socket;

public interface NodeListener {
    void onMessage(Message msg);
    void onCommStatus(boolean status, String msg);
}
