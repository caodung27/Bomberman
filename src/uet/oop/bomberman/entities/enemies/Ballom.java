package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Ballom extends Enemy {
    private static final Random random = new Random();
    private int lastRandomMove = -1;

    public Ballom(int rx, int ry, Image img, boolean isALive, String direction) {
        super(rx, ry, img, isALive, direction);
        this.delayTime = BALLOM_DELAY_TIME;
        // Set img
        upImg[0] = Sprite.balloom_left.getFxImage();
        upImg[1] = Sprite.balloom_left_1.getFxImage();
        upImg[2] = Sprite.balloom_left_2.getFxImage();

        downImg[0] = Sprite.balloom_right.getFxImage();
        downImg[1] = Sprite.balloom_right_1.getFxImage();
        downImg[2] = Sprite.balloom_right_2.getFxImage();

        leftImg[0] = Sprite.balloom_left.getFxImage();
        leftImg[1] = Sprite.balloom_left_1.getFxImage();
        leftImg[2] = Sprite.balloom_left_2.getFxImage();

        rightImg[0] = Sprite.balloom_right.getFxImage();
        rightImg[1] = Sprite.balloom_right_1.getFxImage();
        rightImg[2] = Sprite.balloom_right_2.getFxImage();
    }

    // Ballom move randomly
    @Override
    public void move() {
        if (isAvailToTakeNewSteps()) {
            // Generate random step that different from previous step
            int randomDirection;
            do {
                randomDirection = random.nextInt(4);
            } while (randomDirection == this.getLastRandomMove());
            this.setLastRandomMove(randomDirection);

            // Move
            switch (randomDirection) {
                case 0:
                    this.setMove("up");
                    break;
                case 1:
                    this.setMove("down");
                    break;
                case 2:
                    this.setMove("left");
                    break;
                case 3:
                    this.setMove("right");
                    break;
            }
        }
    }

    public int getLastRandomMove() {
        return lastRandomMove;
    }

    public void setLastRandomMove(int lastRandomMove) {
        this.lastRandomMove = lastRandomMove;
    }
}