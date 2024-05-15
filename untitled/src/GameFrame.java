import java.awt.Color;
import java.awt.Component;
import javax.swing.JFrame;

public class GameFrame extends JFrame {
    GamePanel panel = new GamePanel();

    GameFrame() {
        this.add(this.panel);
        this.setTitle("2D Ping-Pong");
        this.setResizable(false);
        this.setBackground(new Color(31, 78, 47));
        this.setDefaultCloseOperation(3);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo((Component)null);
    }
}