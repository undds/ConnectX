package cpsc2150.extendedConnectX.models;


import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(org.junit.runners.JUnit4.class)
public class TestGameBoardMem {

    private IGameBoard MakeaGB(int row, int col, int num) {
        IGameBoard d;
        d = new GameBoardMem(row, col, num);
        return d;
    }
    public String convert(char[][] a) {
        StringBuilder str = new StringBuilder();

        str.append("|0|"); //print first element
        for (int i = 1; i < a.length; i++){
            str.append(i + "|");
        }
        str.append("\n");

        for(int j = a.length-1; j >= 0; j--) { //PRINT TOP TO BOT
            for (int i = 0; i < a[0].length; i++) { // PRINT LEFT TO RIGHT
                str.append("|");
                if(a[j][i] == 0){
                    str.append(' ');
                }
                else
                    str.append(a[j][i]);
            }
            str.append("|\n");
        }
        return str.toString();
    }

    @Test
    public void testConstructorNorm() {
        IGameBoard gameBoard = MakeaGB(3,3,3);

        char[][] expected = new char[3][3];

        String str1, str2;
        str1 = gameBoard.toString();
        str2 = convert(expected);

        assertEquals(str1, str2);
    }

    @Test
    public void testConstructorHighRow() {
        IGameBoard gameBoard = MakeaGB(5,3,3);
        char[][] expected = new char[5][3];

        String str1, str2;
        str1 = gameBoard.toString();
        str2 = convert(expected);

        assertEquals(str1,str2);
    }
    @Test
    public void testConstructorHighCol() {
        IGameBoard gameBoard = MakeaGB(3,5,3);
        char[][] expected = new char[3][5];

        String str1, str2;
        str1 = gameBoard.toString();
        str2 = convert(expected);

        assertEquals(str1,str2);
    }

    @Test
    public void testIfFreeEmpty() {
        IGameBoard gameBoard = MakeaGB(3,3,3);
        assertTrue(gameBoard.checkIfFree(1));
    }

    @Test
    public void testIfFreeFull() {
        IGameBoard gameBoard = MakeaGB(3,3,3);
        gameBoard.placeToken('X', 0);
        gameBoard.placeToken('O', 0);
        gameBoard.placeToken('X', 0);
        assertFalse(gameBoard.checkIfFree(0));
    }

    @Test
    public void testIfFreeHalf() {
        IGameBoard gameBoard = MakeaGB(3,3,3);
        gameBoard.placeToken('X', 1);
        gameBoard.checkIfFree(1);
    }

    @Test
    public void testHorizWinRightStart() {
        IGameBoard gameBoard = MakeaGB(3,3,3);
        gameBoard.placeToken('X', 0); // X at (0,0)
        gameBoard.placeToken('X', 1); // X at (1,0)
        gameBoard.placeToken('X', 2); // X at (2,0)
        assertTrue(gameBoard.checkHorizWin(new BoardPosition(0, 2), 'X'));
    }
    @Test
    public void testHorizWinLoss() {
        IGameBoard gameBoard = MakeaGB(3,3,3);
        gameBoard.placeToken('X', 0); // X at (0,0)
        gameBoard.placeToken('X', 2); // X at (2,0)
        assertFalse(gameBoard.checkHorizWin(new BoardPosition(0, 2), 'X'));
    }

    @Test
    public void testHorizMiddleStart() {
        IGameBoard gameBoard = MakeaGB(3,3,3);
        gameBoard.placeToken('X', 0); // X at (0,0)
        gameBoard.placeToken('X', 1); // X at (1,0)
        gameBoard.placeToken('X', 2); // X at (2,0)
        assertTrue(gameBoard.checkHorizWin(new BoardPosition(0, 1), 'X'));
    }

    @Test
    public void testHorizLeftStart() {
        IGameBoard gameBoard = MakeaGB(3,3,3);
        gameBoard.placeToken('X', 0); // X at (0,0)
        gameBoard.placeToken('X', 1); // X at (1,0)
        gameBoard.placeToken('X', 2); // X at (2,0)
        assertTrue(gameBoard.checkHorizWin(new BoardPosition(0, 0), 'X'));
    }

    @Test
    public void testVertWinCap() {
        IGameBoard board = MakeaGB(3,3,3);
        board.placeToken('X', 0); // 0,0
        board.placeToken('X', 0); // 0,1
        board.placeToken('X', 0); // 0,2
        assertTrue(board.checkVertWin(new BoardPosition(2, 0), 'X'));
    }

    @Test
    public void testVertTooLittle() {
        IGameBoard board = MakeaGB(3,3,3);
        board.placeToken('X', 0); // 0,0
        board.placeToken('X', 0); // 0,1
        assertFalse(board.checkVertWin(new BoardPosition(1, 0), 'X'));
    }

    @Test
    public void testVertBotStart() {
        IGameBoard board = MakeaGB(3,3,3);
        board.placeToken('X', 0); // 0,0
        board.placeToken('X', 0); // 0,1
        board.placeToken('X', 0); // 0,2
        assertTrue(board.checkVertWin(new BoardPosition(0, 0), 'X'));
    }

