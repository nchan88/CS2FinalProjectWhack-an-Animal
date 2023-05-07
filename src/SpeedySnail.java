import java.awt.*;

public class SpeedySnail extends Animal {
 private int dx, dy;
 private Image snailImage;
 private int x, y;
 private static final int MAX_SPEED = 8;
 private int speedFactor;
    public SpeedySnail(GameViewer screen, int speedFactor)
    {
        super(screen);
        //speed gradually increased
        this.speedFactor = speedFactor;
        x = (int) (Math.random() * screen.getWidth() * 0.8);
        y = (int) (Math.random() * screen.getHeight() * 0.8);
        dx = MAX_SPEED - (int) (Math.random() * MAX_SPEED * 2) * speedFactor;
        dy = MAX_SPEED - (int) (Math.random() * MAX_SPEED * 2) * speedFactor;
        snailImage = screen.getImages()[1];
    }

    public void move() {
        x += dx;
        y += dy;
    }
    public void bounce() {
        if ((x <= 0 && dx < 0) || (x + snailImage.getWidth(getScreen()) >= getScreen().getWidth() && dx > 0)) {
            dx = -dx;
        }

        // Now check for a y bounce.
        if ((y <= 0 && dy < 0) || (y + snailImage.getHeight(getScreen())  >= getScreen().getHeight() && dy > 0)) {
            dy = -dy;
        }
    }
    public void doyourthing()
    {
        System.out.println("Snail");
        move();
        bounce();
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void draw(Graphics g) {
        //draws every object currently on the board
        g.drawImage(snailImage, x, y, getScreen());
    }
}
