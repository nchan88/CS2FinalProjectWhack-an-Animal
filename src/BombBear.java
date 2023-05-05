public class BombBear {
    //A bear that when you click it explodes. Custom artwork possibly. Gotta make it vibrant
    private Image BombBearIcon;
    private int explosionClock;

        private int dx, dy;
        private static final int MAX_SPEED = 8;
        private int speed;
        public BombBear(GameViewer screen, int speed, int MAX_SPEED - (int) (Math.random() * MAX_SPEED * 2, int )
        {
            super(screen);
            //speed gradually increased
            this.explosionClock = speed;
            this.dx = MAX_SPEED - (int) (Math.random() * MAX_SPEED * 2);
            this.dy = MAX_SPEED - (int) (Math.random() * MAX_SPEED * 2);
        }

        public void explodes() {
            // This creates a 1-in-7 probability we change the fish's speed.

        }
}
