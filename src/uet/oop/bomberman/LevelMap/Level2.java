package uet.oop.bomberman.LevelMap;

import javafx.scene.image.Image;

import static uet.oop.bomberman.BombermanGame.*;

import uet.oop.bomberman.graphics.Map;
import uet.oop.bomberman.entities.enemies.*;
import uet.oop.bomberman.graphics.Sprite;


public class Level2 {
    public Level2() {
        // clear level
        LevelUp.clearLevel();
        new Map("res/levels/Level2.txt");

        LevelUp.setBombAndTime(40, 200);
        // set and add enemies
        Enemy ballom0 = new Ballom(5, 4, Sprite.balloom_left_2.getFxImage(), true, "left");
        Enemy ballom1 = new Ballom(8, 8, Sprite.balloom_left_2.getFxImage(), true, "left");
        Enemy oneal1 = new Oneal(7, 7, Sprite.oneal_left_2.getFxImage(), true, "left");
        Enemy kondo2 = new Kondoria(31, 4, Sprite.kondoria_left_2.getFxImage(), true, "left");
        Enemy kondo3 = new Kondoria(39, 4, Sprite.kondoria_left_2.getFxImage(), true, "left");
        Enemy ballom2 = new Ballom(27, 8, Sprite.balloom_left_2.getFxImage(), true, "left");
        Enemy ballom3 = new Ballom(46, 8, Sprite.balloom_left_2.getFxImage(), true, "left");

        enemies.add(ballom0);
        enemies.add(oneal1);
        enemies.add(kondo2);
        enemies.add(kondo3);
        enemies.add(ballom1);
        enemies.add(ballom2);
        enemies.add(ballom3);

        // load authorView Scr
        Image transparent = new Image("images/transparent.png");
        author_view.setImage(transparent);
    }
}
