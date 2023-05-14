import javax.swing.*;
import java.awt.*;
public class FriendlyBear extends Animal {
    //A friendly bear that you should click on because it wants to give you a hug.
    // (Unlike its cousin Greg who has developed explosive tendencies)
    private GameViewer screen;
    private Image friendlybearImage;
    private int x, y;
    public FriendlyBear(GameViewer screen) {
        super(screen);
        friendlybearImage = screen.getImages()[2];
        // Initialize the animals random location
        x = (int) (Math.random() * screen.getWidth() * 0.8);
        y = (int) (Math.random() * screen.getHeight() * 0.8);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public GameViewer getScreen() {
        return screen;
    }

    public void draw(Graphics g) {
        //draws every object currently on the board
        g.drawImage(friendlybearImage, x, y, screen);
    }
}
