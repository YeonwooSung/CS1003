import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
/**
 * The aim of this class is to read the xml file and describe the content of the xml file.
 * This class uses one command line argument to allow user to input the name of the xml
 * file.
 * @author 160021429
 */
public class DescribeExt1 {
	/**
	 * This main method uses one command line argument to allow user to input the name of the xml file.
	 * It also uses try and catch statement to catch possible extensions such as IOException, ParserConfigurationException, or SAXException.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			File f = new File(args[0]);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(f);
			doc.getDocumentElement().normalize();
			XMLInformation xml = new XMLInformation(doc);
			Element element = doc.getDocumentElement();
			xmlPrint(element, xml);
		}  catch (IOException | ParserConfigurationException | SAXException e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * The aim of this method is to print out the suitable output that the XMLInformation
	 * class processed. This method takes Element type object and XMLInformation obejct
	 * as a parameter to bring the information from the xml file.
	 * @param e
	 * @param xml
	 */
	static void xmlPrint(Element e, XMLInformation xml) {
		System.out.print(e.getElementsByTagName("Heading").item(0).getTextContent() + " can refer to:");
		System.out.println(xml.getText());
	}
}
