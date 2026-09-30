package network;

import gui.DashboardFrame;
import javax.swing.*;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientReceiver extends Thread {
    private Socket socket;
    private DashboardFrame view;

    public ClientReceiver(Socket socket, DashboardFrame view) {
        this.socket = socket;
        this.view = view;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            while (true) {
                Object receivedObject = in.readObject();
                if (receivedObject != null) {
                    String content = receivedObject.toString();

                    SwingUtilities.invokeLater(() -> {
                        if (view != null) {
                            if (content.startsWith("FRIENDS|")) {
                                String[] friends = content.substring(8).split(",");
                                view.updateFriendList(friends);
                            } else {
                                view.appendMessage("[Server]: " + content);
                            }
                        }
                    });
                }
            }
        } catch (Exception e) {
            SwingUtilities.invokeLater(() -> {
                JOptionPane.showMessageDialog(null, "Mất kết nối tới Server!", "Lỗi mạng", JOptionPane.ERROR_MESSAGE);
            });
        }
    }
}