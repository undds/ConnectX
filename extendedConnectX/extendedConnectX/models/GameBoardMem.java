package cpsc2150.extendedConnectX.models;

import java.util.*;

/**
 *
 * @author Frank Yang
 *
 * @invariant 0 {@code <=} Column {@code <} 100 AND  0 {@code <=} Row {@code <=} 100
 *
 * @correspondences self = GameBoard[0...Columns][0...Rows]
 */
public class GameBoardMem extends AbsGameBoard implements IGameBoard {
    private int Rows;
    private int Columns;
    private int numToWin;
    private Map<Character, List<BoardPosition>> myMap = new HashMap<>();
    public GameBoardMem(int row, int col, int num){
        Rows = row;
        Columns = col;
        numToWin = num;
    }

    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player){
        //check key of player
        List<BoardPosition> values;
        values = myMap.get(player);
        //if pos is in the list of the player, return true
        return values.contains(pos);
    }
    /**
     * Returns what is in the GameBoard at position pos
     *
     * @param pos The position in question
     * @return A player's marker. If there is no marker, return a blank space character
     * @pre [pos is contained within (0,0) to (Columns, Rows), inclusive
     * @post (whatsAtPos = p) iff (p != null), else whatsAtPos = <> AND pos = #pos
     */
    public char whatsAtPos(BoardPosition pos) {
        char marker = 0;
        for (Map.Entry<Character, List<BoardPosition>> entry : myMap.entrySet()) {
            List<BoardPosition> positions = entry.getValue();
            if (positions.contains(pos)) {
                marker = entry.getKey();
                break;
            }
        }
        return marker;
    }

    /**
     * Place the player's marker in a specific column. The marker will be placed on the lowest available row.
     * @param p player's character
     * @param c column number
     *
     * @pre p = [any character] AND 0 <= c < Columns
     * @post p = #p AND c = #c
     */
    public void placeToken(char p, int c) {
        if(c >= getNumColumns()) throw new IndexOutOfBoundsException();

        int lowest = 0;
        BoardPosition bp = new BoardPosition(lowest, c);
        List<BoardPosition> values = new ArrayList<>();

        if(myMap.containsKey(p)) {
            values = myMap.get(p);
        }

        char thing = whatsAtPos(bp);
        while(thing != 0){
            lowest++;
            bp = new BoardPosition(lowest, c);
            thing = whatsAtPos(bp);
        }

        values.add(bp);
        myMap.put(p, values);
    }

    public int getNumRows(){return Rows;}
    public int getNumColumns(){return Columns;}
    public int getNumtoWin(){return numToWin;}
}
