import javax.json.JsonArray;
import javax.json.JsonObject;
/**
 * The aim of this class is to read the json file.
 * @author 160021429
 */
public class JsonObjectInformationExt2 {
	private int exceptionCounter;
	private JsonObject parse;
	private String title;
	private int pageID;
	private JsonArray langLinks;
	private JsonArray categories;
	/**
	 * This constructor takes JsonObject obj as a parameter to take several information
	 * from the json file.
	 * 
	 * @param obj
	 */
	JsonObjectInformationExt2(JsonObject obj) {
		parse = obj.getJsonObject("parse");
		title = parse.getString("title");
		pageID = parse.getInt("pageid");
		langLinks = parse.getJsonArray("langlinks");
		categories = parse.getJsonArray("categories");
	}
	/**
	 * This is a getter for exceptionCounter.
	 * @return exceptionCounter
	 */
	public int getExceptionCounter() {
		return exceptionCounter;
	}
	/**
	 * The main aim of this method is to process the json file.
	 * Moreover, it would append strings to the StringBuilder object and return it
	 * by using the toString() method of the StringBuilder.
	 * This method uses if statements to sort texts.
	 * @return String
	 */
	public String getTextFromObject() {
		StringBuilder sb = new StringBuilder("");
		sb.append("   *title: ");
		sb.append(title);
		sb.append("\n   *pageid: ");
		sb.append(pageID);
		if (!langLinks.isEmpty()) {
			sb.append("\n   *langLinks");
			for (int i = 0; i < langLinks.size(); i++) {
				JsonObject jobj = langLinks.getJsonObject(i);
				sb.append("\n      -lang: ");
				sb.append(jobj.getString("lang"));
				sb.append("\n      -url: ");
				sb.append(jobj.getString("url"));
			}
		}
		if (!categories.isEmpty()) {
			sb.append("\n   *categories");
			for (int i = 0; i < categories.size(); i++) {
				JsonObject jobj = categories.getJsonObject(i);
				sb.append("\n      -");
				sb.append(jobj.getString("*"));
			}
		}
		return sb.toString();
	}
}
