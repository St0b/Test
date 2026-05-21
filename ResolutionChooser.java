import java.awt.*;
import javax.swing.*;

public class ResolutionChooser {
    
    private JFrame mainFrame;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ResolutionChooser().showResolutionDialog());
    }
    
    public void showResolutionDialog() {
        // Создаём панель для выбора разрешения
        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Добавляем метку
        JLabel label = new JLabel("Выберите разрешение экрана:");
        label.setFont(new Font("Arial", Font.BOLD, 14));
        panel.add(label);
        
        // Создаём выпадающий список с вариантами разрешений
        String[] resolutions = {
            "640x480",
            "800x600",
            "1024x768",
            "1280x720",
            "1366x768",
            "1920x1080",
            "2560x1440",
            "3840x2160"
        };
        
        JComboBox<String> comboBox = new JComboBox<>(resolutions);
        comboBox.setSelectedIndex(2); // По умолчанию 1024x768
        panel.add(comboBox);
        
        // Показываем диалоговое окно
        int result = JOptionPane.showConfirmDialog(
            null,
            panel,
            "Выбор разрешения экрана",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE
        );
        
        // Если пользователь нажал OK
        if (result == JOptionPane.OK_OPTION) {
            String selectedResolution = (String) comboBox.getSelectedItem();
            applyResolution(selectedResolution);
        } else {
            System.exit(0); // Закрываем приложение при отмене
        }
    }
    
    private void applyResolution(String resolution) {
        // Разбираем строку разрешения
        String[] parts = resolution.split("x");
        int width = Integer.parseInt(parts[0]);
        int height = Integer.parseInt(parts[1]);
        
        // Создаём главное окно с выбранным разрешением
        mainFrame = new JFrame("Главное окно - " + resolution);
        mainFrame.setSize(width, height);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        
        // Добавляем информационную панель
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel infoLabel = new JLabel("Разрешение экрана: " + resolution);
        infoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel sizeLabel = new JLabel("Ширина: " + width + " px, Высота: " + height + " px");
        sizeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        sizeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        contentPanel.add(Box.createVerticalGlue());
        contentPanel.add(infoLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(sizeLabel);
        contentPanel.add(Box.createVerticalGlue());
        
        mainFrame.add(contentPanel);
        mainFrame.setVisible(true);
    }
}