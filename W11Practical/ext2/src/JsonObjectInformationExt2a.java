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
public class JsonObjectInformationExt2a {
	private int count;
	private String id;
	/**
	 * This constructor will get the id of users and the retweet_count from the JSON file.
	 * I use 2 if statements to avoid the NullPointerException. And it has try-catch statement
	 * to catch potential exceptions.
	 * @param s
	 */
	JsonObjectInformationExt2a(String s) {
		count = 0;
		try {
			JsonReader jr = Json.createReader(new StringReader(s));
			JsonObject obj = jr.readObject();
			JsonObject user = obj.getJsonObject("user");
			JsonObject retweetStatus = obj.getJsonObject("retweeted_status");
			if (retweetStatus != null) {
				int i = retweetStatus.getInt("retweet_count");
				count = i;
			}
			if (user != null) {
				id = user.getString("id_str");
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
	 * This is the getter of id
	 * @return id
	 */
	public String getID() {
		return id;
	}
}
