import java.awt.*;

public class SpeedySnail extends Animal {
    //A snail that moves at various speeds across the board.
    //Some snails might move like Turbo, and some might move like... well... snails...
    private int dx, dy;
    private Image snailImage;
    private int x, y;
    private static final int MAX_SPEED = 8;
    private double speedFactor;
    public SpeedySnail(GameViewer screen, double speedFactor)
    {
        super(screen);
        //speed gradually increased
        this.speedFactor = speedFactor;
        x = (int) (Math.random() * screen.getWidth() * 0.8);
        y = (int) (Math.random() * screen.getHeight() * 0.8);
        dx = MAX_SPEED - (int) ((Math.random() * MAX_SPEED * 2) * speedFactor);
        dy = MAX_SPEED - (int) ((Math.random() * MAX_SPEED * 2) * speedFactor);
        snailImage = screen.getImages()[1];
    }
    //Adjusts position based on dx and dy values every time actionPerformed is called, simulated movement.
    public void move() {
        x += dx;
        y += dy;
    }
    //If hits a wall bounces.
    public void bounce() {
        if ((x <= 0 && dx < 0) || (x + snailImage.getWidth(getScreen()) >= getScreen().getWidth() && dx > 0)) {
            dx = -dx;
        }
        if ((y <= 0 && dy < 0) || (y + snailImage.getHeight(getScreen())  >= getScreen().getHeight() && dy > 0)) {
            dy = -dy;
        }
    }
    public void doyourthing() {
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
        //Draws every snail object currently on the board
        g.drawImage(snailImage, x, y, getScreen());
    }
}
