import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Game implements ActionListener {
    private GameViewer window;
    private final int NUM_ANIMALS = 20;
    private static final int SLEEP_TIME = 110;
    private ArrayList<Animal> animals;

    private int speedFactor;
    private int score;
    private int misclicks;

    private int lives = 3;

    public Game() {
        // Initialize the View, then create all the Animals:
        animals = new ArrayList<Animal>();
        this.window = new GameViewer(this);
        speedFactor = 2;
        misclicks = 0;
        score = 0;
        //change this
        for (int i = 0; i < 20; i++) {
            int val = (int) (Math.random() * 3);
            //Ok i don't want to have this line because it seems redundant and unnessasry but oh well
            Animal animal = null;
            if (val == 0)
            {
                animal = new Animal(window);
            }
            else if (val == 1)
            {
                animal = new SpeedySnail(window, speedFactor);
            }
            else if (val == 2)
            {
                animal = new BombBear(window, speedFactor);
            }
            animals.add(animal);
        }

    }
    public void actionPerformed(ActionEvent e) {
        if (lives > 0)
        {
            for (Animal a : animals) {
                //executes method specific to each class that does the action
                a.doyourthing();
            }
            window.repaint();
        }

    }

    public ArrayList<Animal> getAnimals() {
        System.out.println(animals);
        return animals;
    }

    public void addMisclick()
    {
        misclicks++;
    }
    public void addScore()
    {
        score++;
    }

    public void removeLife()
    {
        lives--;
    }
    public static void main(String[] args) {
        Game a = new Game();
        Timer clock = new Timer(SLEEP_TIME, a);
        clock.start();
    }
}


