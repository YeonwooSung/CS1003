import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * The aim of this class is to read the file by using the BufferedReader object.
 * @author 160021429
 */
public class ReadingFile {
	private BufferedReader br;
	private String jsonString;
	/**
	 * This constructor uses while loop with the ready method of the BufferedReader object.
	 * Then it appends the read line to the StringBuilder. At the end, it uses toString method
	 * and initialize the jsonString with it.
	 * This constructor takes FileReader fr object as a parameter to create the BufferedReader
	 * object. Moreover, it throws the IOExcpetion.
	 * @param fr
	 * @throws IOException
	 */
	ReadingFile(FileReader fr) throws IOException {
		br = new BufferedReader(fr);
		StringBuilder sb = new StringBuilder("");
		while (br.ready()) {
			sb.append(br.readLine());
		}
		jsonString = sb.toString();
	}
	/**
	 * This is the getter of jsonString.
	 * @return jsonString
	 */
	public String getJsonString() {
		return jsonString;
	}
}
