package cpsc2150.extendedConnectX.controllers;

import cpsc2150.extendedConnectX.models.*;
import cpsc2150.extendedConnectX.views.*;

import java.util.ArrayList;

/**
 * The controller class will handle communication between our View and our Model ({@link IGameBoard})
 * <p>
 * This is where you will write code
 * <p>
 * You will need to include your {@link BoardPosition} class, {@link IGameBoard} interface
 * and both of the {@link IGameBoard} implementations from Project 4.
 * If your code was correct you will not need to make any changes to your {@link IGameBoard} implementation class.
 *
 * @version 2.0
 */
public class ConnectXController {

    /**
     * <p>
     * The current game that is being played
     * </p>
     */
    private IGameBoard curGame;

    /**
     * <p>
     * The screen that provides our view
     * </p>
     */
    private ConnectXView screen;

    /**
     * <p>
     * Constant for the maximum number of players.
     * </p>
     */
    public static final int MAX_PLAYERS = 10;
    
    /**
     * <p>
     * The number of players for this game. Note that our player tokens are hard coded.
     * </p>
     */
    private int numPlayers;

    char[] playerList = new char[]{'X', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};
    char players[] = new char[MAX_PLAYERS];
    int roundNum = 0;
    boolean again = false;

    /**
     * <p>
     * This creates a controller for running the Extended ConnectX game
     * </p>
     * 
     * @param model
     *      The board implementation
     * @param view
     *      The screen that is shown
     * @param np
     *      The number of players for this game.
     * 
     * @post [ the controller will respond to actions on the view using the model. ]
     */
    public ConnectXController(IGameBoard model, ConnectXView view, int np) {
        this.curGame = model;
        this.screen = view;
        this.numPlayers = np;
        for (int i = 0; i < np; i++){
            players[i] = playerList[i];
        }
    }

    /**
     * <p>
     * This processes a button click from the view.
     * </p>
     * 
     * @param col 
     *      The column of the activated button
     * 
     * @post [ will allow the player to place a token in the column if it is not full, otherwise it will display an error
     * and allow them to pick again. Will check for a win as well. If a player wins it will allow for them to play another
     * game hitting any button ]
     */
    public void processButtonClick(int col) {
        if(again){
            newGame();
        }
        again = false;

        String message;

        if(!curGame.checkIfFree(col)){
            message = "COLUMN NOT FREE";
            screen.setMessage(message);
        }
        else{
            int row = 0;
            BoardPosition bp = new BoardPosition(row, col);

            while (curGame.whatsAtPos(bp) != 0){
                row++;
                bp = new BoardPosition(row, col);
            }


            curGame.placeToken(players[roundNum], col);

            screen.setMarker(row , col, players[roundNum]);

            if(curGame.checkForWin(col)){
                message = "Player " + players[roundNum] + " Won! Click again to start new game";
                screen.setMessage(message);
                again = true;
            }
            else if(curGame.checkTie()){
                message = "TIE. Click again to start new game.";
                screen.setMessage(message);
                again = true;
            }
            else{
                roundNum++;
                roundNum %= numPlayers;
                screen.setMessage("It is " + players[roundNum] + "'s turn.");
            }
        }
    }

    /**
     * <p>
     * This method will start a new game by returning to the setup screen and controller
     * </p>
     * 
     * @post [ a new game gets started ]
     */
    private void newGame() {
        //close the current screen
        screen.dispose();
        
        //start back at the set up menu
        SetupView screen = new SetupView();
        SetupController controller = new SetupController(screen);
        screen.registerObserver(controller);
    }
}