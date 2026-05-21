import java.awt.*;
import javax.swing.*;

public class CustomResolutionChooser {
    
    private JFrame mainFrame;
    private ButtonGroup resolutionGroup;
    private JRadioButton[] radioButtons;
    private String[] resolutions;
    private JDialog dialog;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CustomResolutionChooser().showCustomDialog());
    }
    
    public void showCustomDialog() {
        // Создаём пользовательское диалоговое окно
        dialog = new JDialog((Frame) null, "Выбор разрешения экрана", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(350, 300);
        dialog.setLocationRelativeTo(null);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        
        // Создаём панель для радиокнопок
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
        radioPanel.setBorder(BorderFactory.createTitledBorder("Выберите разрешение:"));
        
        // Варианты разрешений
        resolutions = new String[]{
            "640x480 (4:3)",
            "800x600 (4:3)",
            "1024x768 (4:3)",
            "1280x720 (16:9)",
            "1366x768 (16:9)",
            "1920x1080 (16:9)"
        };
        
        resolutionGroup = new ButtonGroup();
        radioButtons = new JRadioButton[resolutions.length];
        
        for (int i = 0; i < resolutions.length; i++) {
            radioButtons[i] = new JRadioButton(resolutions[i]);
            radioButtons[i].setFont(new Font("Arial", Font.PLAIN, 12));
            resolutionGroup.add(radioButtons[i]);
            radioPanel.add(radioButtons[i]);
            radioPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        
        // Выбираем по умолчанию 1024x768 (индекс 2)
        radioButtons[2].setSelected(true);
        
        // Панель с кнопками OK и Отмена
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Отмена");
        
        okButton.addActionListener(e -> {
            String selectedResolution = getSelectedResolution();
            dialog.dispose();
            applyResolution(selectedResolution);
        });
        
        cancelButton.addActionListener(e -> {
            dialog.dispose();
            System.exit(0);
        });
        
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        
        // Добавляем все панели в диалоговое окно
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(radioPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.add(mainPanel);
        dialog.setVisible(true);
    }
    
    private String getSelectedResolution() {
        for (int i = 0; i < radioButtons.length; i++) {
            if (radioButtons[i].isSelected()) {
                return resolutions[i];
            }
        }
        return resolutions[2];
    }
    
    private void applyResolution(String resolution) {
        // Разбираем строку разрешения
        String[] parts = resolution.split(" ");
        String[] size = parts[0].split("x");
        int width = Integer.parseInt(size[0]);
        int height = Integer.parseInt(size[1]);
        
        // Создаём главное окно с выбранным разрешением
        mainFrame = new JFrame("Главное окно - " + resolution);
        mainFrame.setSize(width, height);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        
        // Добавляем информационную панель
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(new Color(240, 248, 255));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        
        JLabel titleLabel = new JLabel("Выбранное разрешение:", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel resolutionLabel = new JLabel(resolution, SwingConstants.CENTER);
        resolutionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        resolutionLabel.setForeground(new Color(0, 102, 204));
        resolutionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel sizeLabel = new JLabel(width + " x " + height + " пикселей", SwingConstants.CENTER);
        sizeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        sizeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        contentPanel.add(Box.createVerticalGlue());
        contentPanel.add(titleLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        contentPanel.add(resolutionLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(sizeLabel);
        contentPanel.add(Box.createVerticalGlue());
        
        mainFrame.add(contentPanel);
        mainFrame.setVisible(true);
    }
}