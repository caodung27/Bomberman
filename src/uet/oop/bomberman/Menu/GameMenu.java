package uet.oop.bomberman.Menu;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.LevelMap.Level1;
import uet.oop.bomberman.LevelMap.Level2;
import uet.oop.bomberman.LevelMap.Level3;
import uet.oop.bomberman.Sound.Sound;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.blocks.Bomb;
import uet.oop.bomberman.entities.bomberman.Bomber;
import uet.oop.bomberman.entities.enemies.Enemy;
import uet.oop.bomberman.entities.items.Item;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.Menu.Menu.*;

public class GameMenu {
  private GraphicsContext gc;
  private Canvas canvas;

  // For time counter
  private long lastSecond;
  private static AnchorPane gamePane;
  private static Scene gameScene;
  private static Stage gameStage;
  private static Stage menuStage;
  // camera smooth translate
  private static final TranslateTransition camera = new TranslateTransition();
  public static ImageView statusGame;
  private static final int GAME_WIDTH = 600;
  private static final int GAME_HEIGHT = 700;
  Random randomPosGen;

  public GameMenu() {
    randomPosGen = new Random();
    initializeStage();
    createKeysListeners();
  }

  // Keyboard Event
  private void createKeysListeners() {
    gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent keyEvent) {
        if (!isStopMoving && !isPause)
          bomberman.handleEventPress(keyEvent);
      }
    });
  }

  private void initializeStage() {
    gamePane = new AnchorPane();
    gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
    gameStage = new Stage();
    // Create canvas
    canvas = new Canvas(Sprite.SCALED_SIZE * LEVEL_WIDTH, Sprite.SCALED_SIZE * SCREEN_HEIGHT);
    canvas.setTranslateY(36);
    gc = canvas.getGraphicsContext2D();
    camera.setDuration(Duration.millis(200));
    camera.setNode(canvas);
    Image author = new Image("images/background.jpg");
    author_view = new ImageView(author);
    author_view.setX(-400);
    author_view.setY(-208);
    author_view.setScaleX(0.5);
    author_view.setScaleY(0.5);
    createMenu();
    gamePane.getChildren().add(canvas);
    gamePane.getChildren().add(author_view);

    // add scene to stage
    gameStage.setTitle("Bomberman Game");
    gameStage.setScene(gameScene);
    gameStage.setResizable(false);
    gameStage.setWidth(SCREEN_WIDTH * 32 + 15);
    gameStage.setHeight(SCREEN_HEIGHT * 32 + 74);
    gameStage.setOnCloseRequest(x -> {
      x.consume();
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Logout");
      alert.setHeaderText("You're about to logout!");
      alert.setContentText("Do you want to save before exiting?");

      if (alert.showAndWait().get() == ButtonType.OK) {
        System.out.println("You successfully logged out");
        Platform.exit();
      }
    });
  }

  public static void createMenu() { // Create a menu
    level = new Text("Level");
    level.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    level.setFill(Color.WHITE);
    level.setX(416);
    level.setY(20);
    bomb = new Text("Bombs");
    bomb.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    bomb.setFill(Color.WHITE);
    bomb.setX(512);
    bomb.setY(20);
    time = new Text("Times");
    time.setFont(Font.font("Arial", FontWeight.BOLD, 14));
    time.setFill(Color.WHITE);
    time.setX(608);
    time.setY(20);

    Image newGame = new Image("images/start.png");
    statusGame = new ImageView(newGame);
    statusGame.setX(-75);
    statusGame.setY(-10);
    statusGame.setScaleX(0.5);
    statusGame.setScaleY(0.5);

    Pane pane = new Pane();
    pane.getChildren().addAll(level, bomb, time, statusGame);
    pane.setMinSize(800, 32);
    pane.setMaxSize(800, 480);
    pane.setStyle("-fx-background-color: #353535");

    gamePane.getChildren().add(pane);

    playGame = new Image("images/pause.png");
    pauseGame = new Image("images/resume.png");

    statusGame.setOnMouseClicked(event -> {
      if (bomberman.isAlive()) {
        isPause = !isPause;
      } else {
        if (BombermanGame.currentLevel == 1) {
          new Level1();
          isOver = false;
        } else if (BombermanGame.currentLevel == 2) {
          new Level2();
          isOver = false;
        } else if (BombermanGame.currentLevel == 3) {
          if (end == false) {
            new Level3();
            isOver = false;
          } else {
            new Level1();
            isOver = false;
            end = false;
          }

        }
      }
      updateMenu();
    });

  }

  public static void updateMenu() { // Update menu
    if (bomberman.isAlive())
      if (isPause) {
        statusGame.setImage(pauseGame);
      } else {
        statusGame.setImage(playGame);
      }
    else {
      Image newGame = new Image("images/start.png");
      statusGame.setImage(newGame);
    }
  }

  public void createNewGame(Stage menuStage) {
    GameMenu.menuStage = menuStage;
    GameMenu.menuStage.hide();
    createGameLoop();
    gameStage.show();
  }

  private void createGameLoop() {
    AnimationTimer timer = new AnimationTimer() {
      @Override
      public void handle(long l) {
        render();
        if (!isPause) {
          if (!isOver) {
            // Update all game obj
            update();
            // Time counter, each level has different time limit
            time();
            // Update menu stats
            updateMenu();
            // Move camera
            camera.play();
          } else {
            Image gameOver = new Image("images/over.png");
            author_view.setImage(gameOver);
          }
        }
      }
    };
    timer.start();
    bomberman.setAlive(false);
    lastSecond = System.currentTimeMillis();
  }

  // stage update
  public void update() {
    // entities like: brick, wall,...
    entities.forEach(Entity::update);

    bomberman.update();
    // Count time between 2 steps
    bomberman.setCountToRun(bomberman.getCountToRun() + 1);
    if (bomberman.getCountToRun() == Bomber.BOMBER_WAIT_NEXT_STEP) {
      bomberman.checkRun();
      bomberman.setCountToRun(0);
    }

    enemies.forEach(Enemy::update);
    for (Enemy enemy : enemies) {
      // Count time between 2 steps
      enemy.setCountToRun(enemy.getCountToRun() + 1);
      if (enemy.getCountToRun() == Enemy.ENEMY_WAIT_NEXT_STEP) {
        enemy.checkRun();
        enemy.setCountToRun(0);
      }
    }
    enemies.removeIf(enemy -> !enemy.isAlive());

    // Update bomb list
    for (int i = 0; i < bombs.size(); i++) {
      if (bombs.get(i) != null) {
        bombs.get(i).update();
      }
    }
    bombs.removeIf(Bomb::isExploded);

    items.forEach(Item::update);

    portal1.update();
    portal2.update();
    portal3.update();
    Sound.updateSound();
  }

  public void time() {
    long now = System.currentTimeMillis();
    if (now - lastSecond > 1000) {
      lastSecond = now;

      if (bomberman.isAlive() && time_number > 0) {
        time_number--;
        Menu.time.setText("Time " + time_number);
        // If out of time, game over
        if (time_number <= 0) {
          bomberman.setAlive(false);
          isOver = true;
        }
      }
    }
  }

  // Handle camera
  public void moveCamera() {
    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    int cameraX = bomberman.getX() - (SCREEN_WIDTH_PIXELS - Sprite.SCALED_SIZE) / 2;
    // Check if camera move through game border
    if (cameraX < 0)
      cameraX = 0;
    if (cameraX + SCREEN_WIDTH_PIXELS > LEVEL_WIDTH_PIXELS)
      cameraX = SCREEN_WIDTH_PIXELS;
    camera.setToX(-cameraX);
  }

  // object render
  public void render() {
    moveCamera();
    stillObjects.forEach(g -> g.render(gc));
    entities.forEach(g -> g.render(gc));
    items.forEach(item -> item.render(gc));
    bombs.forEach(bomb -> bomb.render(gc));
    bomberman.render(gc);
    enemies.forEach(enemy -> enemy.render(gc));
  }
}
