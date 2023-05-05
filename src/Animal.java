import javax.swing.*;
import java.awt.*;
public class Animal {
    private GameViewer screen;
    private Image moleimage;
    private int x, y;
    private int windowWidth;
    private int windowHeight;
    private static final int MAX_SPEED = 8;
    public Animal(GameViewer screen) {
        // Initialize the tank values
        this.screen = screen;
        this.windowWidth = screen.getWidth();
        this.windowHeight = screen.getHeight();
        moleimage = screen.getImages()[0];
        // Initialize the animals random location
        this.x = (int) (Math.random() * windowWidth * 0.8);
        this.y = (int) (Math.random() * windowHeight * 0.8);
        //Instiatiate protocal making it so that it cant go to the same place twice? Or make it feature?
    }

    public void doyourthing()
    {
//        //until clicked, just stay put
//        while (!isClicked)
//        {
//            draw()
//        }
    }

    public void draw(Graphics g) {
        //draws every object currently on the board
            g.drawImage(moleimage, x, y, screen);
    }
}