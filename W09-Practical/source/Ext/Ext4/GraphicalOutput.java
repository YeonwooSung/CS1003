import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * This class is for showing the graphical output by using swing. This class extends
 * JFrame. It has 2 private object which are HashMap<String,String> hm and StringBuffer
 * sb. This class has 3 inner classes to make my listeners.
 * @author 160021429
 */
public class GraphicalOutput extends JFrame {
	private HashMap<String, String> hm;
	private StringBuffer sb;
	private final int W = 300;
	private final int THREE = 3;
	private final int TWENTY = 20;
	private final int V2 = 40;
	private final int W2 = 50;
	private final int W1 = 100;
	private final int V1 = 30;
	private final int V3 = 200;
	/**
	 * The aim of this constructor is to create the content pane and add JCheckBoxes and
	 * JButton. It takes JsonObjectInformationExt4 object as a parameter to take several
	 * information from it. It will make a string type array that stores the name of categories.
	 * Moreover, it will create the HashMap hm that uses the name of the category as a key and
	 * the content of the category as a value. Furthermore, it creates StringBuffer to store the
	 * content of the category. It also uses several listeners to handle events that is occurred.
	 * @param joi
	 */
	GraphicalOutput(JsonObjectInformationExt4 joi) {
		String[] s = new String[joi.getAList().size()];
		s = joi.getAList().toArray(s);
		hm = joi.getHM();
		sb = new StringBuffer("");
		int verticalLength = (s.length + THREE) * V1 + s.length * V2;
		setTitle("Categories");
		setSize(W, verticalLength);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		int vLocation = TWENTY;
		for (int i = 0; i < s.length; i++) {
			JCheckBox cb = new JCheckBox(s[i]);
			vLocation += W2;
			cb.setSelected(false);
			cb.setSize(W1, V1);
			cb.setLocation(W1, vLocation);
			cb.addItemListener(new MyItemListener());
			cb.setVisible(true);
			c.add(cb);
		}
		JButton b = new JButton("Apply");
		b.setSize(W2, TWENTY);
		vLocation += V2;
		b.setLocation(V3, vLocation);
		b.setVisible(true);
		b.addActionListener(new MyActionListener());
		c.add(b);
		c.addMouseListener(new MyMouseListener());
		setVisible(true);
	}
	/**
	 * This inner class is a listener that handles events that is called by JCheckBox.
	 * It implements the ItemListener.
	 * @author 160021429
	 */
	class MyItemListener implements ItemListener {
		/**
		 * It overrides the itemStateChanged method of the ItemListener interface. 
		 * This method uses if-else statement to check if the JCheckBox is selected or deselected.
		 * It appends or delete the content of the category to the StringBuffer sb.
		 * @Override
		 */
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				JCheckBox b = (JCheckBox) e.getItem();
				String name = b.getText();
				sb.append(hm.get(name));
			} else {
				JCheckBox b = (JCheckBox) e.getItem();
				String name = b.getText();
				String content =  hm.get(name);
				int i = sb.indexOf(content);
				sb.delete(i, i + content.length());
			}
		}
	}
	/**
	 * The aim of this class is to handle MouseEvent.
	 * @author 160021429
	 */
	class MyMouseListener extends MouseAdapter {
		private final int COL = 256;
		private final int TWO = 2;
		/**
		 * It uses Math.random method to select the color randomly. Then it set the
		 * background color by using that color.
		 * @Override
		 */
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == TWO) {
				int r = (int) (Math.random() * COL);
				int g = (int) (Math.random() * COL);
				int b = (int) (Math.random() * COL);
				JPanel p = (JPanel) e.getSource();
				p.setBackground(new Color(r, b, g));
			}
		}
	}
	/**
	 * The aim of this class is to handle the Action event that is occurred by the JButton.
	 * It implements the ActionListener interface.
	 * @author 160021429
	 */
	class MyActionListener implements ActionListener {
		/**
		 * The aim of this method is to handle the Action event. If the user press the
		 * JButton, the Action event will occur. Then, this method will create the
		 * TextContentPane object, which takes string as a parameter. By creating the
		 * TextContentPane object, the software will create one more content pane which
		 * contains texts of categories that the user choose.
		 * @Override
		 */
		public void actionPerformed(ActionEvent e) {
			new TextContentPane(sb.toString());
		}
	}
}
