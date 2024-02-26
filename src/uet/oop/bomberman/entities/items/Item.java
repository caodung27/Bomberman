package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class Item extends Entity {
    public Item(int rx, int ry, Image img) {
        super(rx, ry, img);
    }

    public Item() {

    }
}