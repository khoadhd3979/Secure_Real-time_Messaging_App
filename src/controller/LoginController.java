package controller;

import gui.DashboardFrame;
import gui.LoginFrame;
import gui.RegisterFrame;
import network.ClientSocketManager;

import javax.swing.*;

public class LoginController {
    private LoginFrame view;

    public LoginController(LoginFrame view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.setLoginButtonListener(e -> {
            String email = view.getEmail();
            String password = view.getPassword();

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }

            try {
                boolean isConnected = ClientSocketManager.connect();
                if (isConnected) {
                    String payload = "LOGIN|" + email + "|" + password;
                    ClientSocketManager.sendPacket(payload);
                    DashboardFrame dashboardView = new DashboardFrame();
                    new DashboardController(dashboardView);
                    dashboardView.setVisible(true);
                    view.dispose();
                } else {
                    JOptionPane.showMessageDialog(view, "Không thể kết nối đến Server!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        view.setRegisterButtonListener(e -> {
            RegisterFrame regView = new RegisterFrame();
            new RegisterController(regView);
            regView.setVisible(true);
            view.dispose();
        });
    }
}