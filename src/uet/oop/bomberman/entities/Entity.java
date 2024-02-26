package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    // X cordinate from left to right of canvas
    protected int x;
    // Y cordinate from top to down of canvas
    protected int y;
    // X cordinate in matrix
    protected int rx;
    // Y cordinate in matrix
    protected int ry;
    // Entity's image
    protected Image img;

    public Entity(int rx, int ry, Image img) {
        // Convert x,y in matrix to x,y in canvas
        this.x = rx * Sprite.SCALED_SIZE;
        this.y = ry * Sprite.SCALED_SIZE;
        this.rx = rx;
        this.ry = ry;
        this.img = img;
    }

    public Entity() {
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update();

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRx() {
        return rx;
    }

    public void setRx(int rx) {
        this.rx = rx;
    }

    public int getRy() {
        return ry;
    }

    public void setRy(int ry) {
        this.ry = ry;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
}