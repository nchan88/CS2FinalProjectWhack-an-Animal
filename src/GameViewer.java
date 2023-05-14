import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
public class GameViewer extends JFrame implements MouseListener {
        private Image windowImage;
        private Image[] animalImages;
        private final int WINDOW_WIDTH = 1000;
        private final int WINDOW_HEIGHT = 800;
        //A misclick is any click that does not hit an animal.
        private int misclicks;
        private int score;
        //This boolean tracks whether the game ended because of a misclick or if it was played to completion.
        private boolean lost;
        private double highScore;
        public final int ANIMAL_SIZE = 48;
        public final int BEAR_SIZE = 62;
        private Game g;
        //Try changing this! You can play with as many (or as few) rounds as you would like.
        private final int NUM_ROUNDS = 3;
        public GameViewer(Game g) {
            this.g = g;
            lost = false;
            //Since clicking to go to the next round is considered a misclick, I initialize misclicks to be
            //the negative amount of rounds so that the number is accurate in the end.
            misclicks = NUM_ROUNDS * -1;
            score = 0;
            highScore = Integer.MAX_VALUE;
            windowImage = new ImageIcon("Resources/backgroundimage.jpg").getImage();
            //Initializes all my animal images in an array.
            animalImages = new Image[4];
            animalImages[0] = new ImageIcon("Resources/mole.png").getImage();
            animalImages[1] = new ImageIcon("Resources/snail.png").getImage();
            animalImages[2] = new ImageIcon("Resources/bear.png").getImage();
            animalImages[3] = new ImageIcon("Resources/evilbear.png").getImage();
            addMouseListener(this);
            // Setup the window and the buffer strategy.
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setTitle("Whack-an-Animal DELUXE EDITION");
            setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
            setVisible(true);
            createBufferStrategy(2);
        }

        public Image[] getImages() {
            return animalImages;
        }

        public void paint(Graphics g) {
            BufferStrategy bf = this.getBufferStrategy();
            if (bf == null)
                return;
            Graphics g2 = null;
            try {
                g2 = bf.getDrawGraphics();
                myPaint(g2);
            }
            finally {
                g2.dispose();
            }
            bf.show();
            Toolkit.getDefaultToolkit().sync();
        }
        @Override
        // # 7: Required of a MouseListener
        public void mousePressed(MouseEvent e)
        {
            if (!g.getGameOver()) {
                //I assume a misclick unless there isn't one, in which case I will subtract.
                misclicks++;
                if (g.isonlyBears()) {
                    //If the round is passed, simulates another one and adds to the int round.
                    if (g.getRound() < NUM_ROUNDS) {

                        g.simulateRound(g.getAnimalCount(), g.getSpeedFactor());
                        repaint();
                        g.addRound();
                    }
                    //This gets triggered if the round capacity has been reached and the player has finished their game.
                    else {
                        g.getAnimals().clear();
                        repaint();
                        g.setGameOver();
                    }
                }
                //If I don't make a copy of animals here then I will get the concurrent modification exception error
                //While this error doesn't seem to effect how my program actually runs, if I don't include the lines below
                //my terminal is flooded with error messages.
                ArrayList<Animal> iterativecopy = new ArrayList<Animal>();
                iterativecopy.addAll(g.getAnimals());
                //Checks if you click any animals.
                for (Animal a : iterativecopy) {
                    //If your click is within the borders of any animal, that animal is removed.
                    //The reason there are different parameters for the friendlybear class is because it is slightly bigger
                    //and therefore has an adjusted hitbox than the snail and mole.
                    if ((e.getX() >= a.getX() && e.getX() <= a.getX() + ANIMAL_SIZE && e.getY() >= a.getY() &&
                        e.getY() <= a.getY() + ANIMAL_SIZE || (a instanceof FriendlyBear && e.getX() >= a.getX() &&
                        e.getX() <= a.getX() + BEAR_SIZE && e.getY() >= a.getY() && e.getY() <= a.getY() + BEAR_SIZE))
                        && !(a instanceof BombBear)) {
                        g.getAnimals().remove(a);
                        score++;
                        misclicks--;
                        //Even though I call paint in action performed this call makes it considerably more responsive.
                        repaint();
                    }
                    //If a BombBear is clicked ends the game and mark the game as a loss.
                    //Player has to make sure even if overlapping that they do not press a BombBear.
                    else if (a instanceof BombBear && e.getX() >= a.getX() && e.getX() <= a.getX() + 62 && e.getY() >= a.getY()
                        && e.getY() <= a.getY() + 62) {
                            lost = true;
                            g.getAnimals().clear();
                            repaint();
                            g.setGameOver();
                        }
                    }
                }
            else
            {
                misclicks = NUM_ROUNDS * -1;
                score = 0;
                g.getAnimals().clear();
                //If the click is within the dimensions of the word "HERE".
                if (e.getX() >= 815 && e.getX() <= 952 && e.getY() >= 564 && e.getY() <= 603) {
                    //Runs a new game
                    lost = false;
                    g.run();
                }
            }
        }
        public void mouseReleased(MouseEvent e) {
        }
        public void mouseClicked(MouseEvent e) {
        }
        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }
        public void myPaint(Graphics g) {
            if (!this.g.getGameOver()) {
                g.drawImage(windowImage, 0,0,this);
                //Draws each animal object behind any text that will be drawn later in this method.
                for (Animal a : this.g.getAnimals()) {
                    a.draw(g);
                }
                //Makes sure it doesn't display a high score in the first game.
                if (highScore != Integer.MAX_VALUE) {
                    g.drawString("High Score " + Double.toString(highScore), 300, 50);
                }
                g.drawString("Animals Clicked: " + Integer.toString(score), 100, 50);
                //Gets the current time since the game has started.
                g.drawString(Double.toString(this.g.calculateTotal()), 700, 50);
            }
            else {
                gameoverSequence(g);
            }
        }
        public void gameoverSequence(Graphics g) {
            g.clearRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            g.drawImage(windowImage, 0,0,this);
            g.setFont(new Font("Serif", Font.PLAIN, 50));
            if (lost) {
                lostSequence(g);
            }
            else {
                completedSequence(g);
            }

        }
        public void lostSequence(Graphics g)
        {
            g.drawString("You clicked a BombBear... GAME OVER!", 70, 300);
            g.drawString("If you would like to play again, press HERE", 70, 600);
        }
        public void completedSequence(Graphics g)
        {
            //If the score from this game is lower than the previous high score, set it to high score.
            if (this.g.calculateTotal() < highScore) {
                highScore = this.g.calculateTotal();
            }
            g.drawString("High Score " + Double.toString(highScore), 100, 80);
            g.drawString("Animals clicked: " + Integer.toString(score), 100, 300);
            g.drawString("Your time was " + this.g.calculateTotal() + " seconds", 100, 200);
            //Rounds accuracy to one decimal.
            g.drawString("Your accuracy was " + Double.toString(Math.round((score * 1.0 / (score + misclicks)
            ) * 100) * 100.0 / 100.0) + "%", 100, 400);
            g.drawString("If you would like to play again, press HERE", 70, 600);
        }
    }



