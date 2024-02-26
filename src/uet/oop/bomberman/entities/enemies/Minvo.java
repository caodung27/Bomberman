package uet.oop.bomberman.entities.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.enemies.pathFinding.AStar;
import uet.oop.bomberman.entities.enemies.pathFinding.Node;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;

import java.util.*;

public class Minvo extends Enemy {
    public Minvo(int rx, int ry, Image img, boolean isALive, String direction) {
        super(rx, ry, img, isALive, direction);
        this.delayTime = MINVO_DELAY_TIME;
        // Set img
        upImg[0] = Sprite.minvo_left.getFxImage();
        upImg[1] = Sprite.minvo_left_1.getFxImage();
        upImg[2] = Sprite.minvo_left_2.getFxImage();

        downImg[0] = Sprite.minvo_right.getFxImage();
        downImg[1] = Sprite.minvo_right_1.getFxImage();
        downImg[2] = Sprite.minvo_right_2.getFxImage();

        leftImg[0] = Sprite.minvo_left.getFxImage();
        leftImg[1] = Sprite.minvo_left_1.getFxImage();
        leftImg[2] = Sprite.minvo_left_2.getFxImage();

        rightImg[0] = Sprite.minvo_right.getFxImage();
        rightImg[1] = Sprite.minvo_right_1.getFxImage();
        rightImg[2] = Sprite.minvo_right_2.getFxImage();
    }

    // Minvo move base on a* path finding algorithms which implemented in pathFinding package
    @Override
    public void move() {
        if (isAvailToTakeNewSteps()) {
            // Path finding A*
            Node initial_node = new Node(this.ry, this.rx);
            Node final_node = new Node(bomberman.getRy(), bomberman.getRx());

            int rows = SCREEN_HEIGHT;
            int cols = LEVEL_WIDTH;

            AStar a_star = new AStar(rows, cols, initial_node, final_node);

            int[][] blocks_in_array = new int[LEVEL_WIDTH * SCREEN_HEIGHT][2];
            int count_block = 0;

            for (int i = 0; i < SCREEN_HEIGHT; i++) {
                for (int j = 0; j < LEVEL_WIDTH; j++) {
                    if (objId[j][i] != 0) {
                        blocks_in_array[count_block][0] = i;
                        blocks_in_array[count_block][1] = j;
                        count_block++;
                    }
                }
            }
            a_star.setBlocks(blocks_in_array, count_block);
            List<Node> path = a_star.findPath();
            if (path.size() != 0) {
                int nextX = path.get(1).getCol();
                int nextY = path.get(1).getRow();
                if (this.ry > nextY) {
                    this.setMove("up");
                } else if (this.ry < nextY) {
                    this.setMove("down");
                } else if (this.rx > nextX) {
                    this.setMove("left");
                } else if (this.rx < nextX) {
                    this.setMove("right");
                }
            }
        }
    }
}