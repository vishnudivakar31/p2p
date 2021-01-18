import io.vdev.util.P2PUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Serializable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Message implements Serializable {
    private String message;
    private boolean status;

    public Message() {}

    public Message(String message, boolean status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String toString() {
        return "message: " + this.message + ", status: " + this.status;
    }

    public Boolean equals(Message msg) {
        return this.message.equals(msg.getMessage()) && this.status == msg.getStatus();
    }
}

@DisplayName("P2PUtil Test Suite")
public class P2PUtilTest {
    @Test
    @DisplayName("Testing for encoding and decoding methods")
    public void testCoding() {
        String originalMessage = "Hi. This is a test";
        byte[] encodedMessage = P2PUtil.encode(originalMessage.getBytes());
        byte[] decodedMessage = P2PUtil.decode(encodedMessage);
        assertEquals(originalMessage, new String(decodedMessage));
    }

    @Test
    @DisplayName("Testing for serialization methods")
    public void serializationTest() throws IOException, ClassNotFoundException {
        Message msg = new Message("TEST 1", true);
        byte[] msgBytes = P2PUtil.convertToByteArray(msg);
        Message newMsg = (Message) P2PUtil.convertFromByteArray(msgBytes);
        assertTrue(newMsg.equals(msg));
    }

    @Test
    @DisplayName("Testing for end-end encoding/decoding and serialization methods")
    public void codingAndSerializationTest() throws IOException, ClassNotFoundException {
        Message msg = new Message("TEST 1", true);
        byte[] encodedMsg = P2PUtil.encode(P2PUtil.convertToByteArray(msg));
        byte[] decodedMessage = P2PUtil.decode(encodedMsg);
        Message newMsg = (Message) P2PUtil.convertFromByteArray(decodedMessage);
        assertTrue(newMsg.equals(msg));
    }
}
