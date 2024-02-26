package uet.oop.bomberman.entities.items;

import uet.oop.bomberman.graphics.Sprite;

public class BombLimitItem extends Item {
    public BombLimitItem(int rx, int ry) {
        super(rx, ry, Sprite.powerup_detonator.getFxImage());
    }

    @Override
    public void update() {

    }
}