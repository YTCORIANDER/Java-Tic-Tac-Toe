/************************************************************************
* Configurations.java
* This program demostrates computerPlay described
* Determine who win in titactoe through check all rows, cols, diagonals
* HashDictionary.java interface to this file
*************************************************************************/

public class Configurations {
    private int boardSize;
    private int lengthToWin;
    private int maxLevels;
    private char[][] board;

    // constractor
    public Configurations (int boardSize, int lengthToWin, int maxLevels){
        this.boardSize = boardSize;
        this.lengthToWin = lengthToWin;
        this.maxLevels = maxLevels;

        // create board
        board = new char[boardSize][boardSize];

        // initial the board to space
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = ' ';
            }
        }
    }
    
    // return empty hashdictionary 6379
    public HashDictionary createDictionary(){
        return new HashDictionary(boardSize);
    }

    // check board config is exists or not
    public int repeatedConfiguration(HashDictionary hashTable){
        String string = toString();
        if(hashTable.get(string) != 0){
            return hashTable.get(string);
        }
        return -1;  
    }

    // add board config to hash table
    public void addConfiguration(HashDictionary hashTable, int score) {
        String boardConfig = toString();
        Data record = new Data(boardConfig, score);
        hashTable.put(record);
    }
    
    // save row col to board in symbol
    public void savePlay(int row, int col, char symbol){
        symbol = board[row][col];
    }

    // check board is null or not
    public boolean squareIsEmpty (int row, int col){
        return (board[row][col] == 0);
    }

    public boolean wins(char symbol) {
        //using like flag check exist won the game
        int won;
    
        // Check horizontal board
        int i = 0;
        while (i < boardSize) {
            won = 0;
            int j = 0;
            while (j < boardSize) {
                if (board[i][j] == symbol) {
                    won++;
                    if (won == lengthToWin) {
                        return true;
                    }
                }
                j++;
            }
            i++;
        }
    
        // Check vertical board
        int j = 0;
        while (j < boardSize) {
            won = 0;
            i = 0;
            while (i < boardSize) {
                if (board[i][j] == symbol) {
                    won++;
                    if (won == lengthToWin) {
                        return true;
                    }
                }
                i++;
            }
            j++;
        }
    
        // Check top-left to bottom-right diagonal
        i = 0;
        while (i <= boardSize - lengthToWin) {
            j = 0;
            while (j <= boardSize - lengthToWin) {
                won = 0;
                int k = 0;
                while (k < lengthToWin) {
                    if (board[i + k][j + k] == symbol) {
                        won++;
                        if (won == lengthToWin) {
                            return true;
                        }
                    }
                    k++;
                }
                j++;
            }
            i++;
        }
    
        // Check top-right to bottom-left diagonal
        i = 0;
        while (i <= boardSize - lengthToWin) {
            j = lengthToWin - 1;
            while (j < boardSize) {
                won = 0;
                int k = 0;
                while (k < lengthToWin) {
                    if (board[i + k][j - k] == symbol) {
                        won++;
                        if (won == lengthToWin) {
                            return true;
                        }
                    }
                    k++;
                }
                j++;
            }
            i++;
        }
        return false;
    }
            

    // check if draw then renturn true
    public boolean isDraw(){
        int i = 0, j = 0;
        while (i < boardSize){
            i++;
            while(j < boardSize){
                j++;
                if(board[i][j] != 0){
                    return true;
                }
            }
        }
        return false;
    }

    // check win status
    public int evalBoard(){
        if (wins('O')){
            return 3;
        }
        else if(wins('X')){
            return 0;
        }
        else if(isDraw()){
            return 2;
        }
        return 1;
    }
    
    // change the board to string
    public String toString(){
        StringBuilder output = new StringBuilder();
            for(int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    output.append(board[i][j]).append(" ");
                }
            }
        return output.toString();
    }
}
