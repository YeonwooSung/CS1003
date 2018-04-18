import java.io.*;
import java.util.Collections;
/**
 * The main aim of this class is to check the speed of my practical.
 * 
 * @author 160021429
 */
public class WordAnalyserExt2 {
	public static void main(String[] args){
		try{
			//create files by using command line arguments
			File input = new File(args[0]);
			File output = new File(args[1]);
			//create the objects
			CSVTextReaderExt2 csv = new CSVTextReaderExt2(input);
			PrintWriter pw = new PrintWriter(output);
			StoreWordsExt2 sw = new StoreWordsExt2();
			long time1 = System.currentTimeMillis(); //to check the time it takes
			//while loop starts
			while(csv.canReadMore()){
				String line = csv.ReadTheFile();
				csv.wordAnalyse(line);
			}
			//to check the time until program analyse the text
			long timeAnalyse = System.currentTimeMillis();
			String[] stringArray = new String[csv.al.size()];
			stringArray = csv.al.toArray(stringArray);
			sw.storeAllElements(stringArray);
			sw.useNgramArray();
			Collections.sort(sw.ar, Collections.reverseOrder());
			//to check the time until program sort the trigram
			long timeSort = System.currentTimeMillis();
			for(int i=0; i<sw.ar.size(); i++)
				pw.println("\""+sw.ar.get(i).ngram+"\""+","+sw.ar.get(i).count);
			long time2 = System.currentTimeMillis(); //to check the time it takes
			long wholeTime = time2-time1; //to check the total time that it takes
			//to check the time that it takes while the program analyse the text
			long analysingTime = timeAnalyse - time1;
			//to check the time that it takes while the program sort trigrams
			long sortingTime = timeSort - timeAnalyse;
			//to check the time that it takes while the program prints information into the output file
			long printingTime = time2 - timeSort;
			//print out times that it takes
			System.out.println("whole time: "+wholeTime);
			System.out.println("analysing time: "+ analysingTime);
			System.out.println("sorting time: "+ sortingTime);
			System.out.println("printing time: "+printingTime);
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
