package server;

import java.net.*;
import java.io.*;
 
public class ZuschauerProtokoll {
    private static final int WAITING = 0;
    private static final int SENTQUESTION = 1;
 
    private int state = WAITING;
 
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
