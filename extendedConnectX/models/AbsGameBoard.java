package cpsc2150.extendedConnectX.models;

public abstract class AbsGameBoard implements IGameBoard{
    /**
     * Prints the whole gameboard. Each coordinate pair is filled with the appropriate marker or blank space
     *
     * @return whole gameboard filled with X's, O's, and blank spaces
     * @pre pos is contained within (0,0) to (8,6), inclusive
     * @post toString = [picture of gameboard]
     **/
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("|0|"); //print first element
        for (int i = 1; i < getNumColumns(); i++){
            str.append(i + "|");
        }
        str.append("\n");

        for(int j = getNumRows()-1; j >= 0; j--) { //PRINT TOP TO BOT
            for (int i = 0; i < getNumColumns(); i++) { // PRINT LEFT TO RIGHT
                str.append("|");
                BoardPosition bp = new BoardPosition(j, i);
                str.append(whatsAtPos(bp));
            }
            str.append("|\n");
        }
        return str.toString();
    }
}
