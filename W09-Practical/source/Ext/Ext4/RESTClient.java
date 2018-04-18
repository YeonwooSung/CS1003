import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
// Adapted from Steve Linton
// Modified for the duckduckgo api by Kasim Terzic
/**
 * This class is used to demonstrate REST (Representational State Transfer) communication in Java.
 * An HttpURLConnection object is created with a URL as a parameter, which allows data to be read and posted between
 * the server at the specified URL, and the Java client.
 */
public class RESTClient {
	private final int TWO_HUNDRED = 200;
    /**
     *  Return a String representation of the returned data from a REST call to a specified URL
     *  This method throws the MalformedURLException and the IOException.
     *  @param strURL                    to read the online resource
     *  @throws MalformedURLException    to avoid the possible exception
     *  @throws IOException              to avoid the possible exception
     *  @return String
     */
    public String makeRESTCall(String strURL) throws MalformedURLException, IOException {
        URL url = new URL(strURL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //Specify that we are expecting JSON data to be returned
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "*/*");
        // We must specify the user agent, or the API will crash. So pretend that we are curl version 7.
        // I had a wonderful evening figuring this one out, really great fun, I recommend it to everyone!
        conn.setRequestProperty("User-Agent", "curl/7.37.0");
        // 200 is the 'OK' response code. This method may also return 401 for an unauthorised request,
        // or -1 if the response is not valid HTTP
        if (conn.getResponseCode() != TWO_HUNDRED) {
            throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
        }
        //Create reader to read response from the server
        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        //Using a StringBuilder is more time and memory efficient, when the size of the concatenated String could be very large
        StringBuilder buffer = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
            buffer.append(output);
        }
        conn.disconnect();
        // System.out.println(buffer.toString());
        return buffer.toString();
    }
}
