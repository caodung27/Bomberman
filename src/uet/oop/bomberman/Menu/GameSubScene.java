package uet.oop.bomberman.Menu;

import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

public class GameSubScene extends SubScene {
    private final static String BG_IMAGE = "/Demo/yellow_panel.png";
    private boolean isHidden = true;

    public GameSubScene() {
        super(new AnchorPane(), 500, 460);
        prefWidth(500);
        prefHeight(460);
        BackgroundImage image = new BackgroundImage(new Image(BG_IMAGE, 500, 460, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        AnchorPane root2 = (AnchorPane) this.getRoot();
        root2.setBackground(new Background(image));

        setLayoutX(815);
        setLayoutY(60);
    }

    public void moveSubScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.5));
        transition.setNode(this);
        if(isHidden) {
            transition.setToX(-510);
            isHidden = false;
        } else {
            transition.setToX(0);
            isHidden = true;
        }
        transition.play();
    }

    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }
}
