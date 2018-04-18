import javax.json.JsonArray;
import javax.json.JsonObject;
/**
 * The main aim of this class is to process the json file.
 * @author 160021429
 */
public class JsonObjectInformationExt3 {
	private String heading;
	private JsonArray ja;
	private String text;
	private int exceptionCounter;
	/**
	 * This constructor takes JsonObject obj as a parameter to get several information from the json file.
	 * @param obj
	 */
	JsonObjectInformationExt3(JsonObject obj) {
		heading = obj.getString("Heading");
		ja = obj.getJsonArray("RelatedTopics");
		text = getTextFromObject();
	}
	/**
	 * This is the getter of heading.
	 * @return heading
	 */
	public String getHeading() {
		return heading;
	}
	/**
	 * This is the getter of text.
	 * @return text
	 */
	public String getText() {
		return text;
	}
	/**
	 * This is the getter of exceptionCounter.
	 * @return exceptionCounter
	 */
	public int getExceptionCounter() {
		return exceptionCounter;
	}
	/**
	 * The aim of this method is to append information that this class processed from the json file. 
	 * It appends information with the html format. Then it uses toString 
	 * method of the StringBuilder and return it.
	 * @return String
	 */
	private String getTextFromObject() {
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < ja.size(); i++) {
			JsonObject obj = ja.getJsonObject(i);
			try {
				sb.append("<br>");
				sb.append(obj.getString("Result"));
				sb.append("</br>");
				sb.append("\n");
			} catch (NullPointerException e) {
				exceptionCounter++;
			}
		}
		return sb.toString();
	}
}
