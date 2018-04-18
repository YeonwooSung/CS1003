import java.util.HashMap;
import java.util.ArrayList;

/**
 * The aim of this class is to store strings by form of trigram.
 * 
 * @author 160021429
 */
public class StoreWords {
	//This array list will store the trigram as a key, and the count as a value.
	HashMap<String,Integer> hm;
	//This array list will store NgramCount objects
	ArrayList<NgramCount> ar;
	//This array list will store keys of the hashmap, therefore, the program could get the
	//value from the hash map.
	ArrayList<String> key;
	//constant to avoid the magic number
	final int ZERO = 0;
	final int ONE = 1;
	final int TWO = 2;
	/**
	 * create the hash map object and the array list object inside the constructor.
	 */
	StoreWords(){
		hm = new HashMap<String,Integer>();
		key = new ArrayList<>();
	}
	/**
	 * The aim of this method is to store all strings by the format of trigram.
	 * This method uses a string type array as a parameter, which includes all strings
	 * from the text file.
	 * 
	 * @param s
	 */
	void storeAllElements(String[] s){
		for(int i=ZERO; i<s.length-TWO; i++){
			//I used the string buffer because, I need to add more elements in the string
			//when I make the trigram. Therefore, I thought that using the string buffer 
			//would be more efficient way.
			StringBuffer sb = new StringBuffer(s[i]);
			sb.append(" ");
			sb.append(s[i+ONE]);
			sb.append(" ");
			sb.append(s[i+TWO]);
			String string = sb.toString();
			
			if(hm.containsKey(string)){ //check if the hash map already contains this trigram
				int k = hm.get(string); //get the value to change the number of counts
				hm.remove(string); //remove the key and value -> to update the counts
				k++; //add 1 to the count
				hm.put(string, k); //put the new value and key into the hash map
			}
			if(!hm.containsKey(string)){ //check if the hash map does not contains this key
				hm.put(string, ONE); //add this trigram as a key. The value should be one.
				key.add(string); //add trigram to the array list
			}
		}
	}
	/**
	 * The aim of this method is to create NgramCount object by using the key array list and
	 * the hash map which contains the trigram and the count, and add those objects into the
	 * array list.
	 */
	void useNgramArray(){
		ar = new ArrayList<>(); //create the array list object to store NgramCount object
		//get all elements from the array list. Use them to get all counts from the hash map.
		for(int i=ZERO; i<key.size(); i++){
			String ngram = key.get(i);
			int count = hm.get(ngram);
			NgramCount nc = new NgramCount(ngram, count); //create the NgramCount object
			ar.add(nc); //add all of them
		}
	}
}
