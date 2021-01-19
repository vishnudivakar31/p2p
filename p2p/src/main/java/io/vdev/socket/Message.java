package io.vdev.socket;

import java.io.Serializable;

public class Message implements Serializable {
    private Sender sender;
    private byte[] dataBytes;

    public Message(Sender sender, byte[] dataBytes) {
        this.sender = sender;
        this.dataBytes = dataBytes;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public byte[] getDataBytes() {
        return dataBytes;
    }

    public void setDataBytes(byte[] dataBytes) {
        this.dataBytes = dataBytes;
    }

    public String toString() {
        return sender.toString() + "\n" + "msg: " + dataBytes.toString();
    }
}
