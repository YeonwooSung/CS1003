import java.io.*;
import java.util.Collections;
/**
 * The aim of this class is to combine trigram counts from more than one file.
 * This class is almost same with the WordAnalyser class, however, this class creates
 * 3 CSVTextReaderExt3 objects to read 3 different text files and combine them.
 * 
 * @author 160021429
 *
 */
public class WordAnalyserExt3 {
	public static void main(String[] args){
		try{
			//create files by using command line arguments
			File input1 = new File(args[0]); //input file 1
			File input2 = new File(args[1]); //input file 2
			File input3 = new File(args[2]); //input file 3
			File output = new File(args[3]);
			//create the objects
			CSVTextReaderExt3 csv1 = new CSVTextReaderExt3(input1); //input file 1
			CSVTextReaderExt3 csv2 = new CSVTextReaderExt3(input2); //input file 2
			CSVTextReaderExt3 csv3 = new CSVTextReaderExt3(input3); //input file 3
			PrintWriter pw = new PrintWriter(output);
			StoreWordsExt3 sw = new StoreWordsExt3();
			//run the 3 while loops
			while(csv1.canReadMore()){ //run the first while loop
				String line = csv1.ReadTheFile();
				csv1.wordAnalyse(line);
			}
			while(csv2.canReadMore()){ //run the second while loop
				String line = csv2.ReadTheFile();
				csv2.wordAnalyse(line);
			}
			while(csv2.canReadMore()){ //run the third while loop
				String line = csv2.ReadTheFile();
				csv2.wordAnalyse(line);
			}
			//string type arrays for each input files
			String[] stringArray1 = new String[csv1.al.size()];
			String[] stringArray2 = new String[csv2.al.size()];
			String[] stringArray3 = new String[csv3.al.size()];
			stringArray1 = csv1.al.toArray(stringArray1);
			stringArray2 = csv2.al.toArray(stringArray2);
			stringArray3 = csv3.al.toArray(stringArray3);
			//store all elements from 3 input files
			sw.storeAllElements(stringArray1);
			sw.storeAllElements(stringArray2);
			sw.storeAllElements(stringArray3);
			//store all trigrams and counts by using NgramCount object and store all objects into the ArrayList
			sw.useNgramArray(); 
			Collections.sort(sw.ar, Collections.reverseOrder());
			for(int i=0; i<sw.ar.size(); i++)
				pw.println("\""+sw.ar.get(i).ngram+"\""+","+sw.ar.get(i).count);
			pw.flush();
			pw.close();
		}catch(ArrayIndexOutOfBoundsException e){ //try ends and catch the ArrayIndexOutOfBoundsException
			System.out.println("Usage: java WordAnalyser <input_file1> <input_file2> <input_file3> <output_file>");
		} catch (FileNotFoundException e) { //catch the FileNotFoundException
			e.printStackTrace();
		} catch (IOException e) { //catch the IOException
			e.printStackTrace();
		}
	}
}
