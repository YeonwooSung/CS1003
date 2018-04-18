import java.io.*;
import java.util.ArrayList;
/**
 * The aim of this class is to read the text file and separate them as words,
 * and save them in the array list. Same with the CSVTextReader class.
 * 
 * @author 160021429
 */
public class CSVTextReaderExt1 {
	//declare the BufferedReader as a private attribute
	private BufferedReader br;
	//create the ArrayList to store strings
	ArrayList<String> al = new ArrayList<>();
	//create an constant to change small letter to capital letter
	final int changeToCapital = 32;
	//constants to check if the character is the small letter of the capital letter
	final int smallestCapital = 65;
	final int biggestCapital = 90;
	final int smallestSmallLetter = 97;
	final int biggestSmallLetter = 122;
	CSVTextReaderExt1(File inputFile) throws FileNotFoundException{
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
		for(int i=0; i<stringArray.length; i++){
			StringBuffer s = new StringBuffer("");
			for(int j=0; j<stringArray[i].length(); j++){
				char c = stringArray[i].charAt(j);
				//check if c is character
				//and then check if c is the small letter of the capital letter
				if(c>=smallestCapital && c<=biggestCapital){
					c += changeToCapital;
					s.append(c);
				}else if(c>=smallestSmallLetter && c<=biggestSmallLetter){
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