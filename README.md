# p2p
A java library for P2P communication.

## Node Class
Construct a node class object to start a node server
```
Node(Integer port, NodeListener listener)
```

This will automatically start a node server for you in a thread.

## NodeListener
An interface to provide callbacks for received messages and communication status

```
public interface NodeListener {
    void onMessage(Message msg);
    void onCommStatus(boolean status, String msg);
}
```

## A Simple example

```
public class Driver implements NodeListener {

    private Integer port;
    private String username;
    private Node node;

    public Driver(Integer port, String username) {
        this.port = port;
        this.username = username;
        this.node = new Node(port, this);
    }

    public Node getNode() {
        return node;
    }

    @Override
    public void onMessage(Message message) {
        System.out.println(username);
        System.out.println("**sender: -- " + message.getSender().toString());
        System.out.println(new String(message.getDataBytes()));
    }

    @Override
    public void onCommStatus(boolean status, String msg) {
        System.out.println(username);
        System.out.println(msg);
    }
}
```

```
public class Main {

    public static void main(String[] args)  {
        if(args.length != 2) {
            System.out.println("***ERROR -- Expected 2 arguments***");
            System.out.println("**** ARG 1 -- username");
            System.out.println("**** ARG 2 -- port");
            return;
        }
        Scanner sc = new Scanner(System.in);
        String username = args[0];
        int port = Integer.parseInt(args[1]);
        Driver driver = new Driver(port, username);

        System.out.println("peer ip: ");
        String peerIP = sc.nextLine();

        System.out.println("peer port: ");
        int peerPort = sc.nextInt();

        String msg = "";

        do {
            System.out.println("Enter message: ");
            msg = sc.nextLine();
            if(msg.length() > 0) {
                Sender sender = new Sender(peerIP, peerPort);
                driver.getNode().sendToNode(new Message(sender, msg.getBytes()));
            }
        } while(!msg.equals("END"));
    }
}
```

## Installation Guide

```
clone the repository
run mvn clean install
```

include p2p-v0.1.jar in your project
