package cpsc2150.extendedConnectX.models;

import java.util.Arrays;

/**
 *
 * @author Frank Yang
 *
 * @invariant 0 {@code <=} Column {@code <} 100 AND  0 {@code <=} Row {@code <=} 100
 *
 * @correspondences self = GameBoard[0...Columns][0...Rows]
 */
public class GameBoard extends AbsGameBoard implements IGameBoard {
    private int Rows;
    private int Columns;
    private int numToWin;

    private char GB[][];

    public GameBoard(int row, int col, int numWin){
        Rows = row;
        Columns = col;
        numToWin = numWin;
        GB = new char[Rows][Columns];
    }
    public int getNumRows(){return Rows;}
    public int getNumColumns(){return Columns;}
    public int getNumtoWin(){return numToWin;}

    /**
     * Place the player's marker in a specific column. The marker will be placed on the lowest available row.
     * @param p player's character
     * @param c column number
     *
     * @pre p = [any character] AND 0 <= c < Columns
     * @post p = #p AND c = #c
     */
    public void placeToken(char p, int c)
    {
        int i = 0;
        while(GB[c][i] != 0){ //check from bottom-up. if marker at pos, move up one row
            i++;
        }
        GB[c][i] = p;
    }
    /**
     * Returns what is in the GameBoard at position pos
     *
     * @param pos The position in question
     * @return A player's marker. If there is no marker, return a blank space character
     * @pre [pos is contained within (Rows,Columns) , inclusive
     * @post (whatsAtPos = p) iff (p = [any character], else whatsAtPos = <> AND pos = #pos
     */
    public char whatsAtPos(BoardPosition pos) {
        if(pos.getRow() >= getNumRows())
            return ' ';
        if (pos.getColumn() >= getNumColumns())
            return ' ';
        char thing = GB[pos.getColumn()][pos.getRow()];
        return thing;
    }
}
