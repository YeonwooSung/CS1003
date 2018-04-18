/**
 * The aim of this class is to store the information that the ReadingCSV class read.
 * 
 * @author 160021429
 */
public class CSVObject {
	//private fields to use as names of columns
	private int invoiceNo;
	private String stockCode;
	private String description;
	private int quantity;
	private String invoiceDate;
	private double unitPrice;
	private String customerID;
	private String country;
	/**
	 * The aim of this constructor is split the String that it takes as a parameter,
	 * and store those split words to fields of the class.
	 * 
	 * @param line
	 */
	CSVObject(String line) {
		String[] s = line.split(",");
		invoiceNo = Integer.parseInt(s[0]);
		stockCode = s[1];
		description = s[2];
		quantity = Integer.parseInt(s[3]);
		invoiceDate = s[4];
		unitPrice = Double.parseDouble(s[5]);
		customerID = s[6];
		country = s[7];
	}
	/**
	 * The aim of this method is returning the string that is the SQL query.
	 * @return
	 */
	public String invoiceQuery() { //method to change the format -> 1 string (SQL query)
		StringBuffer sb = new StringBuffer(""); //use StringBuffer to append SQL statements
		sb.append("INSERT INTO invoice ");
		sb.append("VALUES (?, ?, ?, ?, ? , ?, ? , ?)");
		//change StringBuffer to String by using the toString method and return it
		return sb.toString();
	}
	
	/**
	 * This is a getter for invoiceNo
	 * @return
	 */
	public int getInvoiceNo() { 
		return invoiceNo; 
	}
	/**
	 * This is a getter for stockCode
	 * @return
	 */
	public String getStockCode() { 
		return stockCode; 
	}
	/**
	 * This is a getter for description
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * This is a getter for quantity
	 * @return
	 */
	public int getQuantity() { 
		return quantity;
	}
	/**
	 * This is a getter for invoiceDate
	 * @return
	 */
	public String getInvoiceDate() {
		return invoiceDate; 
	}
	/**
	 * This is a getter for unitPrice
	 * @return
	 */
	public double getUnitPrice() { 
		return unitPrice; 
	}
	/**
	 * This is a getter for customerID
	 * @return
	 */
	public String getCustomerID() { 
		return customerID;
	}
	/**
	 * This is a getter for country
	 * @return
	 */
	public String getCountry() { 
		return country; 
	}
	/**
	 * The aim of this method is to check if the input file has a blank column.
	 * This method takes a string type array as a parameter to check if there is a missing
	 * column. If so, then this method returns the true. If not, this method will return 
	 * the false.
	 * 
	 * @param s
	 * @return
	 */
	public boolean checkBlank(String[] s) { //method to check if the csv file has a blank column
		boolean checker = false;
		for(int i=0; i<s.length; i++){
			if(s[i].equals(" ")){
				checker = true;
			}
		}
		return checker;
	}
}
