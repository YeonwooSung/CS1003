import java.util.HashMap;
import java.util.ArrayList;
/**
 * The aim of this class is to store strings by form of trigram.
 * This class is same with the StoreWords class.
 * 
 * @author 160021429
 */
public class StoreWordsExt2 {
	HashMap<String,Integer> hm;
	ArrayList<NgramCount> ar;
	ArrayList<String> key;
	
	//constant to avoid the magic number
	final int ZERO = 0;
	final int ONE = 1;
	final int TWO = 2;
	StoreWordsExt2(){
		hm = new HashMap<String,Integer>();
		key = new ArrayList<>();
	}
	
	void storeAllElements(String[] s){
		for(int i=ZERO; i<s.length-TWO; i++){
			StringBuffer sb = new StringBuffer(s[i]);
			sb.append(" ");
			sb.append(s[i+ONE]);
			sb.append(" ");
			sb.append(s[i+TWO]);
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