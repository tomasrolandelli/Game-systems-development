

import java.awt.Rectangle;

public class CollisionChecker {
    public CollisionChecker() {
    }

    public Boolean didTouchTopOrBottomEdge(Integer ballYCoordinate, Integer maxHeight) {
        return ballYCoordinate <= 0 || ballYCoordinate >= maxHeight;
    }

    public Boolean didTouchPaddle(Rectangle ball, Rectangle paddle) {
        return ball.intersects(paddle);
    }

    public Boolean didTouchLeftGoalPost(Integer ballXCoordinate, Integer ballYCoordinate, Integer FRAME_HEIGHT) {
        return ballXCoordinate < 10 && ballYCoordinate >= FRAME_HEIGHT / 4 || ballXCoordinate < 10 && ballYCoordinate <= FRAME_HEIGHT / 4 * 3;
    }

    public Boolean didTouchRightGoalPost(Integer ballXCoordinate, Integer ballYCoordinate, Integer FRAME_HEIGHT, Integer maxWidth) {
        return ballXCoordinate > maxWidth - 10 && ballYCoordinate >= FRAME_HEIGHT / 4 || ballXCoordinate > maxWidth - 10 && ballYCoordinate <= FRAME_HEIGHT / 4 * 3;
    }

    public Boolean didTouchLeftGoalEdges(Integer ballXCoordinate, Integer ballYCoordinate, Integer FRAME_HEIGHT) {
        return ballXCoordinate <= 30 && ballYCoordinate <= FRAME_HEIGHT / 4 || ballXCoordinate <= 30 && ballYCoordinate >= FRAME_HEIGHT / 4 * 3;
    }

    public Boolean didTouchRightGoalEdges(Integer ballXCoordinate, Integer ballYCoordinate, Integer FRAME_HEIGHT, Integer maxWidth) {
        return ballXCoordinate >= maxWidth - 30 && ballYCoordinate <= FRAME_HEIGHT / 4 || ballXCoordinate >= maxWidth - 30 && ballYCoordinate >= FRAME_HEIGHT / 4 * 3;
    }
}
