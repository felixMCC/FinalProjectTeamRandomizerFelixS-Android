package com.example;

/**
 * Created by Felix on 11/9/15.
 */
public class Control {
    //MVC objects for paradigm
    Model theModel = new Model();
    View theView = new View();

    //main program
    public void runProgram(){
        //introduction
        theView.printToUser("Java For Android - Final Project\nTeam Randomizer\nBy: Nestor (Felix) Sotres");
        theView.printToUser("\nWelcome! This program helps you create teams from a group of people.\nYou can choose to create teams via Full Random Method or Fair Teams. ");
        theView.printToUser("Full Random: Simply enter the players info and number of teams. The Team Randomizer will randomly generate teams for you.");
        theView.printToUser("Fair Teams: Allows you to enter a rating (skill level) for each player before creating teams. Team Randomizer will attempt to create teams with similar skill levels.\n");

        //main program loop
        do{
            //prompt for team creation options 1 = fair teams random 2 = random teams
            promptTeamCreationMethod();

            //prompt user for total number of players
            promptForTotalNumberPlayers();


            //create players
            for(int cnt = 0; cnt < theModel.getNumberOfPlayers(); cnt++){
                //Check if team creation is "Fair Teams" or "Random Teams"
                if(theModel.getTeamCreationMethod() == 1){
                    //Fair Teams require player skill rating
                    createPlayerWithRating();
                }else{
                    //Random Teams DONT require skill rating
                    createPlayerNoRating();
                }

            }

            //print player info (Testing)
            //printPlayerInfo();

            //prompt for number of teams
            createNumberOfTeams();

            //Once all teams are created, calculate each team's cumulative skill level
            theModel.calculateAllTeamSkillLevels();

            //print teams
            theView.printTeams(theModel.getTeamsArray(), theModel.getTeamCreationMethod());
            theView.printToUser("\nWould you like to create brand new teams?\n(Y = Yes / N = No)");
        }while(theModel.checkYesNo());

        theView.printToUser("Thank you for using Team Randomizer. Have a nice day!");
        System.exit(0);
    }

    //prompts user for total number of players
    private void promptForTotalNumberPlayers(){
        theView.printToUser("How many players will be playing?");
        try {
            //get the number of players from the user
            int tempTotalPlayers = Integer.parseInt(theModel.getUserInput());
            if(tempTotalPlayers > 1) {
                //set the total number of players in the model
                theModel.setNumberOfPlayers(tempTotalPlayers);
            }else{
                theView.printToUser("Please check entry and try again.");
                promptForTotalNumberPlayers();
            }

        }catch(NumberFormatException e){
            //error parsing string to int, try again
            theView.printToUser("Please check entry and try again.");
            promptForTotalNumberPlayers();
        }
    }

    //prompts user for player info and creates player objects
    private void createPlayerWithRating(){
        try{
            //temp variables
            String tempName = "";
            int tempRating = 0;
            theView.printToUser("Please enter player's name:");
            tempName = theModel.getUserInput();
            theView.printToUser("Please enter player's rating 1-5:\n(1 = novice, 5 = expert)");
            tempRating = Integer.parseInt(theModel.getUserInput());
            //create player object and store within the model
            theModel.createAndStorePlayer(tempName, tempRating);
        }catch(NumberFormatException e){
            //error parsing string to int, try again
            theView.printToUser("Please check entry and try again.");
            createPlayerWithRating();
        }
    }

    //prompts user for player info and creates player objects
    private void createPlayerNoRating(){
        try{
            //temp variables
            String tempName = "";
            theView.printToUser("Please enter player's name:");
            tempName = theModel.getUserInput();
            //create player object and store within the model
            theModel.createAndStorePlayer(tempName, 1);
        }catch(NumberFormatException e){
            //error parsing string to int, try again
            theView.printToUser("Please check entry and try again.");
            createPlayerNoRating();
        }
    }

    //creates teams
    private void createNumberOfTeams(){
        //prompt user for number of teams
        promptForNumberOfTeams();

        //create teams
        theModel.createTeams();

    }

    //prompt user for the number of teams and validate values
    private void promptForNumberOfTeams(){
        //ask for number of players
        theView.printToUser("How many teams need to be created?");
        String tempStringTeams = theModel.getUserInput();   //store number of teams from user
        //send value to be verified
        if (theModel.validateTeams(tempStringTeams) && theModel.verifyInteger(tempStringTeams) > 1){
            //number of teams is valid, do nothing (number of teams has been set inside the model)
        }else{
            //invalid number of teams, try again
            theView.printToUser("Invalid number of teams, please try again.");
            promptForNumberOfTeams();
        }
    }

    //prompt user for a way to create teams
    private void promptTeamCreationMethod(){
        theView.printToUser("How would you like to create teams?\n1 = Fair Teams, 2 = Full Random\n");
        String tempStringMethod = theModel.getUserInput();    //get user input
        //verity for valid integer
        if(theModel.verifyInteger(tempStringMethod) > 0){
            //store valid int in temp value to figure out choice
            int tempIntMethod = theModel.verifyInteger(tempStringMethod);
            //make sure user enters valid choice
            if(tempIntMethod == 1 || tempIntMethod == 2 ){
                //set method for creating teams 1 = full random 2 = fair teams
                theModel.setTeamCreationMethod(tempIntMethod);
            }else{
                //try again
                theView.printToUser("Invalid option, please try again.");
                promptTeamCreationMethod();
            }

        }else{
            theView.printToUser("Invalid option, please try again.");
            promptTeamCreationMethod();
        }

    }
    //print player info
    private void printPlayerInfo(){
        theView.printToUser("\nPrinting player info: \n");
        //pass the player list from the model to the view for printing
        theView.setPlayerList(theModel.getPlayerList());
        //tell the view to print the list
        theView.printPlayersInfo();
    }

}




