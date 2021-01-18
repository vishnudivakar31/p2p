package io.vdev.util;
import java.io.*;
import java.util.Base64;

public class P2PUtil {

    public static byte[] encode(byte[] data) {
        return Base64.getEncoder().encode(data);
    }

    public static byte[] decode(byte[] encodedBytes) {
        return Base64.getDecoder().decode(encodedBytes);
    }

    public static byte[] convertToByteArray(Object classObj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(classObj);
        return out.toByteArray();
    }

    public static Object convertFromByteArray(byte[] byteArray) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(byteArray);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }


}
