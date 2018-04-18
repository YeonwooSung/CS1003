import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/**
 * The aim of this class is to process the xml file and get information from it.
 * @author 160021429
 */
public class XMLInformation {
	private StringBuilder sb;
	private String text;
	/**
	 * This constructor takes a Document doc object as a parameter to process the xml file.
	 * It creates 2 StringBuilders to append information.
	 * @param doc
	 */
	XMLInformation(Document doc) {
		sb = new StringBuilder("");
		StringBuilder builder = new StringBuilder("");
		Node node = doc.getDocumentElement();
		/*
		 * In the xml file of DuckDuckGo.com, some RelatedTopic nodes are in the RelatedTopicsSection
		 * node. To get the texts inside the RelatedTopicsSection, it gets texts from all RelatedTopic
		 * nodes first. Then, it gets texts which are in the RelatedTopicsSection nodes. To avoid the
		 * duplication, I use StringBuilder's indexOf method and delete method.
		 * That is the reason why I used 2 for loops with different NodeLists.
		 */
		NodeList nList = ((Element) node).getElementsByTagName("RelatedTopic");
		for (int i = 0; i < nList.getLength(); i++) {
			Element e = (Element) nList.item(i);
			NodeList tList = e.getElementsByTagName("Text");
			sb.append("\n -");
			sb.append(tList.item(0).getTextContent());
		}
		NodeList topics = ((Element) node).getElementsByTagName("RelatedTopicsSection");
		for (int i = 0; i < topics.getLength(); i++) {
			Element e = (Element) topics.item(i);
			builder.append("\n *Category: ");
			builder.append(e.getAttribute("name"));
			NodeList textList = e.getElementsByTagName("Text");
			for (int j = 0; j < textList.getLength(); j++) {
				String texture = textList.item(j).getTextContent();
				builder.append("\n   -");
				builder.append(texture);
				int index = sb.indexOf("\n -" + texture);
				int length = texture.length();
				if (index >= 0) {
					sb.delete(index, length + index);
				}
			}
		}
		sb.append(builder.toString());
		text = sb.toString();
	}
	/**
	 * This is the getter of text.
	 * @return text
	 */
	public String getText() {
		return text;
	}
}
