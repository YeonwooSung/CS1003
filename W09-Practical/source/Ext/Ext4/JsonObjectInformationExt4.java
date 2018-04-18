import java.util.ArrayList;
import java.util.HashMap;
import javax.json.JsonArray;
import javax.json.JsonObject;
public class JsonObjectInformationExt4 {
	private String definition;
	private String heading;
	private int imageWidth;
	private JsonArray ja;
	private int exceptionCounter;
	private ArrayList<String> aList;
	private HashMap<String, String> hm;
	/**
	 * The aim of this constructor is reading some information from the Json file. To read
	 * the json file, it takes JsonObject obj as a parameter.
	 * @param obj
	 */
	JsonObjectInformationExt4(JsonObject obj) {
		definition = obj.getString("DefinitionSource");
		heading = obj.getString("Heading");
		imageWidth = obj.getInt("ImageWidth");
		ja = obj.getJsonArray("RelatedTopics");
		
		aList = new ArrayList<>();
		aList.add("non-categorized");
		hm = new HashMap<>();
		
		getTextFromObject();
	}
	/**
	 * This is the getter of definition.
	 * @return definition
	 */
	public String getDefinition() {
		return definition;
	}
	/**
	 * This is the getter of heading.
	 * @return heading
	 */
	public String getHeading() {
		return heading;
	}
	/**
	 * This is the getter of imageWidth.
	 * @return imageWidth
	 */
	public int getImageWidth() {
		return imageWidth;
	}
	/**
	 * This is the getter of hm.
	 * @return hm
	 */
	public HashMap<String, String> getHM() {
		return hm;
	}
	/**
	 * This is the getter of aList.
	 * @return aList
	 */
	public ArrayList<String> getAList() {
		return aList;
	}
	/**
	 * This is the getter of exceptionCounter.
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
	private void getTextFromObject() {
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
							builder.append("\n  - ");
							builder.append(texture);
						} catch(NullPointerException ex) {
							exceptionCounter++;
						}
					}
					if (builder.length() != 0){
						aList.add(obj.getString("Name"));
						hm.put(obj.getString("Name"), builder.toString());
					}
				} catch (NullPointerException e) {
					exceptionCounter++;
				}
			} else {
				try {
					String texture = obj.getString("Text");
					sb.append("\n  - ");
					sb.append(texture);	
				} catch(NullPointerException e) {
					exceptionCounter++;
				}
			}
		}
		hm.put("non-categorized", sb.toString());
	}
}
