import javax.swing.*;

public class ConfirmationWindows {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Первый вопрос
            int answer1 = JOptionPane.showConfirmDialog(
                null,
                "Вы любите программировать?",
                "Вопрос 1",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            
            // Второй вопрос
            int answer2 = JOptionPane.showConfirmDialog(
                null,
                "Вы любите кофе?",
                "Вопрос 2",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            
            // Определяем результат в зависимости от ответов
            String result;
            
            if (answer1 == JOptionPane.YES_OPTION && answer2 == JOptionPane.YES_OPTION) {
                result = "Вы настоящий программист! Любите код и кофе — отличное сочетание! ☕💻";
            } else if (answer1 == JOptionPane.YES_OPTION && answer2 == JOptionPane.NO_OPTION) {
                result = "Вы любите программировать, но не пьёте кофе. Возможно, вы предпочитаете чай! 🍵💻";
            } else if (answer1 == JOptionPane.NO_OPTION && answer2 == JOptionPane.YES_OPTION) {
                result = "Вы не программист, но любите кофе. Может быть, вы дизайнер или менеджер? ☕🎨";
            } else {
                result = "Вы не любите ни программировать, ни пить кофе. Чем же вы тогда занимаетесь? 🤔";
            }
            
            // Показываем итоговое информационное окно
            JOptionPane.showMessageDialog(
                null,
                result,
                "Результат",
                JOptionPane.INFORMATION_MESSAGE
            );
        });
    }
}