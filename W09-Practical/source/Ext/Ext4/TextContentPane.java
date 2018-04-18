import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/**
 * The aim of this class is to create the content pane. This class extends JFrame and uses
 * java.awt and javax.swing to provide a graphical output. It creates the content pane, which
 * is called Text Output. It's size is 1000, 500. It contains JTextArea that contains the
 * information that the program read from the json file.
 * @author 160021429
 */
public class TextContentPane extends JFrame {
	private final int W = 1000;
	private final int V = 500;
	/**
	 * This constructor takes String text as a parameter to output the content that the
	 * program reads from the json file.
	 * @param text
	 */
	TextContentPane(String text) {
		Container c = getContentPane();
		setTitle("Text Output");
		setSize(W, V);
		JTextArea textArea = new JTextArea(W, V);
		JScrollPane sp = new JScrollPane(textArea);
		sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textArea.insert(text, 0);
		textArea.setEditable(false);
		c.add(textArea);
		setVisible(true);
	}
}
