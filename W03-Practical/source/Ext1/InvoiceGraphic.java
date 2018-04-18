import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * The main object of this class is to present the graphical output
 * of percentage of cancelled invoices.
 * 
 * Therefore, the constructor requires 3 parameters, which are
 * numOfCancelledInvoice, numOfInvoice, and title.
 * The title is the title of the swing content pane.
 * I used remaining 2 parameters when I calculate the percentage of
 * cancelled invoices.
 * 
 * @author Yeonwoo Sung
 *
 */
public class InvoiceGraphic extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	MyPanel pane;
	int numOfCancelledInvoice;
	int numOfInvoice;
	
	/**
	 * use integers as a parameter to set the value of MyPanel class's attributes
	 * @param numOfCancelledInvoice
	 * @param numOfInvoice
	 */
	InvoiceGraphic(int numOfCancelledInvoice, int numOfInvoice, String title){ //create the constructor
		super(title); //use the super class(JFrame)'s constructor
		Container container = getContentPane(); //get the content pane and create the container object
		
		//setting the values to integer type attributes
		this.numOfCancelledInvoice = numOfCancelledInvoice;
		this.numOfInvoice = numOfInvoice;
		
		setVisible(true); //set the content pane visible
		//exit the program when the user close the window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//set the border layout as a layout
		container.setLayout(new BorderLayout());
		setSize(400,400); //set the size of the frame
		pane = new MyPanel();//create the MyPanel object
		container.add(pane,BorderLayout.CENTER);
		pane.setVisible(true);
		
		//set the value to height1 and height2
		pane.height1 = numOfCancelledInvoice*400/numOfInvoice;
		pane.height2 = numOfInvoice - numOfCancelledInvoice;
		pane.height2 *= 400;
		pane.height2 /= numOfInvoice;
		pane.text1 = "cancelled invoices: Red";
		pane.text2 = "non-cancelled invoices: Blue";
	}
	
	class MyPanel extends JPanel{ //create the inner class
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		int height1;
		int height2;
		String text1;
		String text2;
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			g.setColor(Color.red);
			g.fillRect(60, 400-height1, 50, height1);
			g.setColor(Color.blue);
			g.fillRect(160, 400-height2, 50, height2);
		}
	}//inner class ends
}
