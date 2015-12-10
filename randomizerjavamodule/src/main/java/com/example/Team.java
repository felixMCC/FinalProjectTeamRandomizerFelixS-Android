package com.example;

/**
 * Created by Felix on 11/16/15.
 */
public class Team {
    private int teamNumber = 0;             //team number
    private int fitPlayersEvenly = 0;       //How many players can fit evenly into this team
    private int currentNumberOfPlayers = 0; //how many players are inside this team right now
    private int teamRating = 0;             //Sum of all player's skill ratings
    private Player[] playerArray;           //holds player objects

    //creates the team, starting with team number then the array to hold the players
    Team(int tNum, int pArrayLength, int playersEvenly){
        teamNumber = tNum;
        playerArray = new Player[pArrayLength];
        fitPlayersEvenly = playersEvenly;
    }

    //getter methods

    public int getTeamNumber(){
        return teamNumber;
    }

    public Player[] getPlayerArray(){
        return playerArray;
    }

    public int getFitPlayersEvenly(){
        return fitPlayersEvenly;
    }

    public int getCurrentNumberOfPlayers(){
        return currentNumberOfPlayers;
    }

    public int getTeamRating(){ return teamRating;}

    //setter methods

    public void setFitPlayersEvenly(int number){
        fitPlayersEvenly = number;
    }

    //increments the number of players this team has
    public void incrementNumberOfPlayers(){
        currentNumberOfPlayers += 1;
    }

    //add player to this team
    public void addPlayerToTeam(Player plyr){
        playerArray[getCurrentNumberOfPlayers()] = plyr;
    }

    //adds all player's ratings toget to form a total team rating
    public void calcTeamRating(){
        int tempTeamRating = 0;
        for(int cnt = 0; cnt < playerArray.length; cnt++){
            if(playerArray[cnt] != null){
                tempTeamRating += playerArray[cnt].getRating();
            }
        }
        //set teams total skill level rating
        teamRating = tempTeamRating;
    }
}
