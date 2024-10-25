/*************************************************
* Data.java
* This program demostrates record stored in HashDictionary
************************************************/

public class Data {
    private String config;
    private int score;

    // constructor
    public Data(String config, int score){
        this.config = config;
        this.score = score;
    }

    // return config
    public String getConfiguration(){
        return config;
    }
    
    // return store
    public int getScore(){
        return score;
    }
}


