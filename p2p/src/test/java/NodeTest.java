import io.vdev.socket.Message;
import io.vdev.socket.Node;
import io.vdev.socket.NodeListener;
import io.vdev.socket.Sender;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.lang.Boolean.TRUE;

@DisplayName("Node Test Suite")
public class NodeTest implements NodeListener {
    @Test
    @DisplayName("p2p communication test")
    public void p2pTest() throws InterruptedException {
        Node bob = new Node(5000, this);
        Node alice = new Node(6000, this);
        bob.sendToNode(new Message(new Sender("localhost", 6000), "Hello".getBytes()));
        Thread.sleep(2000);
        alice.sendToNode(new Message(new Sender("localhost", 5000), "Hi. How are you?".getBytes()));
    }

    @Override
    public void onMessage(Message msg) {
        System.out.println(msg.toString());
        System.out.println(new String(msg.getDataBytes()));
    }

    @Override
    public void onCommStatus(boolean status, String msg) {
        if(status == TRUE) {
            System.out.println("Success: " + msg);
        } else {
            System.out.println("Error: " + msg);
        }
    }
}
