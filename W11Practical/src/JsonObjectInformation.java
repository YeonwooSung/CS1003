import javax.json.JsonReader;
import javax.json.stream.JsonParsingException;
import java.io.StringReader;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonArray;
/**
 * The aim of this class is to process the JSON file and store hash tags in the ArrayList.
 * @author 160021429
 */
public class JsonObjectInformation {
	private ArrayList<String> hashtag;
	/**
	 * The aim of this constructor is to process the Json file and store information in the ArrayList.
	 * The constructor takes String s as a parameter, which contains the content of the JSON file.
	 * This constructor has a try-catch statement to catch the JsonParsingException that may occur
	 * until the JsonReader reads the JSON file and this constuctor process the data from JSON file.
	 * @param s
	 */
	JsonObjectInformation(String s) {
		hashtag = new ArrayList<>();
		try {
			JsonReader jr = Json.createReader(new StringReader(s));
			JsonObject obj = jr.readObject();
			JsonObject entities = obj.getJsonObject("entities");
			JsonArray hashtags = entities.getJsonArray("hashtags");
			//use for loop to iterate the JsonArray
			for (int i = 0; i<hashtags.size(); i++) {
				JsonObject hashtagObject = hashtags.getJsonObject(i);
				byte[] bytes = hashtagObject.getString("text").getBytes();
				String text = new String(bytes);
				hashtag.add(text);
			}
			jr.close();
		} catch (JsonParsingException e) {
			e.getMessage();
		} catch (Exception e) {
			e.getMessage();
		}
	}
	/**
	 * The getter of hashtag, which returns the ArrayList<String> hashtag.
	 * @return hashtag
	 */
	ArrayList<String> getHashtag() {
		return hashtag;
	}
}