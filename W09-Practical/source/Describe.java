import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import javax.json.*;
import javax.json.stream.JsonParsingException;
/**
 * The aim of this class is to automatically describe the content of the json file.
 * @author 160021429
 */
public class Describe {
	/**
	 *The main method uses one command line argument so that user could input the name of
	 * the file. Then this class reads that json file and process it.
	 * It uses multiple catch statements to catch potential exceptions such as
	 * IOException or JsonParsingException.
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
	 * The aim of this method is to print the suitable message. The message contains the
	 * result that the JsonObjectInformation object processed.
	 * This method takes JsonObejct as a parameter to create the JsonObejctInformation
	 * object and process the json file.
	 * @param obj
	 */
	static void jsonPrint(JsonObject obj) {
		JsonObjectInformation joi = new JsonObjectInformation(obj);
		System.out.print(joi.getHeading() + " can refer to:");
		System.out.println(joi.getText());
	}
}
