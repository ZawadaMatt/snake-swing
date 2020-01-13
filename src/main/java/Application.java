import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame ex = new Application();
            ex.setVisible(true);
        });
    }

    public Application() {
        initUI();
    }

    private void initUI() {
        add(new Snake());

        setResizable(false);
        pack();

        setTitle("Snake");
        setLocationRelativeTo(null);
        setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}


