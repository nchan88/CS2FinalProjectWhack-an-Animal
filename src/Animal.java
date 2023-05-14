import java.awt.*;
public class Animal {
    private GameViewer screen;
    private Image moleimage;
    //All animals need coordinates to know when they are clicked.
    private int x, y;
    private int windowWidth;
    private int windowHeight;
    public Animal(GameViewer screen) {
        this.screen = screen;
        this.windowWidth = screen.getWidth();
        this.windowHeight = screen.getHeight();
        moleimage = screen.getImages()[0];
        // Initializes the animals random location.
        this.x = (int) (Math.random() * windowWidth * 0.8);
        this.y = (int) (Math.random() * windowHeight * 0.8);
    }
    //This method does not do anything for the bear and animal classes, however, I want to make it a polymorphic so that
    //new animals could be created in the future.
    public void doyourthing() {
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
        //draws every animal object currently on the board
        g.drawImage(moleimage, x, y, screen);
    }
}