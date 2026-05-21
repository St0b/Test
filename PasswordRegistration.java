import javax.swing.*;
import java.awt.*;

public class PasswordRegistration {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PasswordRegistration().start());
    }
    
    public void start() {
        // 1) Окно приветствия и предложение зарегистрироваться
        int welcomeResult = JOptionPane.showConfirmDialog(
            null,
            "Добро пожаловать! Желаете зарегистрироваться в программе?",
            "Приветствие",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        if (welcomeResult != JOptionPane.YES_OPTION) {
            System.exit(0);
        }
        
        // 2) Окно ввода логина
        String login = getValidLogin();
        
        // 3) Окно ввода пароля
        String password = getValidPassword();
        
        // 4) Окно подтверждения пароля
        confirmPassword(password);
        
        // 5) Информационное окно об успешной регистрации
        JOptionPane.showMessageDialog(
            null,
            "Регистрация успешно завершена!\n\n"
            + "Ваш логин: " + login + "\n"
            + "Добро пожаловать в систему!",
            "Успешная регистрация",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    private String getValidLogin() {
        String login = "";
        boolean isValid = false;
        
        while (!isValid) {
            JPanel panel = new JPanel(new BorderLayout(10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            JLabel label = new JLabel("Введите логин (более 5 символов, без пробелов):");
            JTextField textField = new JTextField(20);
            
            panel.add(label, BorderLayout.NORTH);
            panel.add(textField, BorderLayout.CENTER);
            
            int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Регистрация - ввод логина",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
            );
            
            if (result != JOptionPane.OK_OPTION) {
                System.exit(0);
            }
            
            login = textField.getText().trim();
            
            if (login.length() > 5 && !login.contains(" ")) {
                isValid = true;
            } else {
                String errorMessage = "";
                if (login.length() <= 5) {
                    errorMessage += "Логин должен быть длиннее 5 символов.\n";
                }
                if (login.contains(" ")) {
                    errorMessage += "Логин не должен содержать пробелов.\n";
                }
                JOptionPane.showMessageDialog(
                    null,
                    errorMessage,
                    "Ошибка ввода логина",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
        
        return login;
    }
    
    private String getValidPassword() {
        String password = "";
        boolean isValid = false;
        
        while (!isValid) {
            JPanel panel = new JPanel(new BorderLayout(10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            JLabel label = new JLabel("<html>Введите пароль (более 8 символов, без пробелов,<br>содержит хотя бы одну цифру и хотя бы одну букву):</html>");
            JPasswordField passwordField = new JPasswordField(20);
            
            panel.add(label, BorderLayout.NORTH);
            panel.add(passwordField, BorderLayout.CENTER);
            
            int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Регистрация - ввод пароля",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
            );
            
            if (result != JOptionPane.OK_OPTION) {
                System.exit(0);
            }
            
            password = new String(passwordField.getPassword());
            
            boolean hasDigit = false;
            boolean hasLetter = false;
            
            for (char c : password.toCharArray()) {
                if (Character.isDigit(c)) {
                    hasDigit = true;
                }
                if (Character.isLetter(c)) {
                    hasLetter = true;
                }
            }
            
            if (password.length() > 8 && !password.contains(" ") && hasDigit && hasLetter) {
                isValid = true;
            } else {
                String errorMessage = "";
                if (password.length() <= 8) {
                    errorMessage += "Пароль должен быть длиннее 8 символов.\n";
                }
                if (password.contains(" ")) {
                    errorMessage += "Пароль не должен содержать пробелов.\n";
                }
                if (!hasDigit) {
                    errorMessage += "Пароль должен содержать хотя бы одну цифру.\n";
                }
                if (!hasLetter) {
                    errorMessage += "Пароль должен содержать хотя бы одну букву.\n";
                }
                JOptionPane.showMessageDialog(
                    null,
                    errorMessage,
                    "Ошибка ввода пароля",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
        
        return password;
    }
    
    private void confirmPassword(String originalPassword) {
        boolean isValid = false;
        
        while (!isValid) {
            JPanel panel = new JPanel(new BorderLayout(10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            JLabel label = new JLabel("Повторите пароль:");
            JPasswordField passwordField = new JPasswordField(20);
            
            panel.add(label, BorderLayout.NORTH);
            panel.add(passwordField, BorderLayout.CENTER);
            
            int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Регистрация - подтверждение пароля",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
            );
            
            if (result != JOptionPane.OK_OPTION) {
                System.exit(0);
            }
            
            String confirmedPassword = new String(passwordField.getPassword());
            
            if (confirmedPassword.equals(originalPassword)) {
                isValid = true;
            } else {
                JOptionPane.showMessageDialog(
                    null,
                    "Пароли не совпадают. Попробуйте снова.",
                    "Ошибка подтверждения пароля",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}