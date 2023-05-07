import java.awt.*;

public class BombBear extends Animal {
    //A bear that when you click it explodes. Custom artwork possibly. Gotta make it vibrant
    private Image bearImage;
    private int explosionClock;
    private static final int MAX_SPEED = 8;
        private int x, y;
        private int speed;
        public BombBear(GameViewer screen, int speedFactor)
        {
            super(screen);
            x = (int) (Math.random() * screen.getWidth() * 0.8);
            y = (int) (Math.random() * screen.getHeight() * 0.8);
            //speed gradually increased
            this.explosionClock = speed;
            bearImage = screen.getImages()[2];
        }

        public void explodes() {
            //If clicked, lose life.

        }
    public void doyourthing()
    {
        explodes();
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void draw(Graphics g) {
        //draws every object currently on the board
        g.drawImage(bearImage, x, y, getScreen());
    }
}
