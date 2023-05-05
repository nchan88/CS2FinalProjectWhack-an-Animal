public class SpeedySnail extends Animal {
 private int dx, dy;

 private int x, y;
 private static final int MAX_SPEED = 8;
 private int speedFactor;
    public SpeedySnail(GameViewer screen, int speedFactor)
    {
        super(screen);
        //speed gradually increased
        this.speedFactor = speedFactor;
        this.dx = MAX_SPEED - (int) (Math.random() * MAX_SPEED * 2) * speedFactor;
        this.dy = MAX_SPEED - (int) (Math.random() * MAX_SPEED * 2) * speedFactor;
    }

//    public void move() {
//        // This creates a 1-in-7 probability we change the fish's speed.
//        if ((int) (Math.random() * 7) == 1)
//        {
//            dx += -MAX_SPEED / 2 + (int) (Math.random() * MAX_SPEED);
//            dx = Math.min(dx, MAX_SPEED);
//            dx = Math.max(dx, -MAX_SPEED);
//            dy += -MAX_SPEED / 2 + (int) (Math.random() * MAX_SPEED);
//            dy = Math.min(dy, MAX_SPEED);
//            dy = Math.max(dy, -MAX_SPEED);
//        }
//        x += dx;
//        y += dy;
//    }
//    public void bounce() {
//        // TODO: Write the Fish's bounce() method.
//        if ((x <= 0 && dx < 0) || (x + rightImage.getWidth(tank) >= tankWidth && dx > 0)) {
//            dx = -dx;
//        }
//
//        // Now check for a y bounce.
//        if ((y <= 0 && dy < 0) || (y + leftImage.getHeight(tank)  >= tankHeight && dy > 0)) {
//            dy = -dy;
//        }
//    }
    public void doyourthing()
    {
        System.out.println("yo");
    }
}
