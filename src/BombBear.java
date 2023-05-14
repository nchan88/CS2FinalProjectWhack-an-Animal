import java.awt.*;

public class BombBear extends Animal {
    //A bear that when you click on it the game ends.
    private Image evilBear;
    private Image bearImage;
    private int count;
        private int x, y;
        public BombBear(GameViewer screen)
        {
            super(screen);
            count = 0;
            x = (int) (Math.random() * screen.getWidth() * 0.8);
            y = (int) (Math.random() * screen.getHeight() * 0.8);
            bearImage = screen.getImages()[2];
            evilBear = screen.getImages()[3];
        }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void draw(Graphics g) {
        //Draws every bombbear object currently on the board.
        //This if statement makes it so everytime the Bombbear is drawn, it alternates its image, giving it an
        //appearance of flashing rapidly
        if (count == 0)
        {
            g.drawImage(bearImage, x, y, getScreen());
            count = 1;
        }
        else
        {
            g.drawImage(evilBear, x, y, getScreen());
            count = 0;
        }
    }
}
