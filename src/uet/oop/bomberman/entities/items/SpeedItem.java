package uet.oop.bomberman.entities.items;

import uet.oop.bomberman.graphics.Sprite;

public class SpeedItem extends Item {
    public SpeedItem(int rx, int ry) {
        super(rx, ry, Sprite.powerup_speed.getFxImage());
    }

    @Override
    public void update() {

    }
}