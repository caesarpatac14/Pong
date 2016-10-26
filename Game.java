package lab_11;

/**
 * Created by acer on 10/20/2016.
 */


/**
 * This class Game will be our main class. Here, we draw the frames, loop the game, and call the classes or methods in our racket, ball, and sound classes.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


@SuppressWarnings("serial")
public class Game extends JPanel { // our class that extends JPanel

    String player1;
    String player2;

    int currChoice = 0;
    private String[] options = {"Start", "Quit"};

    private int p1PoweUps = 1;
    private int p2PowerUps = 1;

    Ball ball = new Ball(this); // instantiate a new class Ball passing this class Game
    Racket racket1 = new Racket(this); // instantiates a new class Racket passing this class Game (first racket)
    Racket racket2 = new Racket(this); // instantiates a new class Racket passing this class Game (second racket)
    int speed = 1; // the speed of the game
    public int player1Score = 0; // indicates player 1's score
    public int player2Score = 0; // indicates player 2's score

    private int getScore1() { // the score of player 1
        return player1Score;
    }

    private int getScore2() { // the score of player 1
        return player2Score;
    }

    public Game() { // our default constructor

        addKeyListener(new KeyListener() { // we will instantiate a new KeyListener for our controls
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) { // determines the key pressed in the keyboard
                racket1.keyPressed(e); // performs the method keyPressed in class Racket (for racket 1)

                // performs this method to move our second racket
                if (e.getKeyCode() == KeyEvent.VK_W) { // if W was pressed, move up
                    racket2.y1 = -speed;
                }
                if (e.getKeyCode() == KeyEvent.VK_S) { // if S was pressed, move down
                    racket2.y1 = speed;
                }
                if (e.getKeyCode() == KeyEvent.VK_D) {
                    if (p1PoweUps == 1) {
                        ball.x1 = speed + 5;
                        p1PoweUps--;
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (p2PowerUps == 1) {
                        ball.x1 = -speed - 5;
                        p2PowerUps--;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                racket1.keyReleased(e);
                racket2.keyReleased(e);
            } // release the key after pressing
        });
        setFocusable(true); // to be able to read keyboard events, this must be true
        Sound.BACK.loop(); // set the sound effects
    }

    private void move() { // method used to move  the ball and the racket
        ball.move(); // calls the move method in our Ball class to move our ball
        racket1.move(); // calls the move method in our Racket class to move our racket
        racket2.move(); // calls the move method in our Racket class to move our racket
    }

    @Override
    public void paint(Graphics g) { // this method is used to draw the ball and racket in the screen
        super.paint(g); // calls the paint method in our JPanel superclass

        Graphics2D g2d = (Graphics2D) g; // typecasting g to Graohics2D data type
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // used to remove bad graphics. Smoother Borders are shown
        ball.paint(g2d); // paints the ball
        racket1.paint(g2d); // paints our first racket

        // This whole thing paints our second racket with color black
        g.setColor(Color.BLACK);
        g.fillRect(racket2.X2, racket2.y, racket2.HEIGHT, racket2.WIDTH);

        g2d.fillRect(395, 0, 3, 300); // serves as the net for better GUI

        // sets the font, its color and position on screen
        g2d.setColor(Color.GRAY);
        g2d.setFont(new Font("Verdana", Font.BOLD, 20));
        g2d.drawString(player1 + ": " + String.valueOf(getScore1()), 10, 30);
        g2d.drawString("Power Up: " + p1PoweUps, 10, 55);
        g2d.drawString(player2 + ": " + String.valueOf(getScore2()), 425, 30);
        g2d.drawString("Power Up: " + p2PowerUps, 425, 55);
    }

    public void gameOver(String winner) { // display a screen with your scare after you fail to bounce the ball back
        Sound.BACK.stop(); // stop the looping of the back sound effects
        Sound.GAMEOVER.play(); // play the game over sound
        JFrame frame = new JFrame();
        String daog = "";
        if (player1Score == 3) { // If player 1 score is 3, set the player as the winner
            daog = player1 + winner + "\n" + "Play Again?";
        }
        if (player2Score == 3) { // If player 2 score is 3, set the player as the winner
            daog = player2 + winner + "\n" + "Play Again?";
        }
        int choice = JOptionPane.showConfirmDialog(frame, daog); // pop up a frame that says the winner and if restart or not

        if (choice == JOptionPane.YES_OPTION) { // if condition yes was selected, reset all variables
            player1Score = 0;
            player2Score = 0;
            ball.y = 0;
            ball.x = 0;
            ball.x1 = 1;
            ball.y1 = 1;
            ball.score1 = 0;
            ball.score2 = 0;
            racket1.y = 100;
            racket2.y = 100;
            speed = 1;
            p1PoweUps = 1;
            p2PowerUps = 1;
            gamePlay();
        }else if (choice == JOptionPane.NO_OPTION) { // if no option was selected, exit the game
            System.exit(ABORT);
        }
    }

    private void gamePlay() { // the loop of the game
        player1 = JOptionPane.showInputDialog("Enter Player 1 Name"); // show a window that asks for the name of player 1
        player2 = JOptionPane.showInputDialog("Enter PLayer 2 Name"); // show a window that asks for the name of player 2
        while(true) { // a loop that only stops if the game is over. Repaint the game for moving effects
            move();
            repaint();

            try { // if this is not done, the game will be over after you run it. Also contributes to speed of the ball
                Thread.sleep(10);
            }catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) { // main method
        JFrame frame = new JFrame("Mini Tennis"); // the
        Game game = new Game(); // instantiate a new class game
        frame.add(game); // add a new game to your frame
        frame.setResizable(false); // set the screen as non-resizable
        frame.setSize(800, 300); // set the preferred size of the screen
        frame.setVisible(true); // set to true to be able to see the frame in the screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // set to close the frame to avoid memory leak

        game.gamePlay();
    }
}

/**
 * Programs are like women. No matter how much you get mad because of errors, you are always the one who is wrong.
 */
