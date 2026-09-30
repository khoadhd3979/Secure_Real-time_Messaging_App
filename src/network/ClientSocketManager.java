package network;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocketManager {
    private static Socket socket;
    private static ObjectOutputStream out;
    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT = 5000;

    public static boolean connect() {
        try {
            if (socket == null || socket.isClosed()) {
                socket = new Socket(SERVER_IP, SERVER_PORT);
                out = new ObjectOutputStream(socket.getOutputStream());
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void sendPacket(Object payload) {
        try {
            if (out != null) {
                out.writeObject(payload);
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Socket getSocket() {
        return socket;
    }
}