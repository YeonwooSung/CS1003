import javax.json.JsonArray;
import javax.json.JsonObject;
/**
 * The aim of this class is to process the json file.
 * @author 160021429
 */
public class JsonObjectInformation {
	private String definition;
	private String heading;
	private JsonArray ja;
	private String text;
	private int exceptionCounter;
	/**
	 * The aim of this constructor is reading some information from the Json file. To read
	 * the json file, it takes JsonObject obj as a parameter.
	 * @param obj
	 */
	JsonObjectInformation(JsonObject obj) {
		definition = obj.getString("DefinitionSource");
		heading = obj.getString("Heading");
		ja = obj.getJsonArray("RelatedTopics");
		text = getTextFromObject();
	}
	/**
	 * This is a getter for definition.
	 * @return definition
	 */
	public String getDefinition() {
		return definition;
	}
	/**
	 * This is a getter for heading.
	 * @return heading
	 */
	public String getHeading() {
		return heading;
	}
	/**
	 * This is a getter for text.
	 * @return text
	 */
	public String getText() {
		return text;
	}
	/**
	 * This is a getter for exceptionCounter.
	 * @return exceptionCounter
	 */
	public int getExceptionCounter() {
		return exceptionCounter;
	}
	/**
	 * The aim of this method is to process the json file. To sort the data, I used if-else
	 * statement and for loops. The if-else statement checks if the json object contains
	 * Topics array. And it uses StringBuilder object to append texts that the program read
	 * from the json.
	 * @return String     This method uses toString() of StringBuilder and returns it.
	 */
	private String getTextFromObject() {
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < ja.size(); i++) {
			JsonObject obj = ja.getJsonObject(i);
			StringBuilder builder = new StringBuilder("");
			if (obj.containsKey("Topics")) {
				try {
					JsonArray category = obj.getJsonArray("Topics");
					for (int j = 0; j < category.size(); j++) {
						try {
							String texture = category.getJsonObject(j).getString("Text");
							builder.append("\n    - ");
							builder.append(texture);
						} catch (NullPointerException ex) {
							exceptionCounter++;
						}
					}
					/*
					 * The reason that I used builder is to avoid the NullPointerException.
					 * If there are no text in the category, it would be hard to handle the
					 * "* Category: ". Therefore, I used builder, the another StringBuilder,
					 * to avoid the possible error.
					 */
					if (builder.length() != 0) {
						sb.append("\n  * Category: ");
						sb.append(obj.getString("Name"));
						sb.append(builder.toString());
					}
				} catch (NullPointerException e) {
					exceptionCounter++;
				}
			} else {
				try {
					String texture = obj.getString("Text");
					sb.append("\n  - ");
					sb.append(texture);
				} catch (NullPointerException e) {
					exceptionCounter++;
				}
			}
		}
		return sb.toString();
	}
}
