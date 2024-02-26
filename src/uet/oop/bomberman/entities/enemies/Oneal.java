package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.bomberman;

public class Oneal extends Enemy {
    public Oneal(int rx, int ry, Image img, boolean isALive, String direction) {
        super(rx, ry, img, isALive, direction);
        this.delayTime = ONEAL_DELAY_TIME;
        // Set img
        upImg[0] = Sprite.oneal_left.getFxImage();
        upImg[1] = Sprite.oneal_left_1.getFxImage();
        upImg[2] = Sprite.oneal_left_2.getFxImage();

        downImg[0] = Sprite.oneal_right.getFxImage();
        downImg[1] = Sprite.oneal_right_1.getFxImage();
        downImg[2] = Sprite.oneal_right_2.getFxImage();

        leftImg[0] = Sprite.oneal_left.getFxImage();
        leftImg[1] = Sprite.oneal_left_1.getFxImage();
        leftImg[2] = Sprite.oneal_left_2.getFxImage();

        rightImg[0] = Sprite.oneal_right.getFxImage();
        rightImg[1] = Sprite.oneal_right_1.getFxImage();
        rightImg[2] = Sprite.oneal_right_2.getFxImage();
    }

    // Oneal follow bomber base on X and Y cordinate of bomber
    @Override
    public void move() {
        if (isAvailToTakeNewSteps()) {
            // Avoid move in X axis and Y axis at the same time as well as move diagonally

            if (bomberman.getX() < this.getX()) {
                this.setMove("left");
                return;
            } else if (bomberman.getX() > this.getX()) {
                this.setMove("right");
                return;
            }

            // If enemy x = bomber x
            if (bomberman.getY() > this.getY()) {
                this.setMove("down");
            } else if (bomberman.getY() < this.getY()) {
                this.setMove("up");
            }
        }
    }
}