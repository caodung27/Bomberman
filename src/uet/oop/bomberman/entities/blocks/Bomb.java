package uet.oop.bomberman.entities.blocks;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.items.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.Sound.Sound;

import static uet.oop.bomberman.BombermanGame.*;

public class Bomb extends Entity {
    // bomb stats
    private static final long bombLastTime = 2200; // For middle bomb
    private static final long uiLastTime = 480; // For bomb in 4 direction
    private static final int MAX_BOMB_LEVEL = 2;
    private long bombStartTime;
    private boolean isExploded;
    private boolean isFinal;
    private boolean isRaw; // Check if is middle bomb
    private boolean ui; // Check if is bomb in 4 direction
    // For animation
    private int animationTransform = 1;
    private int countTransform = 0;
    public static int curBombLevel = 1; // Current level of bomb power
    // Bomb images for 4 direction
    private final Bomb[] left = new Bomb[MAX_BOMB_LEVEL];
    private final Bomb[] right = new Bomb[MAX_BOMB_LEVEL];
    private final Bomb[] up = new Bomb[MAX_BOMB_LEVEL];
    private final Bomb[] down = new Bomb[MAX_BOMB_LEVEL];

    public Bomb(int rx, int ry, Image img, boolean isExploded, boolean ui) {
        super(rx, ry, img);
        this.isExploded = isExploded;
        this.ui = ui;
        this.bombStartTime = System.currentTimeMillis();
        this.isFinal = false;
        this.isRaw = true;
        // If is middle bomb mark 2 in bomb matrix, otherwise mark as 3
        bombMatix[this.rx][this.ry] = 2;
    }

    public void explosion() {
        setRaw(false);
        if (countTransform % 10 == 0) {
            if (animationTransform == 1) {
                this.img = Sprite.bomb_exploded.getFxImage();
                animationTransform = 2;
                explosionPhase1();
            } else if (animationTransform == 2) {
                this.img = Sprite.bomb_exploded1.getFxImage();
                animationTransform = 3;
                explosionPhase2();
            } else if (animationTransform == 3) {
                this.img = Sprite.bomb_exploded2.getFxImage();
                this.isFinal = true;
                animationTransform = 4;
                explosionPhase3();
            } else {
                bombMatix[this.rx][this.ry] = 0;
                this.isExploded = true;
                bomberman.gainBombRemain();
            }
        }
    }

    @Override
    public void update() {
        if (!ui) {
            if (System.currentTimeMillis() - this.bombStartTime > bombLastTime) {
                countTransform++;
                explosion();
            }
        } else {
            if (System.currentTimeMillis() - this.bombStartTime > uiLastTime) {
                bombMatix[this.rx][this.ry] = 0;
                isExploded = true;
            }
        }
    }

    // Check bomb destroy brick-3
    private void checkImpact() {
        if (this.isFinal && this.ui && objId[this.rx][this.ry] == 3) {
            for (int i = 0; i < stillObjects.size(); i++) {
                Entity block = stillObjects.get(i);
                if (block.getX() / Sprite.SCALED_SIZE == this.rx
                    && block.getY() / Sprite.SCALED_SIZE == this.ry) {
                    // Note: 4 5 6 7 8 are Speed, Flame, Bomb item, Bomb limit item, Time limit item respectively
                    switch (itemMatrix[this.rx][this.ry]) {
                        case 4:
                            items.add(new SpeedItem(this.rx, this.ry));
                            break;
                        case 5:
                            items.add(new FlameItem(this.rx, this.ry));
                            break;
                        case 6:
                            items.add(new BombItem(this.rx, this.ry));
                            break;
                        case 7:
                            items.add(new BombLimitItem(this.rx, this.ry));
                            break;
                        case 8:
                            items.add(new TimeLimitItem(this.rx, this.ry));
                            break;
                    }
                    if (BombermanGame.currentLevel == 1) {
                        stillObjects.set(i, new Grass(this.rx, this.ry, Sprite.grass2.getFxImage()));
                    } else if (BombermanGame.currentLevel == 2) {
                        stillObjects.set(i, new Grass(this.rx, this.ry, Sprite.grass3.getFxImage()));
                    } else {
                        stillObjects.set(i, new Grass(this.rx, this.ry, Sprite.grass4.getFxImage()));
                    }
                    objId[this.rx][this.ry] = itemMatrix[this.rx][this.ry];
                }
            }
        }
    }

    private void explosionPhase1() {
        // Left
        explosionPhase1("left", this.left,
                Sprite.explosion_horizontal.getFxImage(),
                Sprite.explosion_horizontal_left_last.getFxImage());
        // Right
        explosionPhase1("right", this.right,
                Sprite.explosion_horizontal.getFxImage(),
                Sprite.explosion_horizontal_right_last.getFxImage());
        // Up
        explosionPhase1("up", this.up,
                Sprite.explosion_vertical.getFxImage(),
                Sprite.explosion_vertical_top_last.getFxImage());
        // Down
        explosionPhase1("down", this.down,
                Sprite.explosion_vertical.getFxImage(),
                Sprite.explosion_vertical_down_last.getFxImage());
    }

