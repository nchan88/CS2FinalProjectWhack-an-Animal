import javax.swing.*;
import java.awt.*;
public class Animal {
    private GameViewer screen;
    private Image moleimage;
    private int x, y;
    private int windowWidth;
    private int windowHeight;
    public Animal(GameViewer screen) {
        this.screen = screen;
        this.windowWidth = screen.getWidth();
        this.windowHeight = screen.getHeight();
        moleimage = screen.getImages()[0];
        // Initialize the animals random location
        this.x = (int) (Math.random() * windowWidth * 0.8);
        this.y = (int) (Math.random() * windowHeight * 0.8);
        //Instiatiate protocal making it so that it cant go to the same place twice? Or make it feature?
        //Im leaning to making it a feature
    }

    public void doyourthing()
    {
//        if (clicked == true)
        {
            //delete the object not just the image
            //add to score
        }
        System.out.println("Animal");
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
        g.drawImage(moleimage, x, y, screen);
    }
}