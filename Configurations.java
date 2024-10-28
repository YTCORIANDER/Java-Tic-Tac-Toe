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
        // extend new board
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

    // check all rows, cols, diagonals
    public boolean wins(char symbol) {  
        int i=0, j=0;
        // check horizontal board
        while(i<boardSize){
            i++;
            while(j<boardSize){
                j++;
                return board[i][j] == symbol;
            }
        }

        // check vertical board
        while(j<boardSize){
            i++;
            while(i<boardSize){
                j++;
                return board[i][j] == symbol;
            }
        }

        // check diagonal top left to bottom right
        while(j<boardSize){
            j++;
            return board[j][j] == symbol;
        }

        // check diagonal top right to bottom left
        while(i<boardSize){
            i++;
            return board[i][boardSize - 1 - i] == symbol;
        }

        return true;
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
