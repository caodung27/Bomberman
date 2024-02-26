package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.blocks.Bomb;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.Sound.Sound;

import static uet.oop.bomberman.BombermanGame.*;

public class Kondoria extends Minvo {
    public Kondoria(int rx, int ry, Image img, boolean isALive, String direction) {
        super(rx, ry, img, isALive, direction);
        this.delayTime = KONDORIA_DELAY_TIME;
        // Set img
        upImg[0] = Sprite.kondoria_left.getFxImage();
        upImg[1] = Sprite.kondoria_left_1.getFxImage();
        upImg[2] = Sprite.kondoria_left_2.getFxImage();

        downImg[0] = Sprite.kondoria_right.getFxImage();
        downImg[1] = Sprite.kondoria_right_1.getFxImage();
        downImg[2] = Sprite.kondoria_right_2.getFxImage();

        leftImg[0] = Sprite.kondoria_left.getFxImage();
        leftImg[1] = Sprite.kondoria_left_1.getFxImage();
        leftImg[2] = Sprite.kondoria_left_2.getFxImage();

        rightImg[0] = Sprite.kondoria_right.getFxImage();
        rightImg[1] = Sprite.kondoria_right_1.getFxImage();
        rightImg[2] = Sprite.kondoria_right_2.getFxImage();
    }

    // Override setMove function so that Kondoria can't be blocked
    @Override
    protected void setMove(String moveDrirection, int moveRx, int moveRy, Image img) {
        this.setDirection(moveDrirection);
        // Check collision with wall-2 brick-2 bomb in bomb marix-2
        if (objId[moveRx][moveRy] != 2 && objId[moveRx][moveRy] != 3) {
            // Set number of steps to completely move to new block
            this.setSteps(Sprite.SCALED_SIZE / this.speed);
            this.setCountToRun(0);
            // Start run
            this.checkRun();
        } else {
            // If can not move just set img
            this.setImg(img);
        }
    }

    // Check can eat bomb
    private void checkEatBomb() {
        // Up
        checkBomb(this.getRx(), this.getRy() - 1);
        // Down
        checkBomb(this.getRx(), this.getRy() + 1);
        // Left
        checkBomb(this.getRx() - 1, this.getRy());
        // Right
        checkBomb(this.getRx() + 1, this.getRy());
    }

    private void checkBomb(int bombRx, int bombRy) {
        if (bombMatix[bombRx][bombRy] == 2) {
            Bomb curBomb = Bomb.getBomb(bombRx, bombRy);
            if (curBomb != null && !curBomb.isExploded() && curBomb.isRaw()
                && !curBomb.isUi()) {
                // Remove bomb from bomb list and bomb matrix
                bombs.remove(curBomb);
                bombMatix[bombRx][bombRy] = 0;
                bomberman.gainBombRemain();
                // Add sound
                new Sound("sound/eat.wav", "eat");
            }
        }
    }

    @Override
    public void update() {
        super.update();
        this.checkEatBomb();
    }
}