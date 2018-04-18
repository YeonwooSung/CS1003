import javax.json.JsonReader;
import javax.json.stream.JsonParsingException;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonArray;
/**
 * The aim of this class is to store the number of re-tweets and the text of the most frequently re-tweeted Tweets.
 * @author 160021429
 */
public class JsonObjectInformationExt2 {
	private int count;
	private String text;
	/**
	 * The aim of this constructor is to process the Json file. It uses if statement to check
	 * if the text is null to avoid the NullPointerException. Then, it will get the retweet_count
	 * from the Json file.
	 * @param s
	 */
	JsonObjectInformationExt2(String s) {
		count = 0;
		try {
			JsonReader jr = Json.createReader(new StringReader(s));
			JsonObject obj = jr.readObject();
			text = obj.getString("text");
			JsonObject retweetStatus = obj.getJsonObject("retweeted_status");
			if (retweetStatus != null) {
				count = retweetStatus.getInt("retweet_count");
			}
			jr.close();
		} catch (JsonParsingException e) {
			e.getMessage();
		} catch (Exception e) {
			e.getMessage();
		}
	}
	/**
	 * This is the getter of count
	 * @return count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * This is the getter of text
	 * @return text
	 */
	public String getText() {
		return text;
	}
}