    @Test
    public void testVertMidStart() {
        IGameBoard board = MakeaGB(3,3,3);
        board.placeToken('X', 0); // 0,0
        board.placeToken('X', 0); // 0,1
        board.placeToken('X', 0); // 0,2
        assertTrue(board.checkVertWin(new BoardPosition(1, 0), 'X'));
    }

    @Test
    public void testDiagTopRight(){
        IGameBoard board = MakeaGB(3,3,3);
        board.placeToken('X', 0); // 0,0
        board.placeToken('O', 1); // 1,0
        board.placeToken('X', 1); // 1,1
        board.placeToken('O', 2); // 2,0
        board.placeToken('O', 2); // 2,1
        board.placeToken('X', 2); // 2,2
        assertTrue(board.checkDiagWin(new BoardPosition(2, 2), 'X'));
    }

    @Test
    public void testDiagMidLeftBotTopRight(){
        IGameBoard board = MakeaGB(3,3,3);
        board.placeToken('X', 0); // 0,0
        board.placeToken('O', 1); // 1,0
        board.placeToken('X', 1); // 1,1
        board.placeToken('O', 2); // 2,0
        board.placeToken('O', 2); // 2,1
        board.placeToken('X', 2); // 2,2
        assertTrue(board.checkDiagWin(new BoardPosition(1, 1), 'X'));
    }

    @Test
    public void testDiagBotLeft(){
        IGameBoard board = MakeaGB(3,3,3);
        board.placeToken('X', 0); // 0,0
        board.placeToken('O', 1); // 1,0
        board.placeToken('X', 1); // 1,1
        board.placeToken('O', 2); // 2,0
        board.placeToken('O', 2); // 2,1
        board.placeToken('X', 2); // 2,2
        assertTrue(board.checkDiagWin(new BoardPosition(0, 0), 'X'));
    }

    @Test
    public void testDiagTopLeft(){
        IGameBoard board = MakeaGB(3,3,3);
        board.placeToken('X', 2); // 2,0
        board.placeToken('O', 1);
        board.placeToken('X', 1); // 1,1
        board.placeToken('O', 0);
        board.placeToken('O', 0);
        board.placeToken('X', 0); // 0,2
        assertTrue(board.checkDiagWin(new BoardPosition(2, 0), 'X'));
    }
    @Test
    public void testDiagMidTopLeftBotRight(){
        GameBoard board = new GameBoard(3, 3, 3);
        board.placeToken('X', 2); // 2,0
        board.placeToken('O', 1);
        board.placeToken('X', 1); // 1,1
        board.placeToken('O', 0);
        board.placeToken('O', 0);
        board.placeToken('X', 0); // 0,2
        assertTrue(board.checkDiagWin(new BoardPosition(1, 1), 'X'));
    }

    @Test
    public void testDiagBotRight(){
        GameBoard board = new GameBoard(3, 3, 3);
        board.placeToken('X', 2); // 2,0
        board.placeToken('O', 1);
        board.placeToken('X', 1); // 1,1
        board.placeToken('O', 0);
        board.placeToken('O', 0);
        board.placeToken('X', 0); // 0,2
        assertTrue(board.checkDiagWin(new BoardPosition(0, 2), 'X'));
    }
    @Test
    public void testDiagLoss(){
        GameBoard board = new GameBoard(3, 3, 3);
        board.placeToken('X', 2); // 2,0
        board.placeToken('O', 1);
        board.placeToken('X', 1); // 1,1
        board.placeToken('O', 0);
        board.placeToken('X', 0); // 0,1
        assertFalse(board.checkDiagWin(new BoardPosition(1, 0), 'X'));
    }

    @Test
    public void testTieEmpty(){
        GameBoard board = new GameBoard(3, 3, 3);
        assertFalse(board.checkTie());
    }

    @Test
    public void testTieFull(){
        IGameBoard board = MakeaGB(3,3,3);
        board.placeToken('X',0);
        board.placeToken('O', 0);
        board.placeToken('X', 0);
        board.placeToken('O', 1);
        board.placeToken('O', 1);
        board.placeToken('X',1);
        board.placeToken('X',2);
        board.placeToken('X', 2);
        board.placeToken('O',2);
        assertTrue(board.checkTie());
    }

    @Test
    public void testTieFullWin(){
        IGameBoard board = MakeaGB(3,3,3);
        board.placeToken('X',0);
        board.placeToken('O', 0);
        board.placeToken('X', 0);
        board.placeToken('O', 1);
        board.placeToken('O', 1);
        board.placeToken('X',1);
        board.placeToken('X',2);
        board.placeToken('X', 2);
        board.placeToken('X',2);
        if(!board.checkForWin(2))
            assertFalse(board.checkTie());
    }

    @Test
    public void testTieHalfBoard(){
        IGameBoard board = MakeaGB(3,3,3);
        board.placeToken('X',0);
        board.placeToken('O', 0);
        board.placeToken('X', 0);
        board.placeToken('O', 1);
        board.placeToken('O', 1);
        board.placeToken('X',1);
        assertTrue(board.checkTie());
    }