    private void explosionPhase2() {
        // Up
        explosionPhase2(this.up,
                Sprite.explosion_vertical1.getFxImage(),
                Sprite.explosion_vertical_top_last1.getFxImage());
        // Down
        explosionPhase2(this.down,
                Sprite.explosion_vertical1.getFxImage(),
                Sprite.explosion_vertical_down_last1.getFxImage());
        // Left
        explosionPhase2(this.left,
                Sprite.explosion_horizontal1.getFxImage(),
                Sprite.explosion_horizontal_left_last1.getFxImage());
        // Right
        explosionPhase2(this.right,
                Sprite.explosion_horizontal1.getFxImage(),
                Sprite.explosion_horizontal_right_last1.getFxImage());

        new Sound("sound/bomb_explosion.wav", "explosion");
    }

    private void explosionPhase3() {
        // Left
        explosionPhase3(this.left,
                Sprite.explosion_horizontal2.getFxImage(),
                Sprite.explosion_horizontal_left_last2.getFxImage());
        // Right
        explosionPhase3(this.right,
                Sprite.explosion_horizontal2.getFxImage(),
                Sprite.explosion_horizontal_right_last2.getFxImage());
        // Up
        explosionPhase3(this.up,
                Sprite.explosion_vertical2.getFxImage(),
                Sprite.explosion_vertical_top_last2.getFxImage());
        // Down
        explosionPhase3(this.down,
                Sprite.explosion_vertical2.getFxImage(),
                Sprite.explosion_vertical_down_last2.getFxImage());
    }

    private void explosionPhase1(String direction, Bomb[] bombArr, Image first, Image last) {
        for (int i = 1; i <= Bomb.curBombLevel; i++) {
            int posX = 0;
            int posY = 0;
            switch (direction) {
                case "up":
                    posY = -i;
                    break;
                case "down":
                    posY = i;
                    break;
                case "left":
                    posX = -i;
                    break;
                case "right":
                    posX = i;
                    break;
            }
            if (objId[rx + posX][ry + posY] == 2)
                break;
            if (objId[rx + posX][ry + posY] != 2) {
                if (curBombLevel > 1) {
                    if (i == 1) {
                        bombArr[0] =
                                new Bomb(rx + posX, ry + posY, first, false, true);
                        bombs.add(bombArr[0]);
                    } else {
                        bombArr[i - 1] = new Bomb(rx + posX, ry + posY,
                                last, false, true);
                        bombs.add(bombArr[i - 1]);
                    }
                } else {
                    bombArr[i - 1] =
                            new Bomb(rx + posX, ry + posY, last, false, true);
                    bombs.add(bombArr[i - 1]);
                }
            }
            if (objId[rx + posX][ry + posY] == 3)
                break;
        }
    }

    private void explosionPhase2(Bomb[] bombArr, Image first, Image last) {
        for (int i = 1; i <= Bomb.curBombLevel; i++) {
            if (bombArr[i - 1] != null) {
                if (curBombLevel > 1) {
                    if (i != 1) {
                        bombArr[i - 1].img = last;
                    } else {
                        bombArr[i - 1].img = first;
                    }
                } else {
                    bombArr[i - 1].img = last;
                }
            } else
                break;
        }
    }

    private void explosionPhase3(Bomb[] bombArr, Image first, Image last) {
        for (int i = 1; i <= Bomb.curBombLevel; i++) {
            if (bombArr[i - 1] != null) {
                if (curBombLevel > 1) {
                    if (i != 1) {
                        bombArr[i - 1].img = last;
                    } else {
                        bombArr[i - 1].img = first;
                    }
                } else {
                    bombArr[i - 1].img = last;
                }
                bombArr[i - 1].isFinal = true;
                bombMatix[bombArr[i - 1].rx][bombArr[i - 1].ry] = 1;
                bombArr[i - 1].checkImpact();
            } else
                break;
        }
    }

    public static Bomb getBomb(int bombRx, int bombRy) {
        for (Bomb bomb : bombs) {
            if (bomb.getRx() == bombRx && bomb.getRy() == bombRy) {
                return bomb;
            }
        }
        return null;
    }

    public long getBombStartTime() {
        return bombStartTime;
    }

    public void setBombStartTime(long bombStartTime) {
        this.bombStartTime = bombStartTime;
    }

    public boolean isExploded() {
        return isExploded;
    }

    public void setExploded(boolean exploded) {
        isExploded = exploded;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public boolean isRaw() {
        return isRaw;
    }

    public void setRaw(boolean isRaw) {
        this.isRaw = isRaw;
    }

    public boolean isUi() {
        return ui;
    }

    public void setUi(boolean ui) {
        this.ui = ui;
    }

    public static int getCurBombLevel() {
        return curBombLevel;
    }

    public static void setCurBombLevel(int curBombLevel) {
        Bomb.curBombLevel = curBombLevel;
    }

    public static void gainBombLevel() {
        if (curBombLevel < MAX_BOMB_LEVEL) {
            curBombLevel++;
        }
    }

    public static void lowerBombLevel() {
        if (curBombLevel > 0) {
            curBombLevel--;
        }
    }
}