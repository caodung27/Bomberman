package uet.oop.bomberman.Menu;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.*;

public class Menu {
    private static final int HEIGHT = SCREEN_HEIGHT * 32 + 74;
    private static final int WIDTH = SCREEN_WIDTH * 32 + 15;
    private static final double MENU_BUTTON_START_X = 50;
    private static final double MENU_BUTTON_START_Y = 270;

    AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;
    private GameSubScene helpSubScene;

    public List<GameButton> menuButtons;
    public List<GameButton> helpButtonList;

    public static Text level, bomb, time;
    // limit is 120 seconds
    public static Image pauseGame, playGame;

    public Menu() {
        menuButtons = new ArrayList<>();
        helpButtonList = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createSubScenes();
        createButtons();
        createBackground();
        createLogo();
    }

    private void showSubScene(GameSubScene subScene) {
        if (subScene != null) {
            subScene.moveSubScene();
        }
    }

    private void createSubScenes() {
        helpSubScene = new GameSubScene();
        createHelpSubScene();
    }

    private void createHelpSubScene() {
        helpSubScene = new GameSubScene();
        mainPane.getChildren().add(helpSubScene);
        GridPane helpGrid = new GridPane();
        helpGrid.setLayoutX(1);
        helpGrid.setLayoutY(1);
        helpGrid.setHgap(1);
        helpGrid.setVgap(1);

        GameButton upButton = new GameButton("W");
        addHelpButtons(upButton);
        GameButton downButton = new GameButton("S");
        addHelpButtons(downButton);
        GameButton leftButton = new GameButton("A");
        addHelpButtons(leftButton);
        GameButton rightButton = new GameButton("D");
        addHelpButtons(rightButton);
        GameButton placeButton = new GameButton("SPACE");
        addHelpButtons(placeButton);

        helpGrid.addRow(0, new InfoLabel("UP"), upButton);
        helpGrid.addRow(1, new InfoLabel("DOWN"), downButton);
        helpGrid.addRow(2, new InfoLabel("LEFT"), leftButton);
        helpGrid.addRow(3, new InfoLabel("RIGHT"), rightButton);
        helpGrid.addRow(4, new InfoLabel("PLACE BOMB"), placeButton);

        helpSubScene.getPane().getChildren().add(helpGrid);
    }

    private void addHelpButtons(GameButton button) {
        helpButtonList.add(button);
        button.setLayoutX(1);
        button.setLayoutY(helpButtonList.size() * 2);
        helpSubScene.getPane().getChildren().add(button);
    }

    private void createButtons() {
        createStartButton();
        createHelpButton();
    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void addMenuButtons(GameButton button) {
        button.setLayoutX(MENU_BUTTON_START_X);
        button.setLayoutY(MENU_BUTTON_START_Y + menuButtons.size() * 90);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    private void createStartButton() {
        GameButton startButton = new GameButton("New Game");
        addMenuButtons(startButton);
        startButton.setOnAction(event -> {
            mainStage.hide();
            GameMenu gameViewManager = new GameMenu();
            gameViewManager.createNewGame(mainStage);
        });
    }

    private void createHelpButton() {
        GameButton helpButton = new GameButton("Help");
        addMenuButtons(helpButton);
        helpButton.setOnAction(event -> showSubScene(helpSubScene));
    }

    private void createBackground() {
        Image bgImage = new Image(getClass().getResourceAsStream("/Demo/DemoBackground.gif"),
                256, 256, false, true);
        BackgroundImage bg = new BackgroundImage(bgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, new BackgroundSize(WIDTH, HEIGHT, false, false, false, false));
        mainPane.setBackground(new Background(bg));
    }

    private void createLogo() {
        Image logoImage = new Image(getClass().getResourceAsStream("/Demo/logo3.png"),
                320, 100, false, false);
        ImageView logo = new ImageView(logoImage);
        logo.setLayoutX(10);
        logo.setLayoutY(100);
        logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                logo.setEffect(new DropShadow(100, Color.YELLOW));
            }
        });
        logo.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                logo.setEffect(null);
            }
        });
        mainPane.getChildren().add(logo);
    }
}