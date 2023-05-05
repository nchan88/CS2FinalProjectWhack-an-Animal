import javax.swing.*;
import java.awt.event.*;
public class Window implements ActionListener {
    private GameViewer window;
    private final int NUM_ANIMALS = 20;
    private static final int SLEEP_TIME = 110;
    private Animal[] animals;

    public Window() {
        // Initialize the View, then create all the Fish:
        animals = new Animal[NUM_ANIMALS];
        this.window = new GameViewer(this);
        for (int i = 0; i < animals.length; i++) {
            animals[i] = new Animal(window);
        }

    }
    public void actionPerformed(ActionEvent e) {
        for (Animal a : animals) {
            //executes method specific to each class that does the action
            a.doyourthing();
        }
        window.repaint();
    }

    public Animal[] getAnimals() {
        return animals;
    }
}
    public void playGame()
    {
        Window a = new Window();
        Timer clock = new Timer(110, a);
        clock.start();
    }
    public static void main(String[] args) {
        g.playGame();
    }
//add method while lives > 0 play game.
//+playGame
//+main
//+isGameover
