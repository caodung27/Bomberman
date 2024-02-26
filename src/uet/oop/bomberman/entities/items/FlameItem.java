package uet.oop.bomberman.entities.items;

import uet.oop.bomberman.graphics.Sprite;

public class FlameItem extends Item {
    public FlameItem(int rx, int ry) {
        super(rx, ry, Sprite.powerup_flames.getFxImage());
    }

    @Override
    public void update() {

    }
}