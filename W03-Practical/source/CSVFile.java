import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;

/**
 * The aim of this class is to save the information in the csv file to the form of
 * object. The attributes of this class is categories in the csv file.
 * 
 * @author 160021429
 *
 */
public class CSVFile {
	
	//attributes
	protected int invoiceNo;
	protected String stockCode;
	protected String description;
	protected int quantity;
	protected String invoiceDate;
	protected double price;
	protected int customerID;
	protected String country;
	
	//create a string to prevent number format exceptions
	protected String invoiceNum;
	//use the calendar type object to set the date
	protected Date date;
	/**
	 * This method uses string as a parameter to set attributes.
	 * This method throws the IndexOutOfBoundsException and the NullPointerException.
	 * Also, this method has several try - catch statements to deal with possible exceptions.
	 * @param string
	 */
	public void setAttributes(String string) throws ArrayIndexOutOfBoundsException, NullPointerException{
		String[] stringArray = string.split(","); //split the string by ","
		try{
			try{
				invoiceNo = Integer.parseInt(stringArray[W03Practical.invoiceNoCol]);
				invoiceNum = Integer.toString(invoiceNo);
			}catch(NumberFormatException e){ //catch the number format exception
				invoiceNo = 0;
				invoiceNum = stringArray[W03Practical.invoiceNoCol];
			}
			stockCode = stringArray[W03Practical.stockCodeCol];
			description = stringArray[W03Practical.descriptionCol];
			quantity = Integer.parseInt(stringArray[W03Practical.quantityCol]);
			invoiceDate = stringArray[W03Practical.invoiceDateCol];
			try{
				setDate(invoiceDate);
			}catch(DataMissingException e){
				System.out.println("Some of the Data is missing");
			}
			price = Double.parseDouble(stringArray[W03Practical.unitPriceCol]);
			
			try{
				customerID = Integer.parseInt(stringArray[W03Practical.customerIDCol]);
			}catch(NumberFormatException e){
				customerID = 0; //if there is no ID, set it to 0
			}
		
			country = stringArray[W03Practical.countryCol];
		}catch(NullPointerException e){
			System.out.println(e.getMessage());
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println(e.getMessage());
		}catch(RuntimeException e){
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * This constructor uses string as a parameter to use the setAttribute method.
	 * @param string
	 */
	public CSVFile(String string){ //use method to set attributes' values
		setAttributes(string);
	}
	
	/**
	 * This method uses string as a parameter to set the value to Date object
	 * and returns the Date object
	 * @param invoiceDate
	 * @return
	 * @throws DataMissingException 
	 */
	public void setDate(String invoiceDate) throws DataMissingException{ //change string to date
		try{
			//split the string by blank
			String[] dateString = invoiceDate.split(" ");
			
			//use the DateFormat object to set the date
			DateFormat df = new SimpleDateFormat("dd/MM/YYYY");
			date = df.parse(dateString[0]);
		}catch(NullPointerException e){ //catch the NullPointerException
			throw new DataMissingException(e);
		}catch(NumberFormatException e){ //catch the NumberFormatException
			throw new DataMissingException(e);
		}catch(ParseException e){ //catch the ParseException
			throw new DataMissingException(e);
		}catch(DateTimeException e){ //catch the DateTimeException
			throw new DataMissingException(e);
		}catch(RuntimeException e){ //catch the RuntimeException
			throw new DataMissingException(e);
		}
		//If the code catch those exception, the code will occurs the DataMissingException
		//to show user that some data is missing.
	}
}
