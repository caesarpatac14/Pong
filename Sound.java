package lab_11;

import java.applet.Applet;
import java.applet.AudioClip;

/**
 * Created by acer on 10/20/2016.
 */


/**
 * This class is used to put background music in our game.
 */


public class Sound { // Just basically opening our audio files and declaring it as constants.

    public static final AudioClip BALL = Applet.newAudioClip(Sound.class.getResource("ball.wav")); // opens the ball.wav file and stores it to BALL
    public static final AudioClip GAMEOVER = Applet.newAudioClip(Sound.class.getResource("gameover.wav")); // opens gameover.wav and stores it to GAMEOVER
    public static final AudioClip BACK = Applet.newAudioClip(Sound.class.getResource("back.wav")); // opens the back.wav and stores it to BACK

}

/**
 * Programs are like women. No matter how much you get mad because of errors, you are always the one who is wrong.
 */
