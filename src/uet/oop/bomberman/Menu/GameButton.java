package uet.oop.bomberman.Menu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameButton extends Button {
    private final String FONT_PATH = "res/Demo/kenvector_future.ttf";

    public GameButton(String text) {
        setStyle("-fx-background-color: transparent");
        setBackground(getBackground());
        setText(text);
        setButtonFont();
        setPrefWidth(180);
        setPrefHeight(30);
        initialiseButtonListeners();
    }

    private void setButtonFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 20));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 20));
            System.out.println("Font not found or could not be loaded. Using default \"Verdana\"");
        }

    }

    private void setButtonPressedStyle() {
        setStyle("-fx-background-color: transparent");
        setPrefHeight(30);
        setLayoutY(getLayoutY() + 4);

    }

    private void setButtonReleasedStyle() {
        setStyle("-fx-background-color: transparent");
        setPrefHeight(30);
        setLayoutY(getLayoutY() - 4);
    }

    private void initialiseButtonListeners() {
        setOnMousePressed(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                setButtonPressedStyle();
            }
        });

        setOnMouseReleased(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                setButtonReleasedStyle();
            }
        });
            setOnMouseEntered(event -> setEffect(new DropShadow(20, Color.YELLOW)));
        setOnMouseExited(event -> setEffect(null));
    }
}
