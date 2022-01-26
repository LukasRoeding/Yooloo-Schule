package server;

import java.net.*;
import java.io.*;
 
public class KnockKnockProtocol {
    private static final int WAITING = 0;
    private static final int SENTQUESTION = 1;
    private static final int SENTCLUE = 2;
    private static final int ANOTHER = 3;
 
    private static final int NUMJOKES = 5;
 
    private int state = WAITING;
    private int currentJoke = 0;
 
    private String[] clues = { "Turnip", "Little Old Lady", "Atch", "Who", "Who" };
    private String[] answers = { "Turnip the heat, it's cold in here!",
                                 "I didn't know you could yodel!",
                                 "Bless you!",
                                 "Is there an owl in here?",
                                 "Is there an echo in here?" };
 
    public String processInput(String theInput) {
        String theOutput = null;
 
        if (state == WAITING) {
            theOutput = "Zuschauer?";
            state = SENTQUESTION;
        } else if (state == SENTQUESTION) {
            if (theInput.equalsIgnoreCase("Ja")) {
                theOutput = "Zuschauer";
                state = WAITING;
            } else if (theInput.equalsIgnoreCase("Nein")){
                theOutput = "Spieler";
                state = WAITING;
            }
            else {
                theOutput = "Du sollst Ja oder Nein sagen!" +
                "Zuschauer?";
            }
        }
        return theOutput;
    }
}
