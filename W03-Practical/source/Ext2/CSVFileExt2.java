import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;

/**
 * @deprecated
 * @author 160021429
 *
 */
public class CSVFileExt2{
	
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
	 * @deprecated
	 */
	public void setAttributes(String line) throws ArrayIndexOutOfBoundsException, NullPointerException{
		StringSplitting splitting = new StringSplitting(line);
		String[] stringArray = splitting.s;
		try{
			try{
				invoiceNo = Integer.parseInt(stringArray[W03PracticalExt2.invoiceNoCol]);
				invoiceNum = Integer.toString(invoiceNo);
			}catch(NumberFormatException e){ //catch the number format exception
				invoiceNo = 0;
				invoiceNum = stringArray[W03PracticalExt2.invoiceNoCol];
			}
			stockCode = stringArray[W03PracticalExt2.stockCodeCol];
			description = stringArray[W03PracticalExt2.descriptionCol];
			quantity = Integer.parseInt(stringArray[W03PracticalExt2.quantityCol]);
			invoiceDate = stringArray[W03PracticalExt2.invoiceDateCol];
			
			try {
				setDate(invoiceDate);
			} catch (DataMissingException e1) {
				e1.printStackTrace();
			}
			
			price = Double.parseDouble(stringArray[W03PracticalExt2.unitPriceCol]);
			
			try{
				customerID = Integer.parseInt(stringArray[W03PracticalExt2.customerIDCol]);
			}catch(NumberFormatException e){
				customerID = 0; //if there is no ID, set it to 0
			}
		
			country = stringArray[W03PracticalExt2.countryCol];
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
	 * This constructor uses string as a parameter to use the setAttribute method
	 * @param string
	 * @deprecated
	 */
	public CSVFileExt2(String string){ //use method to set attributes' values
		setAttributes(string);
	}
	
	/**
	 * This method uses string as a parameter to set the value to Date object
	 * and returns the Date object
	 * @param invoiceDate
	 * @return
	 * @throws DataMissingException 
	 * @deprecated
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
		}catch(DateTimeException e){
			throw new DataMissingException(e);
		}catch(RuntimeException e){
			throw new DataMissingException(e);
		}
	}
}
