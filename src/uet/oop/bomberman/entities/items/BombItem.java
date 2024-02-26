package uet.oop.bomberman.entities.items;

import uet.oop.bomberman.graphics.Sprite;

public class BombItem extends Item {
    public BombItem(int rx, int ry) {
        super(rx, ry, Sprite.powerup_bombs.getFxImage());
    }

    @Override
    public void update() {

    }
}