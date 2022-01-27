package server;

import client.YoolooClient;
import common.YoolooKarte;
import common.YoolooKartenspiel;
import common.YoolooSpieler;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class YoolooLoginData {

    static Scanner scanner  = new Scanner(System.in);
    private static String[][] localLoginDataCollection, tempDataCollection;
    private static int numberOfUsers;
    private static boolean couldLogIn;
    private static String activeUser;
    private static int[] cardOrder;
    private static String[] statusArray;
    File folder = new File("data");
    File file = new File("data/users.txt");
    StringBuilder sb = new StringBuilder();

    public YoolooLoginData() {
    }

    public static String regislog() {
        System.out.println("Welcome to Yooloo Digital.");
        String userInput;
        couldLogIn=false;

        initializeCollection();

        while(couldLogIn == false) {
            System.out.println("Please type in if you want to Login or Register:");
            userInput = scanner.nextLine();
            if (userInput.matches("Login") || userInput.matches("login")) {
                loginUser();
            } else if (userInput.matches("Register") || userInput.matches("register")) {
                registerUser();
            } else {
                System.out.println("Your input was incorrect. Please write \"Login/login\" or \"Register/register\"");
            }
        }
        return activeUser;
    }

    private static void initializeCollection() {
        YoolooLoginData data = new YoolooLoginData();

        while(true) {
            if (data.folder.exists()) {
                if (data.file.exists()) {
                    String path = data.file.getAbsolutePath();

                    if (path.endsWith("users.txt")) {
                        File file = new File(path);

                        try {
                            Scanner sc = new Scanner(file);
                            numberOfUsers = Integer.parseInt(sc.nextLine());


                            localLoginDataCollection = new String[numberOfUsers][3];

                            for (int userIndex = 0; userIndex < numberOfUsers; userIndex++) {
                                //UserName
                                localLoginDataCollection[userIndex][0] = sc.nextLine();
                                //Status
                                localLoginDataCollection[userIndex][1] = sc.nextLine();
                                //Reihenfolge
                                localLoginDataCollection[userIndex][2] = sc.nextLine();
                            }

                            sc.close();
                        } catch (FileNotFoundException var7) {
                            var7.printStackTrace();
                        }
                    } else {
                        System.out.println("Fehler beim Einlesen!");
                    }
                    break;
                } else {
                    localLoginDataCollection = new String[0][3];
                    try {
                        data.file.createNewFile();
                    } catch (IOException var5) {
                        var5.printStackTrace();
                    }
                    data.sb.append("0");
                    data.sb.append("\n");
                    numberOfUsers = 0;

                    try {
                        OutputStream stream = new FileOutputStream(data.file);
                        String s = data.sb.toString();
                        stream.write(s.getBytes());
                        stream.close();
                    } catch (IOException var4) {
                        var4.printStackTrace();
                    }

                    break;
                }
            } else {
                data.folder.mkdirs();
            }
        }
    }

    public static void loginUser(){

        System.out.println("Please type in your username:");
        String userInput;

        userInput = scanner.nextLine();

        if(getLocalLogins(userInput) == true){
            login(userInput);
        }else{
            System.out.println("User does not exist in the Database. Saving "+userInput+" in our database as new User!");
            register(userInput);
        }
    }

    private static void register(String userInput){

        if(numberOfUsers>0) {
            overwriteTempDataCollection(userInput);
        } else {
            numberOfUsers = 1;
            tempDataCollection = new String[1][3];
            tempDataCollection[0][0] = userInput;
            tempDataCollection[0][1] = "NOR";
            tempDataCollection[0][2] = "0,1,2,3,4,5,6,7,8,9";
        }

        setLocalLogins(tempDataCollection);
        login(userInput);
    }

    private static void registerUser(){
        System.out.println("Please type in the username you want to use:");
        String userInput;

        while(true) {
            userInput = scanner.nextLine();

            if(numberOfUsers>0) {
                if (getLocalLogins(userInput) == false) {
                    overwriteTempDataCollection(userInput);

                    break;
                } else {
                    System.out.println("User already in our Database. Please use a different Name!");
                }
            } else {
                numberOfUsers = 1;
                tempDataCollection = new String[1][3];
                tempDataCollection[0][0] = userInput;
                tempDataCollection[0][1] = "NOR";
                tempDataCollection[0][2] = "0,1,2,3,4,5,6,7,8,9";

                break;
            }
        }
        setLocalLogins(tempDataCollection);
        login(userInput);
    }

    private static void overwriteTempDataCollection(String userInput) {
        tempDataCollection = new String[localLoginDataCollection.length + 1][3];
        int userIndexCurrent = 0;
        for (int userIndex = 0; userIndex < localLoginDataCollection.length; userIndex++) {
            tempDataCollection[userIndex][0] = localLoginDataCollection[userIndex][0];
            tempDataCollection[userIndex][1] = localLoginDataCollection[userIndex][1];
            tempDataCollection[userIndex][2] = localLoginDataCollection[userIndex][2];
            userIndexCurrent = userIndex;
        }
        userIndexCurrent++;
        tempDataCollection[userIndexCurrent][0] = userInput;
        tempDataCollection[userIndexCurrent][1] = "NOR";
        tempDataCollection[userIndexCurrent][2] = "0,1,2,3,4,5,6,7,8,9";
        numberOfUsers = userIndexCurrent+1;
    }

    private static boolean getLocalLogins(String user){

        boolean done = false;

        for(int xTimes=0; xTimes<localLoginDataCollection.length;xTimes++){
            if(localLoginDataCollection[xTimes][0].matches(user)){
                done=true;
            }
        }
        return done;
    }

    private static void setLocalLogins(String[][] dataCollection) {
        localLoginDataCollection = dataCollection;
        YoolooLoginData data = new YoolooLoginData();

        data.sb.append(numberOfUsers);
        data.sb.append("\n");

        for(int xTimes = 0; xTimes<numberOfUsers;xTimes++){
            data.sb.append(localLoginDataCollection[xTimes][0]);
            data.sb.append("\n");
            data.sb.append(localLoginDataCollection[xTimes][1]);
            data.sb.append("\n");
            data.sb.append(localLoginDataCollection[xTimes][2]);
            data.sb.append("\n");
        }

        try {
            OutputStream stream = new FileOutputStream(data.file);
            String s = data.sb.toString();
            stream.write(s.getBytes());
            stream.close();
            System.out.println("Daten gespeichert.");
        } catch (IOException var4) {
            var4.printStackTrace();
        }
    }

    private static void login(String userInput) {

        //YoolooClient yc = new YoolooClient();

        String name="";
        //String type="";

        for (int userIndex=0;userIndex<localLoginDataCollection.length;userIndex++){
            if(localLoginDataCollection[userIndex][0].matches(userInput)){
                if(hasStatus(userInput,"BAN")){
                    System.err.println("[VAC-Ban]: This user was flagged for cheating.");
                } else if(hasStatus(userInput,"LOG")){
                    System.err.println("[Failed to Connect]: This user is already logged in.");
                } else {
                    name = localLoginDataCollection[userIndex][0];
                    setActiveUser(name);
                    setStatus(name,"LOG");
                    couldLogIn=true;
                }

            }
        }
        //ys.setAktuelleSortierung(null);
        //yc.setSpielerName(name);
    }

    public static String getActiveUser() {
        return activeUser;
    }

    public static void setActiveUser(String activeUser) {
        YoolooLoginData.activeUser = activeUser;
    }

    public static int[] getCardOrder(String user) {

        String order = "0,1,2,3,4,5,6,7,8,9";



        for (int userIndex=0;userIndex<localLoginDataCollection.length;userIndex++){
            if(localLoginDataCollection[userIndex][0].matches(user)){
                order = localLoginDataCollection[userIndex][2];
            }
        }
        String[] cardOrderString = order.split(",");
        cardOrder = new int[cardOrderString.length];


        for(int xTimes=0;xTimes<cardOrderString.length;xTimes++){
            cardOrder[xTimes] = Integer.parseInt(cardOrderString[xTimes]);
        }

        return cardOrder;
    }

    public static void setCardOrder(Object[] cardOrder, String user) {

        String newOrder = "";

        for(int xTimes = 0; xTimes<cardOrder.length;xTimes++){
            newOrder = newOrder + cardOrder[xTimes] + ",";
        }
        newOrder = newOrder.substring(0,newOrder.length()-1);

        for(int xTimes = 0; xTimes<localLoginDataCollection.length;xTimes++){
            if(localLoginDataCollection[xTimes][0].matches(user)){
                localLoginDataCollection[xTimes][2] = newOrder;
            }
        }
        setLocalLogins(localLoginDataCollection);
    }

    public static boolean hasStatus(String user, String status) {
        //initializeCollection();
        boolean statusFound = false;

        for(int userIndex=0;userIndex<localLoginDataCollection.length;userIndex++){
         if(localLoginDataCollection[userIndex][0].matches(user)){
             if(localLoginDataCollection[userIndex][1].contains(status)){
                 statusFound = true;
             }
         }
        }
        return statusFound;
    }

    public static void resetLogStatus(){
        initializeCollection();

        String string, tempString="";
        String[] statusArray;
        for(int userIndex=0;userIndex<localLoginDataCollection.length;userIndex++){

            tempString="NOR";
            string = localLoginDataCollection[userIndex][1];

            if(string.contains("BAN")){
                tempString=tempString+",BAN";
            }
            if(string.contains("PRE")){
                tempString=tempString+",PRE";
            }

            localLoginDataCollection[userIndex][1] = tempString;
        }
        setLocalLogins(localLoginDataCollection);
    }

    public static void setStatus(String user, String status) {
        //initializeCollection();
        String string;

        String[] statusArray;
        for(int userIndex=0;userIndex<localLoginDataCollection.length;userIndex++){
            if(localLoginDataCollection[userIndex][0].matches(user)){
                string = localLoginDataCollection[userIndex][1];
                statusArray = string.split(",");
                boolean alreadyHasStatus = false;
                for(int statusIndex=0;statusIndex<statusArray.length;statusIndex++){
                    if(statusArray[statusIndex].matches(status)){
                        alreadyHasStatus = true;
                    }
                }
                if(alreadyHasStatus == false){
                    localLoginDataCollection[userIndex][1] = localLoginDataCollection[userIndex][1] + "," + status;
                }
                setLocalLogins(localLoginDataCollection);
            }
        }
    } //"PRE" "NOR" "BAN" "LOG"

}