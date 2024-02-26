package uet.oop.bomberman.entities.items;

import uet.oop.bomberman.graphics.Sprite;

public class TimeLimitItem extends Item {
    public TimeLimitItem(int rx, int ry) {
        super(rx, ry, Sprite.powerup_unknown.getFxImage());
    }

    @Override
    public void update() {

    }
}