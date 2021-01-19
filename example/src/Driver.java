import io.vdev.socket.Message;
import io.vdev.socket.Node;
import io.vdev.socket.NodeListener;

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
