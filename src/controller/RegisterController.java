package controller;

import gui.LoginFrame;
import gui.RegisterFrame;
import network.ClientSocketManager;
import utils.ValidateUtils;

import javax.swing.*;

public class RegisterController {
    private RegisterFrame view;

    public RegisterController(RegisterFrame view) {
        this.view = view;
        initController();
    }

    private void initController() {
        view.setBackListener(e -> {
            LoginFrame loginView = new LoginFrame();
            new LoginController(loginView);
            loginView.setVisible(true);
            view.dispose();
        });

        view.setRegisterListener(e -> {
            String email = view.getEmail();
            String pass = view.getPassword();
            String name = view.getFullName();

            if (email.isEmpty() || pass.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(view.getFrame(), "Vui lòng điền đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!ValidateUtils.isValidEmail(email)) {
                JOptionPane.showMessageDialog(view.getFrame(), "Định dạng Email không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!ValidateUtils.isValidPassword(pass)) {
                JOptionPane.showMessageDialog(view.getFrame(), "Mật khẩu phải có ít nhất 6 ký tự!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                if (ClientSocketManager.connect()) {
                    String payload = "REGISTER|" + email + "|" + pass + "|" + name;
                    ClientSocketManager.sendPacket(payload);
                    JOptionPane.showMessageDialog(view.getFrame(), "Đã gửi yêu cầu đăng ký cho: " + email + "\nĐang chờ Server xử lý...");
                } else {
                    JOptionPane.showMessageDialog(view.getFrame(), "Không thể kết nối đến Server!", "Lỗi mạng", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}