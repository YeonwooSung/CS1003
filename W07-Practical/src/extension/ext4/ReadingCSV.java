import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
/**
 * The aim of this class is to read the CSV file.
 * @author 160021429
 */
public class ReadingCSV {
	private BufferedReader br; //private field to read the file
	/**
	 * The aim of this constructor is taking the File object as a parameter, and
	 * create the BufferedReader object by using the FileReader object, which uses
	 * the file as a parameter, as a parameter. This constructor also throws the
	 * IOExceptions to avoid the possible problem.
	 * 
	 * @param f
	 * @throws IOException
	 */
	ReadingCSV(File f) throws IOException {
		br = new BufferedReader(new FileReader(f)); //create the BufferedReader object
	}
	/**
	 * This method uses the readLine method of the BufferedReader and store the string.
	 * Then it returns that string. It also throws the IOException to avoid the possible
	 * exception.
	 * 
	 * @return
	 * @throws IOException
	 */
	String readTheFile() throws IOException{ //throws the IOException
		String line = br.readLine();
		return line;
	}
	
	/**
	 * The aim of this method is to check whether there is more line that the
	 * buffered reader could read. Then it returns the boolean.
	 * This method also throws the IOException to avoid the possible problem.
	 * 
	 * @return
	 * @throws IOException
	 */
	boolean canReadMore() throws IOException{
		boolean b = br.ready();
		return b;
	}
}
