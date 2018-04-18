import java.io.*;
import java.util.*;

public class WordAnalyserExt1 {
	private static int ngramN; //declare the integer type static field to store the length of ngram
	static int getNgramN(){ //getter for the ngramN
		return ngramN;
	}
	public static void main(String[] args){
		try{
			//create files by using command line arguments
			File input = new File(args[0]);
			File output = new File(args[1]);
			//use command line arguments to input the n
			ngramN = Integer.parseInt(args[2]);
			if(ngramN<=0) //to check if the n is less than or equal to 0
				throw new NumberFormatException();
			//create the objects
			CSVTextReaderExt1 csv = new CSVTextReaderExt1(input);
			PrintWriter pw = new PrintWriter(output);
			StoreWordsExt1 sw = new StoreWordsExt1();
			//while loop starts
			while(csv.canReadMore()){
				String line = csv.ReadTheFile();
				csv.wordAnalyse(line);
			}
			String[] stringArray = new String[csv.al.size()];
			stringArray = csv.al.toArray(stringArray);
			sw.storeAllElements(stringArray);
			sw.useNgramArray();
			Collections.sort(sw.ar, Collections.reverseOrder());
			for(int i=0; i<sw.ar.size(); i++)
				pw.println("\""+sw.ar.get(i).ngram+"\""+","+sw.ar.get(i).count);
			pw.flush();
			pw.close();
		}catch(ArrayIndexOutOfBoundsException e){ //try ends and catch the ArrayIndexOutOfBoundsException
			System.out.println("Usage: java WordAnalyser <input_file> <output_file> <Choose n for Ngram>");
		} catch (FileNotFoundException e) { //catch the FileNotFoundException
			e.printStackTrace();
		} catch (IOException e) { //catch the IOException
			e.printStackTrace();
		}catch (NumberFormatException e){
			System.out.println("Please input the integer that is greater than 0");
		}
	}
}
