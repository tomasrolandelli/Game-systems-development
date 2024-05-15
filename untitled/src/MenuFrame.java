import javax.swing.*;


public class MenuFrame extends JFrame {

    MenuPanel panel;

    MenuFrame() {
        panel = new MenuPanel();
        this.add(panel);
        this.setTitle("Main Manu for Ping-Pong");
        this.setResizable(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}