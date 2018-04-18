import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
/**
 * The aim of this class is to allow program to read the json file from the online and
 * process it. After it processes, it prints the output. It uses try and catch to catch the
 * potential exception. Furthermore, it uses RESTClient object to read the web page called
 * DuckDuckGo.com. At the end, it creates the GraphicalOutput object to show a graphical
 * output to users.
 * @author 160021429
 */
public class W09PracticalExt4 {
	/**
	 * The aim of this main method is to read the online resources and process it.
	 * At the end, it will show you the graphical output by using the GraphicalOutput object.
	 */
	public static void main(String[] args) {
		RESTClient rc = new RESTClient(); //create the RESTClient object to read the online resources.
		String jsonString = null;
		String finding = args[0];
		try {
			StringBuilder sb = new StringBuilder("https://api.duckduckgo.com/?q=");
			sb.append(finding);
			sb.append("&ia=web&format=json");
			jsonString = rc.makeRESTCall(sb.toString()); //returns the json string from the web page
		} catch (Exception e) {
			e.printStackTrace();
		}
		JsonReader reader = Json.createReader(new StringReader(jsonString));
		JsonObject obj = reader.readObject();
		JsonObjectInformationExt4 joi = new JsonObjectInformationExt4(obj);
		//create the GraphicalOutput object to use the graphical output
		new GraphicalOutput(joi);
	}
}
