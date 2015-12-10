package com.example;

import java.util.*;

/**
 * Created by Felix on 11/9/15.
 */
public class View {
    ArrayList viewPlayers;
    Iterator<Player> itPlayer;       //create iterator for Array List

    //setter methods

    //takes arraylist object and attaches it to the local one for use
    public void setPlayerList(ArrayList players){
        viewPlayers = players;
    }


    public void identify(){
        System.out.println("Im the View!");
    }

    //prints message to the console
    public void printToUser(String message){
        System.out.println(message);
    }

    //print player info
    public void printPlayersInfo(){
        //attach iterator object
        itPlayer = viewPlayers.iterator();
        while(itPlayer.hasNext()){

            //get player from iterator object
            Player tempPlayer = itPlayer.next();
            //print player info
            printToUser("Player name: \t" + tempPlayer.getName() + "\n");
            printToUser("Player rating: " + tempPlayer.getRating());
            printToUser("Player team: " + tempPlayer.getTeam() + "\n");

        }
    }

    //print teams
    public void printTeams(Team[] tArray, int teamCreation){
        printToUser("\nPrinting Teams:");
        //go through team list
        for(int cnt = 1; cnt < tArray.length; cnt++ ){
            if(teamCreation == 1){
                //Fair team creation, player rating was used
                printToUser("\nTEAM " + cnt + ": \nTeam Rating: " + tArray[cnt].getTeamRating() + "\n");
            }else{
                //Random team creation, no player rating was used
                printToUser("\nTEAM " + cnt + ": \n");
            }

            //send player array to be printed
            iterateThroughPlayerArray(tArray[cnt].getPlayerArray(), teamCreation);
        }

    }

    //iterates through an array of Players and prints out info
    private void iterateThroughPlayerArray(Player[] pArray, int teamCreationMethod){
        for(int cnt = 0; cnt < pArray.length; cnt++){
            if(pArray[cnt] != null){
                //print player info

                //If team creation Method =1 (Fair Teams), then rating was used and needs to be printed
                if(teamCreationMethod == 1){
                    printToUser("\t" + pArray[cnt].getName() + "(Rating: " + pArray[cnt].getRating() + ")");
                }else{
                    //Random team creation, no rating necessary
                    printToUser("\t " + pArray[cnt].getName());
                }
                //printToUser("\t " + pArray[cnt].getTeam() + "\n"); //testing
            }

        }
    }
}
