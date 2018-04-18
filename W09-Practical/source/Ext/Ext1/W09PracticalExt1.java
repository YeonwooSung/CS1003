import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
/**
 * The aim of this class is to allow program to read the xml file from the online and
 * process it. After it processes, it prints the output. It uses try and catch to catch the
 * potential exception. Furthermore, it uses RESTClient object to read the web page called
 * DuckDuckGo.com.
 * @author 160021429
 */
public class W09PracticalExt1 {
	/**
	 * The aim of this main method is to read the xml file and process it.
	 * At the end it will print the summary of xml file by using DescribeExt1.xmlPrint method.
	 */
	public static void main(String[] args) {
		RESTClient rc = new RESTClient();
		String xmlString = null;
		String finding = args[0];
		try {
			StringBuilder sb = new StringBuilder("https://api.duckduckgo.com/?q=");
			sb.append(finding);
			sb.append("&ia=web&format=xml");
			xmlString = rc.makeRESTCall(sb.toString()); //returns the xml string from the web page
			//create DocumentBuilderFactory to use the DocumentBuilder
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
	        Document doc = db.parse(new InputSource(new StringReader(xmlString)));
	        doc.getDocumentElement().normalize();
			XMLInformation xml = new XMLInformation(doc);
			//create element object to use it as a parameter
			Element element = doc.getDocumentElement();
			DescribeExt1.xmlPrint(element, xml); //use the static method of DescribeExt1 class
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
