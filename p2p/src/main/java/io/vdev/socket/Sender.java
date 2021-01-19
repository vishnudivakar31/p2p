package io.vdev.socket;

import java.io.Serializable;

public class Sender implements Serializable {
    private String ip;
    private int port;

    public Sender(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String toString() {
        return "ip: " + ip + " -- port: " + port;
    }
}
