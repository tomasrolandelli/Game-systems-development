import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball extends Rectangle {
    Random random = new Random();
    int xVelocity;
    int yVelocity;
    int ballSpeed = 3;

    Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        int randomXDirection = this.random.nextInt(2);
        if (randomXDirection == 0) {
            --randomXDirection;
        }

        this.setXDirection(randomXDirection * this.ballSpeed);
        int randomYDirection = this.random.nextInt(2);
        if (randomYDirection == 0) {
            --randomYDirection;
        }

        this.setYDirection(randomYDirection * this.ballSpeed);
    }

    public void setXDirection(int randomXDirection) {
        this.xVelocity = randomXDirection;
    }

    public void setYDirection(int randomYDirection) {
        this.yVelocity = randomYDirection;
    }

    public void move() {
        this.x += this.xVelocity;
        this.y += this.yVelocity;
    }

    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(this.x, this.y, this.width, this.height);
    }
}
