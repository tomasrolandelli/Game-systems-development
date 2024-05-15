import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {
    MenuPanel() {
        this.addKeyListener(new AL(this));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.setPreferredSize(GamePanel.SCREEN);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.draw(g);
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", 1, 70));
        g.drawString("GAME MENU", 330, 100);
        g.setFont(new Font("Consolas", 1, 30));
        g.drawString("Press 'ENTER' to start the game", 265, 200);
        g.setFont(new Font("Consolas", 0, 10));
    }

    public class AL extends KeyAdapter {
        public AL(final MenuPanel this$0) {
        }

        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == 10) {
                new GameFrame();
            }

        }
    }
}