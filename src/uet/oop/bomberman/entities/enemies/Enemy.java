package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.MovableEntity;
import uet.oop.bomberman.entities.blocks.Bomb;

import static uet.oop.bomberman.BombermanGame.*;

public abstract class Enemy extends MovableEntity {
    // Enemy move time stats
    protected long lastMoveTime;
    protected long delayTime;

    // Move delay time for each kind of enemy
    public static final long DEFAULT_DELAY_TIME = 1000;
    public static final long BALLOM_DELAY_TIME = 1300;
    public static final long ONEAL_DELAY_TIME = 600;
    public static final long MINVO_DELAY_TIME = 600;
    public static final long KONDORIA_DELAY_TIME = 700;

    // Enemy speed stats
    public static final int DEFAULT_ENEMY_SPEED = 8;
    public static final int ENEMY_WAIT_NEXT_STEP = 4;

    public Enemy(int rx, int ry, Image img, boolean isALive, String direction) {
        super(rx, ry, img, isALive, direction);
        this.lastMoveTime = 0;
        this.delayTime = DEFAULT_DELAY_TIME;
        this.speed = DEFAULT_ENEMY_SPEED;
    }

    // Each enemy has different movement
    public abstract void move();

    public void killBomber() {
        if (bomberman.getRx() == this.getRx() && bomberman.getRy() == this.getRy()) {
            // isStopMoving from BomberGame
            isStopMoving = true;
            bomberman.killedByEnemy();
            bomberman.setAlive(false);
        }
    }

    @Override
    public void killedByBomb() {
        for (Bomb bomb : bombs) {
            if (bomb.isFinal()) {
                if (bomb.getRx() == this.getRx() && bomb.getRy() == this.getRy()) {
                    this.setAlive(false);
                }
            }
        }
    }

    @Override
    public void update() {
        this.killedByBomb();
        this.killBomber();

        // Time between 2 steps
        long now = System.currentTimeMillis();
        if (now - this.getLastMoveTime() > this.getDelayTime()) {
            this.setLastMoveTime(now);
            this.move();
        }
    }

    public long getLastMoveTime() {
        return lastMoveTime;
    }

    public void setLastMoveTime(long lastMoveTime) {
        this.lastMoveTime = lastMoveTime;
    }

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = delayTime;
    }
}