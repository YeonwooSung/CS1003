import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
/**
 * The aim of this class is to allow program to read the json file from the online and process it.
 * After it processes, it prints the output. It uses try and catch to catch the
 * potential exception. Furthermore, it uses RESTClient object to read the wikipedia API.
 * @author 160021429
 */
public class W09PracticalExt2 {
	/**
	 * The aim of this main method is to read the online resources by using the RESTClient object
	 * and process it by using DescribeExt2.jsonPrint method.
	 */
	public static void main(String[] args) {
		RESTClient rc = new RESTClient(); //create the RESTClient object to read the online resources.
		String jsonString = null;
		String finding = args[0];
		try {
			StringBuilder sb = new StringBuilder("https://en.wikipedia.org/w/api.php?format=json&action=parse&page=");
			sb.append(finding);
			jsonString = rc.makeRESTCall(sb.toString()); //returns the json string from the web page
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		JsonReader reader = Json.createReader(new StringReader(jsonString));
		JsonObject obj = reader.readObject();
		DescribeExt2.jsonPrint(obj, finding); //use the static method of DescribeExt2 class
	}	
}
