package lab_11;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by jcpatac on 10/21/2016.
 */

/**
 * This class is for our racket in the game. Here, we are going to do the methods used to move and draw the racket in our Pong game.
 */

public class Racket {

    private Game game;
    private static final int X1 = 730; // position of the first racket in x-axis
    public static final int X2 = 30; // position of the second racket in x-axis
    public static final int WIDTH = 60; // width of our racket
    public static final int HEIGHT = 10; // height of our racket

    int y = 100; // position of the rackets in y-axis
    int y1 = 0; // movement of our racket per pixels in one game loop (zero because our racket will not move by itself)
//    int racketSpeed = 5;

    public Racket(Game game) { // This is the constructor for the class racket. It initialize the Game class
        this.game = game;
    }

    public void move() { // used to move the racket up and down
        if (y + y1 > 0 && y + y1 < game.getHeight() - HEIGHT) {
            y += y1;
        }
    }

    public void paint(Graphics2D g) { // used to paint the racket in the screen
        g.setColor(Color.RED); // The color of this racket is red
        g.fillRect(X1, y, HEIGHT, WIDTH); // fills the rectangle with specific dimension and position in the screen
    }

    public void keyPressed(KeyEvent e) { // determine the key pressed in the keyboard
        if (e.getKeyCode() == KeyEvent.VK_UP) { // if UP key was pressed
            y1 = -game.speed; // we go to the upper part of the screen
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) { // if DOWN key was pressed,
            y1 = game.speed; // we go to the lower part of our screen
        }
    }

    public void keyReleased(KeyEvent e) { // release key after pressed
        y1 = 0; // if the key is released, we stop moving our racket
    }

    public Rectangle getBounds1() { // get the bounds of the racket
        return new Rectangle(X1, y, HEIGHT, WIDTH); // returns a rectangle (the first racket) with specific position and dimension
    }

    public Rectangle getBounds2() { // get the bounds of the racket
        return new Rectangle(X2, y, HEIGHT, WIDTH); // returns a rectangle (the second racket) with specific position and dimension
    }
}

/**
 * Programs are like women. No matter how much you get mad because of errors, you are always the one who is wrong.
 */
