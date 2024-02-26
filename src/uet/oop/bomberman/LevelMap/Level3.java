package uet.oop.bomberman.LevelMap;

import javafx.scene.image.Image;

import static uet.oop.bomberman.BombermanGame.*;

import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.entities.enemies.*;
import uet.oop.bomberman.graphics.Sprite;

public class Level3 {
    public Level3() {
        // clear level
        LevelUp.clearLevel();
        new Map("res/levels/Level3.txt");

        // set bomb and time limit
        LevelUp.setBombAndTime(50, 180);

        // set and add enemies
        Enemy ballom1 = new Ballom(28, 5, Sprite.balloom_left.getFxImage(), true, "left");
        Enemy ballom2 = new Ballom(30, 7, Sprite.balloom_left.getFxImage(), true, "left");
        Enemy ballom3 = new Ballom(10, 7, Sprite.balloom_left.getFxImage(), true, "left");
        Enemy kondo1 = new Kondoria(7, 7, Sprite.kondoria_left.getFxImage(), true, "left");
        Enemy minvo1 = new Minvo(1, 5, Sprite.minvo_left.getFxImage(), true, "left");
        Enemy minvo2 = new Minvo(4, 6, Sprite.minvo_left.getFxImage(), true, "left");
        Enemy oneal1 = new Oneal(29, 11, Sprite.oneal_left.getFxImage(), true, "left");
        Enemy oneal2 = new Oneal(32, 13, Sprite.oneal_left.getFxImage(), true, "left");

        enemies.add(ballom1);
        enemies.add(ballom2);
        enemies.add(ballom3);
        enemies.add(kondo1);
        enemies.add(minvo1);
        enemies.add(minvo2);
        enemies.add(oneal1);
        enemies.add(oneal2);

        // load authorView Scr
        Image transparent = new Image("images/transparent.png");
        author_view.setImage(transparent);
    }
}
