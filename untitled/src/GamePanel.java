import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    static final int FRAME_WIDTH = 1000;
    static final int FRAME_HEIGHT = 500;
    static final int BALL_DIAMETER = 26;
    static final int PADDLE_WIDTH = 25;
    static int PADDLE_HEIGHT = 80;
    static final Dimension SCREEN = new Dimension(1000, 500);
    int peddleOneHeight;
    int peddleTwoHeight;
    int hitCounter;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Paddle paddle_1;
    Paddle paddle_2;
    Ball ball;
    Score score = new Score(1000, 500);
    CollisionChecker collisionChecker = new CollisionChecker();

    GamePanel() {
        this.newBall();
        this.newPeddle();
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN);
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    public void newBall() {
        this.ball = new Ball(487, 237, 26, 26);
    }

    public void newPeddle() {
        this.peddleOneHeight = this.peddleHeightController(this.score.player_1);
        this.peddleTwoHeight = this.peddleHeightController(this.score.player_2);
        this.paddle_1 = new Paddle(25, 250 - this.peddleOneHeight / 2, 25, this.peddleOneHeight,1);
        this.paddle_2 = new Paddle(950, 250 - this.peddleTwoHeight / 2, 25, this.peddleTwoHeight,2);

    }

    public void paint(Graphics g) {
        this.image = this.createImage(this.getWidth(), this.getHeight());
        this.graphics = this.image.getGraphics();
        this.draw(this.graphics);
        g.drawImage(this.image, 0, 0, this);
    }

    public void draw(Graphics g) {
        this.score.draw(g);
        this.paddle_1.draw(g);
        this.paddle_2.draw(g);
        this.ball.draw(g);
    }

    public void move() {
        this.paddle_1.move();
        this.paddle_2.move();
        this.ball.move();
    }
    public int peddleHeightController(int score){
        return PADDLE_HEIGHT + score * 10;
    }

    public void checkCollision() {
        if (this.collisionChecker.didTouchTopOrBottomEdge(this.ball.y, 474)) {
            this.ball.setYDirection(-this.ball.yVelocity);
        }

        if (this.collisionChecker.didTouchLeftGoalEdges(this.ball.x, this.ball.y, 500)) {
            this.ball.xVelocity = Math.abs(this.ball.xVelocity);
            this.ball.setXDirection(this.ball.xVelocity);
            this.ball.setYDirection(this.ball.yVelocity);
        }

        if (this.collisionChecker.didTouchRightGoalEdges(this.ball.x, this.ball.y, 500, 974)) {
            this.ball.xVelocity = Math.abs(this.ball.xVelocity);
            this.ball.setXDirection(-this.ball.xVelocity);
            this.ball.setYDirection(this.ball.yVelocity);
        }

        if (this.collisionChecker.didTouchPaddle(this.ball, new Rectangle(this.paddle_1.x, this.paddle_1.y1, this.paddle_1.width, this.paddle_1.height))) {
            this.ball.xVelocity = Math.abs(this.ball.xVelocity);
            if (this.hitCounter < 30) {
                ++this.ball.xVelocity;
                if (this.ball.yVelocity > 0 ) {
                    ++this.ball.yVelocity;
                } else {
                    --this.ball.yVelocity;
                }
            }
            this.ball.setXDirection(this.ball.xVelocity);
            this.ball.setYDirection(this.ball.yVelocity);
            ++this.hitCounter;
        }


        if (this.collisionChecker.didTouchPaddle(this.ball, new Rectangle(this.paddle_2.x, this.paddle_2.y2, this.paddle_2.width, this.paddle_2.height))) {
            this.ball.xVelocity = Math.abs(this.ball.xVelocity);
            if (this.hitCounter < 30) {
                ++this.ball.xVelocity;
                if (this.ball.yVelocity > 0 ) {
                    ++this.ball.yVelocity;
                } else {
                    --this.ball.yVelocity;
                }
            }
            this.ball.setXDirection(-this.ball.xVelocity);
            this.ball.setYDirection(this.ball.yVelocity);
            ++this.hitCounter;
        }

        if (this.paddle_1.y1 <= 0) {
            this.paddle_1.y1 = 0;
        }

        if (this.paddle_1.y1 >= 500 - this.peddleOneHeight) {
            this.paddle_1.y1 = 500 - this.peddleOneHeight;
        }

        if (this.paddle_2.y2 <= 0) {
            this.paddle_2.y2 = 0;
        }

        if (this.paddle_2.y2 >= 500 - this.peddleTwoHeight) {
            this.paddle_2.y2 = 500 - this.peddleTwoHeight;
        }
        this.peddleOneHeight = this.peddleHeightController(this.score.player_1);
        this.peddleTwoHeight = this.peddleHeightController(this.score.player_2);

        if (this.collisionChecker.didTouchLeftGoalPost(this.ball.x, this.ball.y, 500)) {
            ++this.score.player_2;
            this.newPeddle();
            this.newBall();
        }

        if (this.collisionChecker.didTouchRightGoalPost(this.ball.x, this.ball.y, 500, 974)) {
            ++this.score.player_1;
            this.newPeddle();
            this.newBall();
        }

        if (this.score.player_1 == 10 || this.score.player_2 == 10) {
            this.score.player_1 = 0;
            this.score.player_2 = 0;
            this.newPeddle();
            this.newBall();
        }

    }

    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double nanoseconds = 1.0E9 / amountOfTicks;
        double delta = 0.0;

        while(true) {
            do {
                long now = System.nanoTime();
                delta += (double)(now - lastTime) / nanoseconds;
                lastTime = now;
            } while(!(delta >= 1.0));

            this.move();
            this.checkCollision();
            this.repaint();
            --delta;
        }
    }

    public class AL extends KeyAdapter {
        public AL() {
        }

        public void keyPressed(KeyEvent e) {
            GamePanel.this.paddle_1.pressed(e);
            GamePanel.this.paddle_2.pressed(e);
        }

        public void keyReleased(KeyEvent e) {
            GamePanel.this.paddle_1.released(e);
            GamePanel.this.paddle_2.released(e);
        }
    }
}

