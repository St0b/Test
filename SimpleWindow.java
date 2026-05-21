import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SimpleWindow extends JFrame {
    
    public SimpleWindow() {
        setTitle("Простое окно");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setFocusable(true);
        
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    JOptionPane.showMessageDialog(SimpleWindow.this, 
                        "Кирилл", 
                        "Информация", 
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        
        add(panel);

        setVisible(true);
        panel.requestFocusInWindow();
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimpleWindow());
    }
}