    @Test
    public void testWhatRoutine(){
        IGameBoard board = MakeaGB(3,3,3);
        board.placeToken('O', 0);
        board.placeToken('X', 0);
        board.placeToken('O', 1);
        board.placeToken('X', 1);
        board.placeToken('X',2);
        BoardPosition bp = new BoardPosition(1,0);

        assertTrue(board.whatsAtPos(bp) == 'X');
    }
    @Test
    public void testWhatEmpty() {
        GameBoard board = new GameBoard(3, 3, 3);
        BoardPosition bp = new BoardPosition(0, 0);

        assertTrue(board.whatsAtPos(bp) == 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testWhatOutOfBounds() {
        GameBoard board = new GameBoard(3, 3, 3);
        BoardPosition bp = new BoardPosition(4, 4);
        board.whatsAtPos(bp);
    }

    @Test
    public void testWhatSymbol(){
        IGameBoard board = MakeaGB(3,3,3);
        board.placeToken('#', 0);
        BoardPosition bp = new BoardPosition(0,0);

        assertTrue(board.whatsAtPos(bp) == '#');
    }
    @Test
    public void testWhatNumber(){
        IGameBoard board = MakeaGB(3,3,3);
        board.placeToken('1', 0);
        BoardPosition bp = new BoardPosition(0,0);

        assertTrue(board.whatsAtPos(bp) == '1');
    }


    @Test
    public void testPlayerRightToken(){
        IGameBoard board = MakeaGB(3,3,3);
        board.placeToken('O', 0);
        board.placeToken('X', 0);
        board.placeToken('O', 1);
        board.placeToken('X', 1);
        board.placeToken('X',2);
        BoardPosition bp = new BoardPosition(1,0);

        assertTrue(board.isPlayerAtPos(bp, 'X'));
    }

    @Test
    public void testPlayerWrongToken(){
        IGameBoard board = MakeaGB(3,3,3);
        board.placeToken('O', 0);
        board.placeToken('X', 0);
        board.placeToken('O', 1);
        board.placeToken('X', 1);
        board.placeToken('X',2);
        BoardPosition bp = new BoardPosition(1,0);

        assertFalse(board.isPlayerAtPos(bp, 'O'));
    }
    @Test
    public void testPlayerMissRow(){
        IGameBoard board = MakeaGB(3,3,3);
        board.placeToken('O', 0);
        board.placeToken('X', 0);
        board.placeToken('O', 1);
        board.placeToken('X', 1);
        board.placeToken('X',2);
        BoardPosition bp = new BoardPosition(2,0);

        assertFalse(board.isPlayerAtPos(bp, 'X'));
    }

    @Test
    public void testPlayerMissColumn(){
        IGameBoard board = MakeaGB(3,3,3);
        board.placeToken('O', 0);
        board.placeToken('X', 0);
        board.placeToken('X', 1);
        BoardPosition bp = new BoardPosition(0,2);

        assertFalse(board.isPlayerAtPos(bp, 'X'));
    }

    @Test
    public void testPlayerSurrounded(){
        IGameBoard board = MakeaGB(3,3,3);
        board.placeToken('O', 0);
        board.placeToken('X', 0);
        board.placeToken('X', 1);
        board.placeToken('O',1);
        board.placeToken('X',1);
        board.placeToken('O',2);
        board.placeToken('X',2);
        BoardPosition bp = new BoardPosition(1,1);

        assertTrue(board.isPlayerAtPos(bp, 'O'));
    }

    @Test
    public void testPlaceHalf(){
        char expected[][] = new char[3][3];
        expected[0][0] = 'O';
        expected[0][1] = 'X';
        expected[1][0] = 'O';
        expected[1][1] = 'X';
        expected[2][0] = 'X';

        IGameBoard board = MakeaGB(3,3,3);
        board.placeToken('O', 0);
        board.placeToken('X', 0);
        board.placeToken('O', 1);
        board.placeToken('X', 1);
        board.placeToken('X',2);

        assertEquals(board.toString(), convert(expected));
    }

    @Test
    public void testPlaceEmpty(){
        char expected[][] = new char[3][3];
        expected [0][0] = 'X';

        IGameBoard board = MakeaGB(3,3,3);
        board.placeToken('X',0);

        assertEquals(board.toString(), convert(expected));
    }
    @Test
    public void testPlaceFull(){
        char expected[][] = new char[3][3];
        expected[0][0] = 'X';
        expected[0][1] = 'O';
        expected[0][2] = 'X';

        IGameBoard board = MakeaGB(3,3,3);
        board.placeToken('X', 0);
        board.placeToken('O', 0);
        board.placeToken('X', 0);

        assertEquals(board.toString(), convert(expected));
    }
    @Test
    public void testPlaceDifferentPlayer(){
        char expected[][] = new char[3][3];
        expected[0][0] = 'X';
        expected[0][1] = 'O';

        IGameBoard board = MakeaGB(3,3,3);
        board.placeToken('X', 0);
        board.placeToken('O', 0);

        assertEquals(board.toString(), convert(expected));
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testPlaceOutOfBounds(){
        IGameBoard board = MakeaGB(3,3,3);
        board.placeToken('X', 4);

    }
}
