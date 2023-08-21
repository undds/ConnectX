package cpsc2150.extendedConnectX.models;

public class BoardPosition {
    private int Row;
    private int Column;

    /**
     * @param r row
     * @param c column
     * @return BoardPosition object with specificed ordered pair coordinates
     *
     * @pre 0 <= r <= 7
     *      0 <= c <= 9
     * @post Row = r AND Column = c
     */
    public BoardPosition(int r, int c){
        Column = c;
        Row = r;
    }

    @Override
    public boolean equals(Object b1){
        BoardPosition test = (BoardPosition) b1;
        if (this.Row == test.Row && this.Column == test.Column)
            return true;
        else
            return false;
    }
    /**
     * @return BoardPosition object's coordinate pair
     * @pre pos is contained within (0,0) to (8,6), inclusive
     * @post toString = (pos.getColumn(), pos.getRow())
     **/
    @Override
    public String toString(){
        return Row + "," + Column;
    }


    /**
     * returns row number
     *
     * @return Row
     *
     * @post Row = #Row
     */
    public int getRow(){ return Row; }

    /**
     * returns column number
     *
     * @return Column
     *
     * @post Column = #Column
     */
    public int getColumn(){
        return Column;
    }
}
