import java.util.HashMap;
import java.util.ArrayList;
/**
 * The aim of this class is to create the n-grams and store it into the ArrayList.
 * The basic format of this class is same with the StoreWords class, however, this 
 * class uses the getter of the ngramN in the WordAnalyser class to set the size of
 * the n-gram.
 * 
 * @author 160021429
 *
 */
public class StoreWordsExt1 {
	HashMap<String,Integer> hm;
	ArrayList<NgramCount> ar;
	ArrayList<String> key;
	
	//constant to avoid the magic number
	final int ZERO = 0;
	final int ONE = 1;
	StoreWordsExt1(){
		hm = new HashMap<String,Integer>();
		key = new ArrayList<>();
	}
	
	void storeAllElements(String[] s){
		int n = WordAnalyserExt1.getNgramN(); //use getter to set the value of n by ngramN
		int limit = s.length-n+ONE;
		for(int i=ZERO; i<limit; i++){
			StringBuffer sb = new StringBuffer("");
			//set the limit of the for loop to create the n-gram
			for(int j = i; j<i+n-ONE; j++){
				sb.append(s[j]);
				sb.append(" ");
			}
			sb.append(s[i+n-ONE]); //add the nth element of the n-gram to the StringBuffer
			String string = sb.toString();
			
			if(hm.containsKey(string)){
				int k = hm.get(string);
				hm.remove(string);
				k++;
				hm.put(string, k);
			}
			if(!hm.containsKey(string)){
				hm.put(string, ONE);
				key.add(string);
			}
		}
	}
	void useNgramArray(){
		ar = new ArrayList<>();
		for(int i=ZERO; i<key.size(); i++){
			String ngram = key.get(i);
			int count = hm.get(ngram);
			NgramCount nc = new NgramCount(ngram, count);
			ar.add(nc);
		}
	}
}
