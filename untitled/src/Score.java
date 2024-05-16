import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Score extends Rectangle{
    public final int COURT_CIRCLE = 150;
    public final int CENTER_DOT = 10;
    static int FRAME_WIDTH;
    static int FRAME_HEIGHT;
    int player_1;
    int player_2;

    Score(int FRAME_WIDTH, int FRAME_HEIGHT) {
        Score.FRAME_WIDTH = FRAME_WIDTH;
        Score.FRAME_HEIGHT = FRAME_HEIGHT;
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", 1, 50));
        g.drawLine(FRAME_WIDTH / 2, 0, FRAME_WIDTH / 2, FRAME_HEIGHT);
        int lineWidth = 25;
        g.fillRect(0, 0, lineWidth, FRAME_HEIGHT / 4);
        g.fillRect(0, FRAME_HEIGHT / 4 * 3, lineWidth, FRAME_HEIGHT);
        g.fillRect(FRAME_WIDTH - lineWidth, 0, lineWidth, FRAME_HEIGHT / 4);
        g.fillRect(FRAME_WIDTH - lineWidth, FRAME_HEIGHT / 4 * 3, lineWidth, FRAME_HEIGHT);
        g.drawOval(FRAME_WIDTH / 2 - 75, FRAME_HEIGHT / 2 - 75, 150, 150);
        g.fillOval(FRAME_WIDTH / 2 - 5, FRAME_HEIGHT / 2 - 5, 10, 10);
        g.drawString(String.valueOf(this.player_1 / 10) + String.valueOf(this.player_1 % 10), FRAME_WIDTH / 2 - 75, 50);
        g.drawString(String.valueOf(this.player_2 / 10) + String.valueOf(this.player_2 % 10), FRAME_WIDTH / 2 + 20, 50);
    }
}
