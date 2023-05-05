import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
    public class GameViewer extends JFrame {
        private Image windowImage;
        private Image[] animalImages;
        private final int WINDOW_WIDTH = 1000;
        private final int WINDOW_HEIGHT = 800;
        private Window a;
        public GameViewer(Window a) {

            // Initialize instance variables.
            // TODO: initialize the View's instance variables.
            this.a = a;
            windowImage = new ImageIcon("Resources/backgroundimage.jpg").getImage();
            animalImages = new Image[2];
            animalImages[0] = new ImageIcon("Resources/moleimage.jpg").getImage();
            animalImages[1] = new ImageIcon("Resources/moleimage.jpg").getImage();


            // Setup the window and the buffer strategy.
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setTitle("Whack-an-Animal DELUXE EDITION");
            this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
            this.setVisible(true);
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
        public void myPaint(Graphics g) {
            g.drawImage(windowImage, 0,0,this);
            for (Animal a : a.getAnimals())
            {
                a.draw(g);
            }
        }
    }



