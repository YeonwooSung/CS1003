import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * The aim of this class is to use JDBC methods for MariaDB. This class creates 3 different
 * tables, and processes data from 3 different tables.
 * 
 * @author 160021429
 */
public class ActionExt2 {
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
	 * This method uses 2 parameters, Statement and line. It uses Statement 
	 * object to execute the SQL queries. Furthermore, this method will split the line 
	 * with comma and use those words as column names of the new table. Also, this method 
	 * throws the SQLException to prevent the possible exception.
	 * 
	 * @param stmt
	 * @param line
	 * @throws SQLException
	 */
	void createInvoice(Statement stmt, String line) throws SQLException {
		//drop the table if it exists already
		stmt.executeUpdate("DROP TABLE IF EXISTS invoice");
		stmt.executeUpdate("DROP TABLE IF EXISTS stock");
		stmt.executeUpdate("DROP TABLE IF EXISTS customer");
		
		String[] s = line.split(","); //split the string type array to give names to columns
		/*
		 * create the table which is called invoice. It has attributes, which are InvoiceNo, 
		 * StockCode, Quantity, InvoiceDate, CustomerID.
		 */
		StringBuffer sb = new StringBuffer("CREATE TABLE invoice ("); //create invoice table
		sb.append(s[ZERO]);
		sb.append(" int, ");
		sb.append(s[ONE]);
		sb.append(" VARCHAR(100), ");
		sb.append(s[THREE]);
		sb.append(" int, ");
		sb.append(s[FOUR]);
		sb.append(" VARCHAR(100), ");
		sb.append(s[SIX]);
		sb.append(" VARCHAR(100));");
		
		//create table
		stmt.executeUpdate(sb.toString());
	}
	/**
	 * This method check if the table already exists. If the table already exists, then
	 * it will drop that table. Then it will create new tables, which is called stock.
	 * This class uses 2 parameters, stmt and line. It uses Statement object to execute 
	 * the SQL queries. Furthermore, this method will split the line with comma and use 
	 * those words as column names of the new table. Also, this method throws the 
	 * SQLException to prevent the possible exception.
	 * 
	 * @param stmt
	 * @param line
	 * @throws SQLException
	 */
	void createStock(Statement stmt, String line) throws SQLException {
		String[] s = line.split(","); //split the string type array to give names to columns
		
		StringBuffer sb = new StringBuffer("");
		/*
		 * create the table which is called stock. It has attributes, which are StockCode, 
		 * Description, and UnitPrice. This table uses StockCode as a primary key.
		 */
		sb.append("CREATE TABLE stock (");
		sb.append(s[ONE]);
		sb.append(" VARCHAR(100), ");
		sb.append(s[TWO]);
		sb.append(" VARCHAR(100), ");
		sb.append(s[FIVE]);
		sb.append(" double, PRIMARY KEY (");
		sb.append(s[ONE]);
		sb.append(") );");
		
		//create table
		stmt.executeUpdate(sb.toString());
	}
	/**
	 * This method check if the table already exists. If the table already exists, then
	 * it will drop that table. Then it will create new tables, which is called customer.
	 * This class uses 2 parameters, stmt and line. It uses Statement object to execute 
	 * the SQL queries. Furthermore, this method will split the line with comma and use 
	 * those words as column names of the new table. Also, this method throws the 
	 * SQLException to prevent the possible exception.
	 * 
	 * @param stmt
	 * @param line
	 * @throws SQLException
	 */
	void createCustomer(Statement stmt, String line) throws SQLException {
		String[] s = line.split(","); //split the string type array to give names to columns
		
		StringBuffer sb = new StringBuffer("");
		/*
		 * create the table which is called customer. It has attributes, which are CustomerID, 
		 * and Country. It uses CustomerID as a primary key.
		 */
		sb.append("CREATE TABLE customer (");
		sb.append(s[SIX]);
		sb.append(" VARCHAR(100), ");
		sb.append(s[SEVEN]);
		sb.append(" VARCHAR(100), PRIMARY KEY (");
		sb.append(s[SIX]);
		sb.append(") );");
		
		//create table
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
		/*
		 * To use the SQL query, create the StringBuffer object.
		 * I used JOIN ON to get columns from multiple tables.
		 */
		StringBuffer buffer = new StringBuffer("SELECT InvoiceNo, ");
		buffer.append("invoice.StockCode, stock.Description,");
		buffer.append(" Quantity, InvoiceDate, ");
		buffer.append("stock.UnitPrice, invoice.CustomerID, customer.Country ");
		buffer.append("FROM invoice ");
		buffer.append("JOIN stock ON ");
		buffer.append("invoice.StockCode = stock.StockCode ");
		buffer.append("JOIN customer ON ");
		buffer.append("invoice.CustomerID = customer.CustomerID;");
		ResultSet rs = stmt.executeQuery(buffer.toString());
		System.out.println("InvoiceNo, StockCode, Description, Quantity, InvoiceDate, UnitPrice, CustomerID, Country");
		//use while loop to iterate the ResultSet from first row to last row
		while (rs.next()) {
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
	 * and the NumberFormatException to prevent possible exceptions. 
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
	 * and the NumberFormatException to prevent possible exceptions. 
	 * 
	 * @param stmt
	 * @throws SQLException
	 */
	void query3(Statement stmt) throws SQLException {
		/*
		 * To use the sql query, create the StringBuffer object.
		 * I used JOIN ON query to take columns from multiple tables.
		 * Also, I used GROUP BY and SUM to calculate the total price per invoice number.
		 */
		StringBuffer sb = new StringBuffer("SELECT InvoiceNo,");
		sb.append(" SUM(stock.UnitPrice*Quantity)");
		sb.append(" AS \"TotalPrice\"  FROM invoice ");
		sb.append("JOIN stock ON ");
		sb.append("invoice.StockCode = stock.StockCode ");
		sb.append("GROUP BY InvoiceNo;");
		ResultSet rs = stmt.executeQuery(sb.toString());
		System.out.println("InvoiceNo, Total Price");
		//use while loop to iterate the ResultSet
		while(rs.next()){
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
	 * and the NumberFormatException to prevent possible exceptions. 
	 * 
	 * @param stmt
	 * @throws SQLException
	 */
	void query4(Statement stmt) throws SQLException {
		/*
		 * To use the sql query, I create the StringBuffer object to store the query.
		 * I used JOIN ON to get columns from multiple tables.
		 * Moreover, I used GROUP BY and SUM to calculate the tatal price per invoice number.
		 * Then, I used ORDER BY and DESC to order by the descending order, so that the maximum
		 * total price comes to the first row.
		 */
		StringBuffer sb = new StringBuffer("SELECT InvoiceNo,");
		sb.append(" SUM(stock.UnitPrice*Quantity)");
		sb.append(" AS \"TotalPrice\"  FROM invoice ");
		sb.append("JOIN stock ON ");
		sb.append("invoice.StockCode = stock.StockCode ");
		sb.append("GROUP BY invoice.InvoiceNo ");
		sb.append("ORDER BY SUM(stock.UnitPrice*invoice.Quantity) ");
		sb.append("DESC");
		//Using the ResultSet object to get the result from the database.
		ResultSet rs = stmt.executeQuery(sb.toString());
		rs.first(); //goes to the first row
		System.out.println("InvoiceNo, Maximum Total Price");
		System.out.println(rs.getInt(ONE) + ", " + rs.getDouble(TWO));
	}
}
