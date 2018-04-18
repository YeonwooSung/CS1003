import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * The aim of this class is to use JDBC methods for MariaDB.
 * 
 * @author 160021429
 */
public class ActionExt1 {
	//private static constants to avoid the magic number
	private static final int ZERO = 0;
	private static final int ONE = 1;
	private static final int TWO = 2;
	private static final int THREE = 3;
	private static final int FOUR = 4;
	private static final int FIVE = 5;
	private static final int SIX = 6;
	private static final int SEVEN = 7;
	private static final int EIGHT = 8;
	
	/**
	 * This method checks if the table already exists. If the table already exists, then
	 * it will drop that table. Then it will create new table, which is called invoice.
	 * This method uses 2 parameters, stmt and line. It uses Statement object to execute
	 * the SQL queries. Furthermore, this method will split the line with comma and use
	 * those words as column names of the new table. Also, this method throws the
	 * SQLException to prevent the possible exception.
	 * 
	 * @param stmt
	 * @param line
	 * @throws SQLException
	 */
	void createTable(Statement stmt, String line) throws SQLException {
		//drop the table if it exists already
		stmt.executeUpdate("DROP TABLE IF EXISTS invoice");
		
		String[] s = line.split(","); //split the string type array to give names to columns
		
		/*
		 * To use the SQL query, create the StringBuffer object.
		 * It creates the table invoice that includes attributes which are InvoiceNo, StockCode, 
		 * Description, Quantity, InvoiceDate, UnitPrice, CustomerID, Country.
		 */
		StringBuffer sb = new StringBuffer("CREATE TABLE invoice (");
		sb.append(s[ZERO]);
		sb.append(" int, ");
		sb.append(s[ONE]);
		sb.append(" VARCHAR(100), ");
		sb.append(s[TWO]);
		sb.append(" VARCHAR(100), ");
		sb.append(s[THREE]);
		sb.append(" int, ");
		sb.append(s[FOUR]);
		sb.append(" VARCHAR(100), ");
		sb.append(s[FIVE]);
		sb.append(" double, ");
		sb.append(s[SIX]);
		sb.append(" VARCHAR(100), ");
		sb.append(s[SEVEN]);
		sb.append(" VARCHAR(100));");
		//create the table which is called invoice
		stmt.executeUpdate(sb.toString());
	}
	/**
	 * The aim of this method is to list all records in the database. It uses Statement
	 * type object as a parameter to use the SQL query. Moreover, this method throws the
	 * SQLException and the NumberFormatException to prevent possible exceptions.
	 * 
	 * @param stmt
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	void query1(Statement stmt) throws SQLException, NumberFormatException {
		ResultSet rs = stmt.executeQuery("SELECT * FROM invoice");
		System.out.println("InvoiceNo, StockCode, Description, Quantity, InvoiceDate, UnitPrice, CustomerID, Country");
		//use while loop to iterate the ResultSet from first row to last row
		while (rs.next()) {
			/*
			 * To use the SQL query, create the StringBuffer object.
			 * It uses the get method of the ResultSet class to get elements.
			 */
			StringBuffer sb = new StringBuffer("");
			sb.append(rs.getInt(ONE));
			sb.append(", ");
			sb.append(rs.getString(TWO));
			sb.append(", ");
			sb.append(rs.getString(THREE));
			sb.append(", ");
			sb.append(rs.getInt(FOUR));
			sb.append(", ");
			sb.append(rs.getString(FIVE));
			sb.append(", ");
			sb.append(rs.getDouble(SIX));
			sb.append(", ");
			sb.append(rs.getString(SEVEN));
			sb.append(", ");
			sb.append(rs.getString(EIGHT));
			System.out.println(sb.toString());
		}
		rs.close(); //close the ResultSet
	}
	/**
	 * The aim of this method is to print out the total number of invoices 
	 * in the database. This method uses Statement type object as a parameter 
	 * to use the SQL query. Moreover, this method throws the SQLException 
	 * to prevent possible exceptions. 
	 * 
	 * @param stmt
	 * @throws SQLException
	 */
	void query2(Statement stmt) throws SQLException {
		/*
		 * To use the SQL query, create the StringBuffer object.
		 * Use the DISTINCT query to separate by the InvoiceNo.
		 * Also, to count the total number of invoice, I used the COUNT() query.
		 */
		StringBuffer sb = new StringBuffer("SELECT COUNT(DISTINCT InvoiceNo) ");
		sb.append("FROM invoice ");
		ResultSet rs = stmt.executeQuery(sb.toString());
		System.out.println("Number of Invoices");
		rs.first();
		int counter = rs.getInt(ONE);
		System.out.println(counter);
	}
	/**
	 * The aim of this method is to list the invoice number and total price 
	 * for each invoice in the database. This method uses Statement type object 
	 * as a parameter to use the SQL query. Moreover, this method throws the SQLException 
	 * to prevent possible exceptions. 
	 * 
	 * @param stmt
	 * @throws SQLException
	 */
	void query3(Statement stmt) throws SQLException {
		/*
		 * To use the sql query, create the StringBuffer.
		 * This query calculates the total price of each invoice by using the GROUP BY query.
		 */
		StringBuffer sb = new StringBuffer("SELECT InvoiceNo,");
		sb.append(" SUM(UnitPrice*Quantity)");
		sb.append(" AS \"TotalPrice\"  FROM invoice ");
		sb.append("GROUP BY InvoiceNo;");
		ResultSet rs = stmt.executeQuery(sb.toString());
		System.out.println("InvoiceNo, Total Price");
		//use while loop to iterate the ResultSet
		while(rs.next()){
			//use the get methods of the ResultSet class to get elements.
			StringBuilder sbr = new StringBuilder("");
			sbr.append(rs.getInt(ONE));
			sbr.append(", ");
			sbr.append(rs.getDouble(TWO));
			System.out.println(sbr.toString());
		}
	}
	/**
	 * The aim of this method is to  print the invoice number and total price for the 
	 * invoice with the highest total price. This method uses Statement type object 
	 * as a parameter to use the SQL query. Moreover, this method throws the SQLException 
	 * to prevent possible exceptions. 
	 * 
	 * @param stmt
	 * @throws SQLException
	 */
	void query4(Statement stmt) throws SQLException {
		/*
		 * To use the SQL query, create the StringBuffer object.
		 * I use the GROUP BY and ORDER BY and DESC to get the biggest total price.
		 * Basically, it calculates the total price, and order by descending order.
		 * Therefore, the maximum number should on the first row.
		 */
		StringBuffer sb = new StringBuffer("SELECT InvoiceNo,");
		sb.append(" SUM(UnitPrice*Quantity)");
		sb.append(" AS \"TotalPrice\"  FROM invoice ");
		sb.append("GROUP BY InvoiceNo ");
		sb.append("ORDER BY SUM(UnitPrice*Quantity) ");
		sb.append("DESC");
		ResultSet rs = stmt.executeQuery(sb.toString());
		rs.first();
		System.out.println("InvoiceNo, Maximum Total Price");
		System.out.println(rs.getInt(ONE) + ", " + rs.getDouble(TWO));
	}
	/**
	 * The aim of this method is to  print the customer ID and total price for the 
	 * customer ID. This method uses Statement type object as a parameter to use the 
	 * SQL query. Moreover, this method throws the SQLException to prevent possible 
	 * exceptions. 
	 * 
	 * @param stmt
	 * @throws SQLException
	 */
	void query5(Statement stmt) throws SQLException {
		/*
		 * To use the SQL query, create the StringBuffer object.
		 * I used GROUP BY to calculate the value of the total price per customer id.
		 */
		StringBuilder sb = new StringBuilder("SELECT CustomerID, ");
		sb.append("SUM(Quantity*UnitPrice) FROM");
		sb.append(" invoice GROUP BY CustomerID");
		ResultSet rs = stmt.executeQuery(sb.toString());
		System.out.println("CustomerID, Total Price");
		//use while loop to iterate
		while(rs.next()){
			System.out.println(rs.getString(1) + ", " + rs.getDouble(2));
		}
	}
}
