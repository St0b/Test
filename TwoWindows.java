import javax.swing.*;

public class TwoWindows {
    
    public static void main(String[] args) {
        // Запускаем приложение в потоке обработки событий
        SwingUtilities.invokeLater(() -> new TwoWindows().showInputDialog());
    }
    
    public void showInputDialog() {
        // Создаём панель для ввода имени
        JPanel panel = new JPanel();
        JTextField textField = new JTextField(15);
        panel.add(new JLabel("Введите ваше имя:"));
        panel.add(textField);
        
        // Показываем диалоговое окно с полем ввода
        int result = JOptionPane.showConfirmDialog(
            null,
            panel,
            "Ввод имени",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE
        );
        
        // Если пользователь нажал OK и ввёл имя
        if (result == JOptionPane.OK_OPTION) {
            String name = textField.getText().trim();
            
            // Если имя не пустое, показываем информационное окно
            if (!name.isEmpty()) {
                JOptionPane.showMessageDialog(
                    null,
                    name,
                    "Информация",
                    JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                // Если имя не введено, показываем сообщение об ошибке
                JOptionPane.showMessageDialog(
                    null,
                    "Вы не ввели имя!",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}