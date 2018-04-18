import java.io.*;
import java.util.Collections;

/**
 * The aim of this class is read the text file to create multiple objects and 
 * analyse words to trigram.
 * Then, it sort those trigrams into the order.
 * At the end, it prints trigrams and the number of counts by using the print writer as a
 * format of csv file.
 * 
 * @author 160021429
 */
public class WordAnalyser {
	public static void main(String[] args){
		try{
			//create files by using command line arguments
			File input = new File(args[0]);
			File output = new File(args[1]);

			//create the objects
			CSVTextReader csv = new CSVTextReader(input);
			PrintWriter pw = new PrintWriter(output);
			StoreWords sw = new StoreWords();

			//while loop starts
			while(csv.canReadMore()){
				//read a line from the text file
				String line = csv.ReadTheFile();
				csv.wordAnalyse(line); //split all words from the text and store them into the array list
			}

			//change the array list to the string type array, because the storeAllElements method
			//of the StoreWords class takes string type array as a parameter.
			String[] stringArray = new String[csv.al.size()];
			stringArray = csv.al.toArray(stringArray);

			sw.storeAllElements(stringArray); //change all words to trigrams
			sw.useNgramArray(); //create NgramCount objects and store them into the array list
			Collections.sort(sw.ar, Collections.reverseOrder());

			//use for loop to print out all trigrams and counts by using the print writer.
			for(int i=0; i<sw.ar.size(); i++)
				pw.println("\""+sw.ar.get(i).ngram+"\""+","+sw.ar.get(i).count);

			pw.flush();
			pw.close();

		}catch(ArrayIndexOutOfBoundsException e){ //try ends and catch the ArrayIndexOutOfBoundsException
			System.out.println("Usage: java WordAnalyser <input_file> <output_file>");
		} catch (FileNotFoundException e) { //catch the FileNotFoundException
			e.printStackTrace();
		} catch (IOException e) { //catch the IOException
			e.printStackTrace();
		}
	}
}
