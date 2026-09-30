package controller;

import gui.DashboardFrame;
import network.ClientPacketHandler;
import network.ClientReceiver;
import network.ClientSocketManager;

import javax.swing.*;
import java.io.File;
import java.util.List;

public class DashboardController {
    private DashboardFrame view;

    public DashboardController(DashboardFrame view) {
        this.view = view;
        initController();
        startReceiver();
    }

    private void initController() {
        view.addSendListener(e -> {
            String msg = view.getMessageInput();
            if (!msg.isEmpty()) {
                view.appendMessage("[Tôi]: " + msg);
                ClientSocketManager.sendPacket("MSG|" + msg);
                view.clearMessageInput();
            }
        });

        view.addAttachListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int option = fileChooser.showOpenDialog(view.getFrame());
            if (option == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                if (selectedFile.length() > 1024 * 1024) {
                    JOptionPane.showMessageDialog(view.getFrame(), "File phải dưới 1MB!", "Từ chối", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    List<byte[]> chunks = ClientPacketHandler.splitFileIntoChunks(selectedFile);
                    view.appendMessage("[Hệ thống]: Đang gửi file " + selectedFile.getName() + " (" + chunks.size() + " gói nhỏ)...");

                    // TODO: Gửi từng chunk qua ClientSocketManager
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(view.getFrame(), "Lỗi đọc file!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }


    private void startReceiver() {
        if (ClientSocketManager.getSocket() != null) {
            ClientReceiver receiver = new ClientReceiver(ClientSocketManager.getSocket(), view);
            receiver.start();
        }
    }
}