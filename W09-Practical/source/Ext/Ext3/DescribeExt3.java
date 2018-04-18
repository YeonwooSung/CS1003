import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import javax.json.*;
import javax.json.stream.JsonParsingException;
/**
 * The aim of this class is to create the html file which contains clickable links.
 * This class will process the json file and create the html file with information that it
 * gets from the json file.
 * @author 160021429
 */
public class DescribeExt3 {
	/**
	 * This main method uses command line argument to allow user to input the name of the
	 * json file. It uses jsonPrint method to write the html file. Moreover, it uses catch
	 * statements to catch potential exceptions such as IOException, and JsonParsingException.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			File file = new File(args[0]);
			FileReader fr = new FileReader(file);
			ReadingFile rf = new ReadingFile(fr);
			JsonReader reader = Json.createReader(new StringReader(rf.getJsonString()));
			JsonObject obj = reader.readObject();
			jsonPrint(obj);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (JsonParsingException e) {
			System.out.println("Not a valid JSON string!");
		}
	}
	/**
	 * This method takes JsonObject obj as a parameter to create the JsonObjectInformationExt3
	 * object. By using that object, it process the json file and get information from the
	 * json file. Then it writes the html file by using the PrintWriter object.
	 * @param obj
	 * @throws FileNotFoundException
	 */
	static void jsonPrint(JsonObject obj) throws FileNotFoundException {
		JsonObjectInformationExt3 joi = new JsonObjectInformationExt3(obj);
		File html = new File("output.html");
		PrintWriter pw = new PrintWriter(html);
		pw.println("<title>" + joi.getHeading() + "</title>");
		pw.println("<body>");
		pw.println(joi.getText());
		pw.print("</body>");
		pw.close();
	}
}
