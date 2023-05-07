import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
    public class GameViewer extends JFrame implements MouseListener {
        private Image windowImage;
        private Image[] animalImages;
        private final int WINDOW_WIDTH = 1000;
        private final int WINDOW_HEIGHT = 800;
        private Game g;
        public GameViewer(Game g) {

            // Initialize instance variables.
            // TODO: initialize the View's instance variables.
            this.g = g;
            windowImage = new ImageIcon("Resources/backgroundimage.jpg").getImage();
            animalImages = new Image[3];
            animalImages[0] = new ImageIcon("Resources/mole.jpg").getImage();
            animalImages[1] = new ImageIcon("Resources/snail.jpg").getImage();
            animalImages[2] = new ImageIcon("Resources/bear.jpg").getImage();
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
            // Change the color
            for (Animal a : this.g.getAnimals())
            {
                if (e.getX() >= a.getX() && e.getX() <= a.getX() + 48 && e.getY() >= a.getY() && e.getY() <= a.getY() + 48)
                {
                    if (a instanceof BombBear)
                    {
                        //gameover = true;
                    }
                    g.getAnimals().remove(a);
                    g.addScore();
                }
                else
                {
                    g.addMisclick();
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
            g.drawImage(windowImage, 0,0,this);
            for (Animal a : this.g.getAnimals())
            {
                System.out.println("Animal at x = " + a.getX() + " y = " + a.getY());
                a.draw(g);
            }
        }
    }



