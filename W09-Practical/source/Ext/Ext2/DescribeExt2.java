import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import javax.json.*;
import javax.json.stream.JsonParsingException;
/**
 * The main method uses one command line argument so that user could input the name of
 * the file. Then this class reads that json file and process it.
 * This class uses multiple catch statements to catch potential exceptions such as
 * IOException or JsonParsingException.
 * @param args
 */
public class DescribeExt2 {
	/**
	 * The aim of this main method is to read the Json file and process it by using
	 * the jsonPrint method.
	 */
	public static void main(String[] args) {
		String topic = args[0];
		try {
			File file = new File(topic);
			FileReader fr = new FileReader(file);
			ReadingFile rf = new ReadingFile(fr);
			JsonReader reader = Json.createReader(new StringReader(rf.getJsonString()));
			JsonObject obj = reader.readObject();
			jsonPrint(obj, topic);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (JsonParsingException e) {
			System.out.println("Not a valid JSON string!");
		}
	}
	/**
	 * The aim of this method is to print the suitable message. The message contains the
	 * result that the JsonObjectInformationExt2 object processed.
	 * This method takes JsonObejct as a parameter to create the JsonObejctInformationExt2
	 * object and process the json file.
	 * @param obj
	 */
	static void jsonPrint(JsonObject obj, String topic) {
		JsonObjectInformationExt2 joi = new JsonObjectInformationExt2(obj);
		System.out.println(topic + " can refer to: ");
		System.out.println(joi.getTextFromObject());
	}
}
