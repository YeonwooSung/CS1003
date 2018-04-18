import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import javax.json.*;
import javax.json.stream.JsonParsingException;
/**
 * The aim of this class is to create JsonObjectInformationExt4 object to process the
 * json file and the GraphicalOutput object to make graphical output. This class use
 * multiple catch statements to catch IOException and JsonParsingException.
 * @author 160021429
 */
public class DescribeExt4 {
	/**
	 * The aim of this main method is to read the Json file and process it.
	 * Then it will show you the graphical output by using the GraphicalOutput object.
	 */
	public static void main(String[] args) throws Exception {
		try {
			File file = new File(args[0]);
			FileReader fr = new FileReader(file);
			ReadingFile rf = new ReadingFile(fr);
			JsonReader reader = Json.createReader(new StringReader(rf.getJsonString()));
			JsonObject obj = reader.readObject();
			JsonObjectInformationExt4 joi = new JsonObjectInformationExt4(obj);
			new GraphicalOutput(joi);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (JsonParsingException e) {
			System.out.println("Not a valid JSON string!");
		}
	}
}
