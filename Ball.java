package lab_11;

/**
 * Created by acer on 10/20/2016.
 */


/**
 * This class is for our ball in the game. Here, we are going to draw the ball, check collisions in all sides, and move the ball.
 */

import java.awt.*;
import java.awt.event.KeyEvent;


public class Ball { // class
    public int score1 = 0;
    public int score2 = 0;
    private Game game;
    private static final int DIAMETER = 30; // sets the diameter of the ball
    int x = 0; // x position of our ball
    int y = 0; // y position of out ball
    int x1 = 1; // this represents the speed of the ball moving in our x-axis. (1 pixel every round of the game loop)
    int y1 = 1; // this represents the speed of the ball moving in our y-axis. (1 pixel every round of the game loop)

    public Ball(Game game) { // constructor that initializes the class Game
        this.game = game; // initializes the class Game
    }

    public void move() { // method that is used to move the ball
        boolean changeDirection = true;
        if (y + y1 < 0) { // We want to change the direction if the ball hits the upper border
            y1 = game.speed;
        }else if (y + y1 > game.getHeight() - DIAMETER) { // we want to change the direction if the ball hits the lower border
            y1 = -game.speed;
        }else if (x + x1 < 0) { // Checks if the ball hits the left or right border. If it does, the round ends
            score2++;
            game.player2Score = score2;
            x1 = game.speed;
        }else if (x + x1 > game.getWidth() - DIAMETER) {
            score1++;
            game.player1Score = score1;
            x1 = -game.speed;
        }else if (collision1()) { // checks if our ball collides with our first racket
            x1 = -game.speed;
//            y = game.racket1.getTopY() - DIAMETER;
            game.speed++;
        }else if (collision2()) { // checks if our ball collides with our second racket
            x1 = game.speed + 1;
//            y = game.racket2.getTopY() + DIAMETER;
//            game.speed++;
        }else { // if neither of the borders nor the rackets are hit, we don't change our direction of the ball
            changeDirection = false;
        }
        if (changeDirection) { // if the ball hits the border or the racket, the sound of collision is going to play.
            Sound.BALL.play();
        }

        if (score1 == 3 || score2 == 3) {
            game.gameOver(" Wins!");
        }
        x += x1; // helps in moving our ball in x-axis
        y += y1; // helps in moving our ball in y-axis
    }

    private boolean collision1() { // determines if the ball collides with the first racket
        return game.racket1.getBounds1().intersects(getBounds()); // returns true if the two rectangles intersect
    }

    private boolean collision2() { // determines if the ball collides with the first racket
        return game.racket2.getBounds2().intersects(getBounds()); // returns true if the two rectangles intersect
    }

    public void paint(Graphics2D g) { // used to paint the ball int the screen
        g.setColor(Color.ORANGE); // sets the color of the ball as orange
        g.fillOval(x, y, DIAMETER, DIAMETER); // fills the oval with specific diameter and position in the screen
    }

    public Rectangle getBounds() { // used to get the bounds of the ball
        return new Rectangle(x, y, DIAMETER, DIAMETER); // returns a rectangle with its dimensions
    }
}

/**
 * Programs are like women. No matter how much you get mad because of errors, you are always the one who is wrong.
 */