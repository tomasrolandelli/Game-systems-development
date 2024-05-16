
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle {
    int id;
    int x;
    int y1;
    int y2;
    int width;
    int height;
    int yVelocity1;
    int yVelocity2;
    int speed = 10;

    Paddle(int x, int y, int width, int height, int id) {
        this.x = x;
        if (id == 1) {
            this.y1 = y;
        } else if (id == 2) {
            this.y2 = y;
        }

        this.width = width;
        this.height = height;
        this.id = id;
    }

    public void pressed(KeyEvent e) {
        if (e.getKeyCode() == 87) {
            this.setYDirection(-this.speed, 1);
        } else if (e.getKeyCode() == 83) {
            this.setYDirection(this.speed, 1);
        }

        if (e.getKeyCode() == 38) {
            this.setYDirection(-this.speed, 2);
        } else if (e.getKeyCode() == 40) {
            this.setYDirection(this.speed, 2);
        }

        this.move();
    }

    public void released(KeyEvent e) {
        if (e.getKeyCode() == 87) {
            this.setYDirection(0, 1);
        } else if (e.getKeyCode() == 83) {
            this.setYDirection(0, 1);
        }

        if (e.getKeyCode() == 38) {
            this.setYDirection(0, 2);
        } else if (e.getKeyCode() == 40) {
            this.setYDirection(0, 2);
        }

        this.move();
    }

    public void setYDirection(int yDirection, int paddleId) {
        if (paddleId == 1) {
            this.yVelocity1 = yDirection;
        } else if (paddleId == 2) {
            this.yVelocity2 = yDirection;
        }

    }

    public void move() {
        this.y1 += this.yVelocity1;
        this.y2 += this.yVelocity2;
    }

    public void draw(Graphics g) {
        if (this.id == 1) {
            g.setColor(Color.BLUE);
            g.fillRect(this.x, this.y1, this.width, this.height);
        } else {
            g.setColor(Color.YELLOW);
            g.fillRect(this.x, this.y2, this.width, this.height);
        }

    }
}

