import java.io.*;
import java.util.ArrayList;
/**
 * The aim of this class is to read the text file and separate them as words,
 * and save them in the array list. 
 * 
 * @author 160021429
 */
public class CSVTextReaderExt3 {
	//declare the BufferedReader as a private attribute
	private BufferedReader br;
	//create the ArrayList to store strings
	ArrayList<String> al = new ArrayList<>();
	//create an constant to change small letter to capital letter
	private final int CHANGE_TO_CAPITAL = 32;
	//constants to check if the character is the small letter of the capital letter
	private final int SMALLEST_CAPITAL = 65;
	private final int BIGGEST_CAPITAL = 90;
	private final int SMALLEST_SMALL = 97;
	private final int BIGGEST_SMALL = 122;
	//create constant to avoid magic number
	final int ZERO = 0;
	CSVTextReaderExt3(File inputFile) throws FileNotFoundException{
		br = new BufferedReader(new FileReader(inputFile));
	}
	
	String ReadTheFile() throws IOException{
		String line = br.readLine();
		return line;
	}
	
	boolean canReadMore() throws IOException{
		boolean b = br.ready();
		return b;
	}
	
	void wordAnalyse(String line){
		String[] stringArray = line.split(" ");
		for(int i=ZERO; i<stringArray.length; i++){
			StringBuffer s = new StringBuffer("");
			for(int j=ZERO; j<stringArray[i].length(); j++){
				char c = stringArray[i].charAt(j);
				//check if c is character
				//and then check if c is the small letter of the capital letter
				if(c>=SMALLEST_CAPITAL && c<=BIGGEST_CAPITAL){
					c += CHANGE_TO_CAPITAL;
					s.append(c);
				}else if(c>=SMALLEST_SMALL && c<=BIGGEST_SMALL){
					s.append(c);
				}
			}
			boolean checker = false;
			for(int k =0; k<s.length(); k++){
				if(s.charAt(k) != '\0')
					checker = true;
			}
			if(checker){
				String string = s.toString();
				if(string != null && string != "")
					al.add(string);
			}
		}
	}
}