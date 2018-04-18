import java.io.*;
import java.sql.*;
/**
 * The aim of this class is processing the data by using the mariaDB queries.
 * When the user tries to use this program, the user should add 3 additional command line
 * arguments. The first one should represent the path of the database.properties file.
 * The second one should represent the path to the data file where the program will
 * read the data. The third one should specify the action taken by the program and 
 * can be one of create, query1, query2, query3, query4.
 * 
 * @author 160021429
 */
public class W07Practical {
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
	
	public static void main(String[] args) {
		Connection conn; // to connect JDBC
		
		try { //try starts
			//store command line arguments to strings
			String arg1 = args[ZERO]; //the path of the database.properties file
			String arg2 = args[ONE]; //the path to the data file where the program will read the data
			String arg3 = args[TWO]; //the command that the program should follow
			
			File file = new File (arg2); //create the file which the program will read
			
			ReadingProperties rp = new ReadingProperties(arg1); //to read the properties file
			
			//connect the MariaDB DriverManager by using the getConnection method
			conn = DriverManager.getConnection(rp.getURL(), rp.getUserName(), rp.getPassword());
			
			Statement stmt = conn.createStatement(); //create the statement
			
			Action a = new Action(); //create the Action instance to use sql queries
			
			switch(arg3){ //use switch statement to make program follows the command that the user gave
			case "create" : {
				ReadingCSV rc = new ReadingCSV(file);
				String firstLine = rc.readTheFile(); //read the first line of the csv file
				a.createTable(stmt, firstLine);
				while (rc.canReadMore()) {
					String line = rc.readTheFile();
					CSVObject csv = new CSVObject(line);
					
					//use boolean to check if there is a missing column
					boolean checker = csv.checkBlank(line.split(","));
					if(checker) {
						continue; //if there is a missing column, skip that row
					}
					
					String query = csv.invoiceQuery();
					
					PreparedStatement ps = conn.prepareStatement(query);
					
					//set informations by using the PreparedStatement
					ps.setInt(ONE, csv.getInvoiceNo());
					ps.setString(TWO, csv.getStockCode());
					ps.setString(THREE, csv.getDescription());
					ps.setInt(FOUR, csv.getQuantity());
					ps.setString(FIVE, csv.getInvoiceDate());
					ps.setDouble(SIX, csv.getUnitPrice());
					ps.setString(SEVEN, csv.getCustomerID());
					ps.setString(EIGHT, csv.getCountry());
					
					ps.executeUpdate();
				}
				//print out "OK" when the program works properly
				System.out.println("OK");
			}
			break;
			
			case "query1" : a.query1(stmt);
			break;
			
			case "query2" : a.query2(stmt);
			break;
			
			case "query3" : a.query3(stmt);
			break;
			
			case "query4" : a.query4(stmt);
			break;
			
			default : throw new MyException();
			}
			stmt.close(); //close the Statement
			conn.close(); //close the Connection
			
		} catch (ArrayIndexOutOfBoundsException e) { //catches the ArrayIndexOutOfBoundsException
			System.out.println("Usage: java -cp <mariadb-client.jar>:. W07Practical <DB_properties_file> <input_file> <action>");
		} catch (SQLException e) { //catch the SQLException
			e.printStackTrace();
		} catch (FileNotFoundException e){ //catch the FileNotFoundException
			System.out.println("Cannot find the file");
		} catch (IOException e) { //catch the IOException
			e.printStackTrace();
		} catch (MyException e) { //catch the MyException
			//prints out messages
			System.out.println("Invalid command");
			System.out.println("Please choose one of { create, query1, query2, query3, query4 }");
		}
	}
}
