/*************************************************
* HashDictionary.java
* This program demostrates linkedlist with separate chaining 
* and it will map String into position of table
************************************************/

import java.util.Hashtable;

public class HashDictionary implements DictionaryADT {
    private Hashtable<String, Data> dict;

    // create dict hashtable
    public HashDictionary(int size) {
        dict = new Hashtable<>(size);
    }

    @Override
    public int put(Data record) throws DictionaryException {
        // add record to dict
        String config = record.getConfiguration();

        // check the config is in dict
        if (dict.containsKey(config)) {
            throw new DictionaryException();
        }

        // check config and record put in dict is not null
        if (dict.put(config, record) != null) {
            return 1;
        } else {
            return 0;
        }
    }

    // remove config which is in dict
    @Override
    public void remove(String config) throws DictionaryException{        
        // check config in dict
        if (!dict.containsKey(config)) {
            throw new DictionaryException();
        }
        dict.remove(config);
    }

    // return score of config in dict if config in dict
    @Override
    public int get(String config){
        if(dict.containsKey(config)){
            return dict.get(config).getScore();
        }
        return -1;
    }

    // return data amount which in dict
    @Override
    public int numRecords(){
        return dict.size();
    }
}
