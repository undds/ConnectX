package cpsc2150.extendedConnectX.models;

public interface IGameBoard {
    public void placeToken(char p, int c) throws IndexOutOfBoundsException;

    public char whatsAtPos(BoardPosition pos);


    /**
     * Returns true if the column can accept another token; false otherwise.
     *
     * @param c The specific column
     * @return True if column can accept another token, else False.
     *
     * @pre  0 <= c < Columns
     * @post c = #c
     */
    default boolean checkIfFree(int c){
        //goto column c and check if uppermost BoardPosition is initialized or not
        BoardPosition bp = new BoardPosition(getNumRows() - 1, c);
        if (whatsAtPos(bp) == 0 || whatsAtPos(bp) == ' ')
            return true;
        else
            return false;
    }

    /**
     * Check to see if last placed token resulted in 5 in a row horizontally.
     *
     * @param pos The position of last placed token
     * @param p   The player's marker
     *
     * @return True if last placed token results in a horizontal win, else False
     *
     * @pre p = (X OR O) AND [pos is contained within (0,0) to (8,6), inclusive
     * @post checkHorizWin iff [5 tokens are horizontally placed in a row] AND pos = #pos AND p = #p
     */
    default boolean checkHorizWin(BoardPosition pos, char p){
        int count = 0;
        BoardPosition bp, consec;
        //check left side
        int i = 1;
        while(i < getNumtoWin()){
            int column = pos.getColumn();
            column -= i;
            if(column < 0) {break;}
            bp = new BoardPosition(pos.getRow(), column);
            consec = new BoardPosition(pos.getRow(), column+1);
            i++;
            if(whatsAtPos(bp) == p  && whatsAtPos(consec) == p)  //SECOND HALF CHECKS FOR CONSECTUVITIY
                count++;
        }
        //check right side
        i=1;
        while(i < getNumtoWin()){
            int column = pos.getColumn();
            column += i;
            if(column >= getNumColumns()) {break;}
            bp = new BoardPosition(pos.getRow(), column);
            consec = new BoardPosition(pos.getRow(), column-1);

            i++;
            if(whatsAtPos(bp) == p  && whatsAtPos(consec) == p)  //SECOND HALF CHECKS FOR CONSECTUVITIY
                count++;
        }

        return count >= getNumtoWin() - 1; //-1 accounts for the initial marker
    }


    /**
     * Check if last placed token resulted in 5 in a row vertically.
     *
     * @param pos The position
     * @param p The player
     *
     * @return True if last placed token results in a vertical win, else False.
     *
     * @pre p = (X OR O) AND [pos is contained within (0,0) to (8,6), inclusive
     * @post checkVertWin iff [5 tokens are vertically placed in a row] AND pos = #pos AND p = #p
     */
    default boolean checkVertWin(BoardPosition pos, char p){
        int count = 0;
        BoardPosition bp, consec;

        //check below
        int i = 1;
        while(i < getNumtoWin()){
            int row = pos.getRow();
            row -= i;
            if(row < 0) {break;}
            bp = new BoardPosition(row, pos.getColumn());
            consec = new BoardPosition(row + 1, pos.getColumn());
            i++;
            if(whatsAtPos(bp) == p  && whatsAtPos(consec) == p)  //SECOND HALF CHECKS FOR CONSECTUVITIY
                count++;
        }

        //check above
        i = 1;
        while(i < getNumtoWin()){
            int row = pos.getRow();
            row += i;
            if(row >= getNumRows()) {break;}
            bp = new BoardPosition(row, pos.getColumn());
            consec = new BoardPosition(row - 1, pos.getColumn());
            i++;
            if(whatsAtPos(bp) == p  && whatsAtPos(consec) == p)  //SECOND HALF CHECKS FOR CONSECTUVITIY
                count++;
        }

        //minus one because of the initial marker
        return count >= getNumtoWin() - 1;
    }


