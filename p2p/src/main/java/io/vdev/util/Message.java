package io.vdev.util;

public class Message {
    private Sender sender;
    private byte[] dataBytes;

    public Message() {
    }

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
}
