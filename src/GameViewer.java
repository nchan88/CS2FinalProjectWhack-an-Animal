import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import static java.lang.String.valueOf;

public class GameViewer extends JFrame implements MouseListener {
        private Image windowImage;
        private Image[] animalImages;
        private final int WINDOW_WIDTH = 1000;
        private final int WINDOW_HEIGHT = 800;
        private int misclicks;
        private int score;
        private boolean lost;

        private double highScore;
        private Game g;

        private final int NUM_ROUNDS = 3;
        private int d;
        public GameViewer(Game g) {

            // Initialize instance variables.
            // TODO: initialize the View's instance variables.
            this.g = g;
            lost = false;
            misclicks = NUM_ROUNDS * -1;
            score = 0;
            highScore = 0;
            windowImage = new ImageIcon("Resources/backgroundimage.jpg").getImage();
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
//            ArrayList<Animal> iterativecopy = new ArrayList<Animal>();
//            iterativecopy.addAll(g.getAnimals());
            // Change the color
            if (!g.getGameOver())
            {
                misclicks++;
                if (g.isonlyBears())
                {
                    if (g.getRound() < 3)
                    {

                        g.simulateRound(g.getAnimalCount());
                        repaint();
                        g.addRound();
                    }
                    else
                    {
                        g.getAnimals().clear();
                        repaint();
                        g.setGameOver();
                    }

                }
                for (Animal a : g.getAnimals())
                {

                    if (e.getX() >= a.getX() && e.getX() <= a.getX() + 48 && e.getY() >= a.getY() && e.getY() <= a.getY() + 48 || (a instanceof FriendlyBear && e.getX() >= a.getX() && e.getX() <= a.getX() + 62 && e.getY() >= a.getY() && e.getY() <= a.getY() + 62))
                    {
                        g.getAnimals().remove(a);
                        score++;
                        misclicks--;
                        //Even though I call paint in action performed this makes it considerably more responsive
                        repaint();
                    }
                    if (a instanceof BombBear && e.getX() >= a.getX() && e.getX() <= a.getX() + 62 && e.getY() >= a.getY() && e.getY() <= a.getY() + 62)
                    {
                        {
                            lost = true;
                            g.getAnimals().clear();
                            repaint();
                            g.setGameOver();
                        }
                        //EXPLODE and gameOver = true
                    }
                }
            }

        }

        @Override
        // # 8: Required of a MouseListener
        public void mouseReleased(MouseEvent e)
        {
            // For demo purposes only
            System.out.println("mouseReleased event handler executed.");
        }

        @Override
        // # 9: Required of a MouseListener
        public void mouseClicked(MouseEvent e)
        {

        }

        @Override
        // # 10: Required of a MouseListener
        public void mouseEntered(MouseEvent e)
        {
            // For demo purposes only
            System.out.println("mouseEntered event handler executed.");
        }

        @Override
        // # 11: Required of a MouseListener
        public void mouseExited(MouseEvent e)
        {
            // For demo purposes only
            System.out.println("mouseExited event handler executed.");
        }
        public void myPaint(Graphics g) {
            if (!this.g.getGameOver())
            {
                if (highScore != 0)
                {
                    g.drawString("High Score " + valueOf(highScore), 100, 80);
                }
                g.drawImage(windowImage, 0,0,this);
                g.drawString("Score " + valueOf(score), 100, 50);
                g.drawString("Misclicks " + valueOf(misclicks), 300, 50);
                g.drawString(valueOf(this.g.calculateTotal()), 700, 50);
                for (Animal a : this.g.getAnimals())
                {
                    //draw the score and timer
//                System.out.println("Animal at x = " + a.getX() + " y = " + a.getY());
                    a.draw(g);
                }
            }
            else
            {
                gameoverSequence(g);
            }
        }
        public void gameoverSequence(Graphics g)
        {
            g.clearRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            g.drawImage(windowImage, 0,0,this);
            g.setFont(new Font("Serif", Font.PLAIN, 50));
            if (lost)
            {
                g.drawString("You clicked a BombBear... GAME OVER!", 70, 300);
                g.drawString("If you would like to play again, press HERE", 70, 400);
            }
            else
            {
                if (this.g.calculateTotal() > highScore)
                {
                    highScore = this.g.calculateTotal();
                }
                g.drawString("High Score " + valueOf(highScore), 100, 80);
                g.drawString("Score " + valueOf(score), 100, 200);
                g.drawString("Your time was " + this.g.calculateTotal() + "seconds", 100, 600);
                g.drawString("Your accuracy was " + valueOf((score * 1.0 / (score + misclicks)) * 100) + "%", 100, 400);
            }

        }
    }



