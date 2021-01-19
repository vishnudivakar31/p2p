import io.vdev.socket.Message;
import io.vdev.socket.Sender;

import java.util.Scanner;

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
