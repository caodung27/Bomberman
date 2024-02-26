package uet.oop.bomberman.graphics;

// Import class

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.blocks.Brick;
import uet.oop.bomberman.entities.blocks.Portal;
import uet.oop.bomberman.entities.blocks.Wall;
import uet.oop.bomberman.entities.blocks.Grass;
import uet.oop.bomberman.BombermanGame;

import static uet.oop.bomberman.BombermanGame.*;

// Import library
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Map {
    // Constructor MapCreation with parameter "level" in string data type.
    public Map(String level) {
        System.out.println(System.getProperty("user.dir"));
        final File fileName = new File(level);
        try (FileReader inputFile = new FileReader(fileName)) {
            Scanner ip = new Scanner(inputFile); // Create object ip from class Scanner.
            String line = ip.nextLine(); // Input variable line in string data type.

            StringTokenizer tokens = new StringTokenizer(line); // Create object tokens from class
            // StringTokenizer in library imported.

            // parseInt(): Method that parses the string argument and returns a primitive int.
            BombermanGame.currentLevel = Integer.parseInt(tokens.nextToken()); // To refer to variable
            // level in
            // main file.

            while (ip.hasNextLine()) {
                for (int i = 0; i < LEVEL_HEIGHT; i++) {
                    String lineTile = ip.nextLine(); // Input variable lineTile in string data type.
                    StringTokenizer tokenTile = new StringTokenizer(lineTile); // Create object tokenTile from
                    // class StringTokenizer in
                    // library imported.

                    for (int j = 0; j < LEVEL_WIDTH; j++) {
                        int token = Integer.parseInt(tokenTile.nextToken());
                        Entity entity; // Create object entity from class Entity.

                        // This switch statement running, and we got a full map for a game.
                        // Through the program, in the for-loop statement, we can get the map according to each
                        // loop it passed.
                        switch (token) {
                            case 1: // 1 is Portal, set Portal image base on level
                                if (BombermanGame.currentLevel == 1) {
                                    entity = new Portal(j, i, Sprite.grass2.getFxImage());
                                    stillObjects.add(entity);
                                } else if (BombermanGame.currentLevel == 2) {
                                    entity = new Portal(j, i, Sprite.grass3.getFxImage());
                                    stillObjects.add(entity);
                                } else {
                                    entity = new Portal(j, i, Sprite.grass4.getFxImage());
                                    stillObjects.add(entity);
                                }
                                token = 0;
                                break;
                            case 2: // 2 is Wall, set Portal image base on level
                                if (BombermanGame.currentLevel == 1) {
                                    entity = new Wall(j, i, Sprite.wall2.getFxImage());
                                    stillObjects.add(entity);

                                } else if (BombermanGame.currentLevel == 2) {
                                    entity = new Wall(j, i, Sprite.wall3.getFxImage());
                                    stillObjects.add(entity);

                                } else {
                                    entity = new Wall(j, i, Sprite.wall4.getFxImage());
                                    stillObjects.add(entity);
                                }
                                break;
                            case 3: // 3 is Brick, set Portal image base on level
                                if (BombermanGame.currentLevel == 1) {
                                    entity = new Brick(j, i, Sprite.brick2.getFxImage());
                                    stillObjects.add(entity);

                                } else if (BombermanGame.currentLevel == 2) {
                                    entity = new Brick(j, i, Sprite.brick3.getFxImage());
                                    stillObjects.add(entity);
                                } else {
                                    entity = new Brick(j, i, Sprite.brick4.getFxImage());
                                    stillObjects.add(entity);
                                }
                                break;
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                                // 4 5 6 7 8 are Speed, Flame, Bomb item, Bomb limit item, Time limit item respectively
                                // Load brick instead of item at game start
                                // When brick is destroy, show item (checkImpact function in Bomb.java)
                                itemMatrix[j][i] = token;
                                token = 3; // 3 is Brick
                                if (BombermanGame.currentLevel == 1) {
                                    entity = new Brick(j, i, Sprite.brick2.getFxImage());
                                    stillObjects.add(entity);

                                } else if (BombermanGame.currentLevel == 2) {
                                    entity = new Brick(j, i, Sprite.brick3.getFxImage());
                                    stillObjects.add(entity);
                                } else {
                                    entity = new Brick(j, i, Sprite.brick4.getFxImage());
                                    stillObjects.add(entity);
                                }
                                break;
                            default: // 0 is Grass
                                if (BombermanGame.currentLevel == 1) {
                                    entity = new Grass(j, i, Sprite.grass2.getFxImage());
                                    stillObjects.add(entity);
                                } else if (BombermanGame.currentLevel == 2) {
                                    entity = new Grass(j, i, Sprite.grass3.getFxImage());
                                    stillObjects.add(entity);
                                } else {
                                    entity = new Grass(j, i, Sprite.grass4.getFxImage());
                                    stillObjects.add(entity);
                                }

                        }
                        // Set obj matrix
                        objId[j][i] = token;
                    }
                }
            }
            ip.close();
        } catch (IOException e) { // Catch exception
            System.out.println("rendering error!!!");
            e.printStackTrace(); // printStackTrace(): Help to understand where the problem is actually
            // happening.
        }

    }
}