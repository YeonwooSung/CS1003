import java.io.FileNotFoundException;
import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
/**
 * The aim of this class is to allow program to read the json file from the online and
 * process it. After it processes, it prints the output. It uses try and catch to catch the
 * potential exception. Furthermore, it uses RESTClient object to read the web page called
 * DuckDuckGo.com.
 * @author 160021429
 */
public class W09PracticalExt3 {
	/**
	 * The aim of this main method is to read the online resource from the DuckDuckGo.com and
	 * process it and print out the suitable summary.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		RESTClient rc = new RESTClient(); //create the RESTClient object to read the online resources.
		String jsonString = null;
		String finding = args[0];
		try {
			StringBuilder sb = new StringBuilder("https://api.duckduckgo.com/?q=");
			sb.append(finding);
			sb.append("&ia=web&format=json");
			jsonString = rc.makeRESTCall(sb.toString()); //returns the json string from the web page
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		JsonReader reader = Json.createReader(new StringReader(jsonString));
		JsonObject obj = reader.readObject();
		DescribeExt3.jsonPrint(obj); //use the static method of DescribeExt3 class
	}
}
