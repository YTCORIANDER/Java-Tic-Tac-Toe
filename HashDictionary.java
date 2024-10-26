/*********************************************************************
* HashDictionary.java
* This program demostrates linkedlist with hashtable separate chaining 
* and it will map String into position of table
**********************************************************************/

import java.util.LinkedList;
public class HashDictionary implements DictionaryADT {
    private LinkedList<Data>[] T;
    private int size;

    // constrator
    @SuppressWarnings("unchecked")
    public HashDictionary(int size) {
        this.size = size;
        T = new LinkedList[size];

        // inital and create list
        int i = 0;
        while (i < size) {
            T[i] = new LinkedList<>();
            i++;
        }   
    }
    
    // custom hash function
    private int h(String config) {
        int hashVal = 0;
        // choose 10 for better distribution
        int prime = 10; 
        
        int i = 0;
        while (i < config.length()) {
            // keep hashVal within bounds
            hashVal = (prime * hashVal + config.charAt(i)) % size;
            i++;
        }
        
        // Ensure non-negative index by adjusting if necessary
        return (hashVal + size) % size;
    }

    @Override
    public int put(Data record) throws DictionaryException {

        // check the config is in table T
        int i = 0;
        while (i < T[h(record.getConfiguration())].size()) {
            //if Configuration exists then throw exception
            if (T[h(record.getConfiguration())].get(i).getConfiguration().equals(record.getConfiguration())) {
                throw new DictionaryException();
            }
            i++;
        }
        // check if adding record to table produces collision or not
        int exist = 0;
        if (!T[h(record.getConfiguration())].isEmpty()) {
            exist = 1;
        } else{
            exist = 0;
        }

        // record put in linkedlist if is not null
        T[h(record.getConfiguration())].add(record);
        return exist;
    }
    

    // remove config which is in dict
    @Override
    public void remove(String config) throws DictionaryException{        
        // check config in dict
        int i = 0;
        // use flag to check if record exist or not
        boolean flag = true;

        while (i < T[h(config)].size()) {
            // if config exist in list then remove item
            if (T[h(config)].get(i).getConfiguration().equals(config)) {
                flag = false;
                T[h(config)].remove(i);
            }
            i++;
        }
        // if exist throw exception
        if(flag){
            throw new DictionaryException();
        }
    }

    // get return score of config in dict if config in dict
    @Override
    public int get(String config){
        int i = 0;
        while (i < T[h(config)].size()) {
            if (T[h(config)].get(i).getConfiguration().equals(config)) {
                return T[h(config)].get(i).getScore();
            }
            i++;
        }
        return -1;
    }

    // return data amount which in dict
    @Override
    public int numRecords(){
        int count = 0, i = 0;
        
        // Add current linked list size to the count
        while (i < T.length) {
            count = T[i].size() + count;
            i++;
        }
        return count;
    }    
}