    /**
     *  checks to see if the last token placed (which was placed in
     *  position pos by player p) resulted in 5 in a row diagonally.
     *
     * @param pos The position
     * @param p The player
     * @return Whether or not the last token was a winning diagonal move
     *
     * @pre p = (X OR O) AND [pos is contained within (0,0) to (8,6), inclusive
     * @post checkDiagWin iff [5 tokens are diagonally placed in a row] AND pos = #pos AND p = #p
     */
    default boolean checkDiagWin(BoardPosition pos, char p){
        int initialHeight = pos.getRow();
        int count = 0;
        BoardPosition bp, consec;
        //check for / win
        //check left side
        int i = 1;
        while(i < getNumtoWin()){
            int column = pos.getColumn();
            int row = pos.getRow();
            column -= i;
            row -= i;
            if(column < 0) {break;}
            if(row < 0) {break;}
            bp = new BoardPosition(row, column);
            consec = new BoardPosition(row+1, column+1);
            i++;
            if(whatsAtPos(bp) == p  && whatsAtPos(consec) == p)  //SECOND HALF CHECKS FOR CONSECTUVITIY
                count++;
        }
        i=1;
        while(i < getNumtoWin()){
            int column = pos.getColumn();
            int row = pos.getRow();
            column += i;
            row += i;
            if(column >= getNumColumns()) {break;}
            if(row >= getNumRows()) {break;}
            bp = new BoardPosition(row, column);
            consec = new BoardPosition(row-1, column-1);
            i++;
            if(whatsAtPos(bp) == p  && whatsAtPos(consec) == p)  //SECOND HALF CHECKS FOR CONSECTUVITIY
                count++;
        }
        if(count >= getNumtoWin()-1)
            return true;

        //check for \ win
        //reset count
        count = 0;
        //check left side

        i=1;
        while(i < getNumtoWin()){
            int column = pos.getColumn();
            int row = pos.getRow();
            column -= i;
            row += i;
            if(column < 0) {break;}
            if(row >= getNumRows()) {break;}
            bp = new BoardPosition(row, column);
            consec = new BoardPosition(row-1, column+1);
            i++;
            if(whatsAtPos(bp) == p  && whatsAtPos(consec) == p)  //SECOND HALF CHECKS FOR CONSECTUVITIY
                count++;
        }


        //check right side
        i=1;
        while(i < getNumtoWin()){
            int column = pos.getColumn();
            int row = pos.getRow();
            column += i;
            row -= i;
            if(column >= getNumColumns()) {break;}
            if(row < 0) {break;}
            bp = new BoardPosition(row, column);
            consec = new BoardPosition(row+1, column-1);
            i++;
            if(whatsAtPos(bp) == p  && whatsAtPos(consec) == p)  //SECOND HALF CHECKS FOR CONSECTUVITIY
                count++;
        }


        return count >= getNumtoWin() - 1;
    }


    /**
     * Check to see if the game has resulted in a tie.
     *
     * @return True if tied, else False
     *
     * @post checkTie iff [no available spaces left]
     */
    default boolean checkTie(){
        //should call checkWin before calling this

        int count = 0;
        BoardPosition bp;
        for(int i = 0; i < getNumColumns(); i++){
            bp = new BoardPosition(getNumRows() - 1, i);
            if(whatsAtPos(bp) != 0)
                count++;
        }
        return count == getNumColumns();
    }
    /**
     * Check if last placed token was a winning move
     *
     * @param c column number
     * @return true IFF [last placed token was a winning move] ELSE false
     *
     * @pre 0 <= c < 9
     * @post c = #c
     */

    default boolean checkForWin(int c){
        //convert c into the board position
        int i = 0;
        BoardPosition bp = new BoardPosition(i, c);
        while(i < getNumRows() && whatsAtPos(bp) != 0){ //check from bottom-up. if marker at pos, move up one row
            i++;
            if(i < getNumRows()) {
                bp = new BoardPosition(i, c);
            }
        }
        if(i == 0) { // column is full, cannot place marker here
            return false;
        }
        i--; //the while loop checks until first empty space, but we want the filled space before the empty.

        bp = new BoardPosition(i,c);
        char mark = whatsAtPos(bp);

        return checkHorizWin(bp, mark) || checkDiagWin(bp, mark) || checkVertWin(bp, mark);
    }


    /**
     *  Returns true if the player is at pos; otherwise, it returns false
     *
     * @param pos The position in question
     * @param player The player
     * @return True if player is at pos, otherwise return false
     *
     * @pre player = (X OR O) AND [pos is contained within (0,0) to (8,6), inclusive]
     * @post isPlayerAtPos iff [whatsAtPos = player's marker] AND pos = #pos AND player = #player
     */
    default boolean isPlayerAtPos(BoardPosition pos, char player){
        return whatsAtPos(pos) == player;
    }

    /**
     * Returns the number of Rows
     * @return number of Rows
     */
    public int getNumRows();

    /**
     * Returns the number of columns
     * @return number of columns
     */
    public int getNumColumns();

    /**
     * Returns number of tokens in a row needed to win
     * @return number of consecutive tokens required for win
     */
    public int getNumtoWin();

}
