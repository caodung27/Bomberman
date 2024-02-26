package uet.oop.bomberman.Menu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class InfoLabel extends Label {
    public final static String FONT_PATH = "res/Demo/kenvector_future.ttf";

    public InfoLabel(String text) {
        setPrefWidth(290);
        setPrefHeight(10);
        setPadding(new Insets(25, 30, 30, 40));
        setText(text);
        setWrapText(true);
        setLabelFont();
    }

    private void setLabelFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 25));
        } catch (FileNotFoundException e) {
            System.out.println("Could not load font. Using defaults...");
            setFont(Font.font("Verdana", 25));
        }
    }
}
