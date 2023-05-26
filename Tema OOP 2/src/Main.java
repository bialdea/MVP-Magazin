import View.LoginFrame;

import javax.swing.*;

public class Main {
        public static void main(String[] args) {

                SwingUtilities.invokeLater(() -> {
                        try {
                                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                        LoginFrame loginForm = new LoginFrame();
                        loginForm.initialize();
                        loginForm.setVisible(true);
                });

        }
}
