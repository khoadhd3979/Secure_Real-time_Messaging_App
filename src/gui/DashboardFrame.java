package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DashboardFrame extends JFrame {
    private JList<String> friendList;
    private DefaultListModel<String> friendListModel;
    private JTextPane chatArea;
    private JTextField txtMessage;
    private JButton btnSend, btnAttach;

    public DashboardFrame() {
        setTitle("SecureChat - Dashboard");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        friendListModel = new DefaultListModel<>();
        friendList = new JList<>(friendListModel);
        JScrollPane leftScroll = new JScrollPane(friendList);
        leftScroll.setPreferredSize(new Dimension(220, 0));
        add(leftScroll, BorderLayout.WEST);
        JPanel chatPanel = new JPanel(new BorderLayout());
        chatArea = new JTextPane();
        chatArea.setEditable(false);
        chatPanel.add(new JScrollPane(chatArea), BorderLayout.CENTER);
        JPanel inputPanel = new JPanel(new BorderLayout());
        txtMessage = new JTextField();
        btnSend = new JButton("Gửi");
        btnAttach = new JButton("Đính kèm");

        JPanel rightButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        rightButtons.add(btnAttach);
        rightButtons.add(btnSend);

        inputPanel.add(txtMessage, BorderLayout.CENTER);
        inputPanel.add(rightButtons, BorderLayout.EAST);
        chatPanel.add(inputPanel, BorderLayout.SOUTH);

        add(chatPanel, BorderLayout.CENTER);
    }

    public String getMessageInput() {
        return txtMessage.getText().trim();
    }

    public void clearMessageInput() {
        txtMessage.setText("");
    }

    public void appendMessage(String message) {
        chatArea.setText(chatArea.getText() + "\n" + message);
    }

    public JFrame getFrame() {
        return this;
    }

    public void addSendListener(ActionListener listener) {
        btnSend.addActionListener(listener);
    }

    public void addAttachListener(ActionListener listener) {
        btnAttach.addActionListener(listener);
    }
    public void updateFriendList(String[] friends) {
        friendListModel.clear();
        for (String f : friends) {
            friendListModel.addElement(f);
        }
    }
}