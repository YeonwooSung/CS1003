import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * The aim of this class is to read a csv file and write a txt file
 * by using CSVFile object.
 * This class uses several java.io methods to read and write a file.
 * 
 * @author Yeonwoo Sung
 *
 */
public class W03Practical{
	//constants to initialize double type variables and integer type variables
	final static double NEGATIVE_ZERO = -0.0;
	final static double ZERO = 0.0;
	
	static int invoiceNoCol;
	static int stockCodeCol;
	static int descriptionCol;
	static int quantityCol;
	static int invoiceDateCol;
	static int unitPriceCol;
	static int customerIDCol;
	static int countryCol;
	
	//this method set the value of static attributes by using the string as a parameter
	public static void setCol(String firstLine){
		String[] s = firstLine.split(",");
		
		//use for loop to use all elements in the string array
		for(int i = 0; i<s.length; i++){
			switch(s[i]){
			case "InvoiceNo" : invoiceNoCol = i;
				break;
			case "StockCode" : stockCodeCol = i;
				break;
			case "Description" : descriptionCol = i;
				break;
			case "Quantity" : quantityCol = i;
				break;
			case "InvoiceDate" : invoiceDateCol = i;
				break;
			case "UnitPrice" : unitPriceCol = i;
				break;
			case "CustomerID" : customerIDCol = i;
				break;
			case "Country" : countryCol = i;
			}
		}
	}
	
	public static void main(String[] args){
		try{ //try starts
			//creates File objects, which use command-line arguments
			File csvFile = new File(args[0]);
			File output = new File(args[1]);
			
			BufferedReader br = new BufferedReader(new FileReader(csvFile));
			PrintWriter pr = new PrintWriter(output);
			
			String firstLine = br.readLine(); // Just Titles of each
			
			setCol(firstLine);
			
			String secondLine = br.readLine();
			CSVFile secondLineData = new CSVFile(secondLine);
			
			//Writes in the output file
			pr.println("Invoice Number: " + secondLineData.invoiceNum);
			pr.println("Stock Code: " + secondLineData.stockCode);
			pr.println("Description: " + secondLineData.description);
			pr.println("Quantity: " + secondLineData.quantity);
			pr.println("Unit Price: " + secondLineData.price);
			
			double totalPrice = secondLineData.quantity * secondLineData.price;
			double minimumPriceInvoice = Double.MAX_VALUE;
			double maximumPriceInvoice = totalPrice;
			
			String invoiceNum = secondLineData.invoiceNum;
			String maxInvoiceNo = secondLineData.invoiceNum;
			String minInvoiceNo = secondLineData.invoiceNum;
			
			while(br.ready()){
				String line = br.readLine();
				CSVFile csv = new CSVFile(line);
				
				try{
					if(invoiceNum.equals(csv.invoiceNum)){
						totalPrice += csv.price * csv.quantity;
						if(totalPrice == NEGATIVE_ZERO)
							totalPrice = ZERO;
						
						pr.println("Invoice Number: " + csv.invoiceNum);
						pr.println("Stock Code: " + csv.stockCode);
						pr.println("Description: " + csv.description);
						pr.println("Quantity: " + csv.quantity);
						pr.println("Unit Price: " + csv.price);
						
					}else{
						pr.println("Total Price: " + totalPrice);
						if(minimumPriceInvoice > totalPrice && totalPrice > ZERO){
							minInvoiceNo = invoiceNum;
							minimumPriceInvoice = totalPrice;
						}	
						totalPrice = csv.price * csv.quantity;
						
						if(totalPrice == NEGATIVE_ZERO)
							totalPrice = ZERO;
						
						pr.println("\nInvoice Number: " + csv.invoiceNum);
						pr.println("Stock Code: " + csv.stockCode);
						pr.println("Description: " + csv.description);
						pr.println("Quantity: " + csv.quantity);
						pr.println("Unit Price: " + csv.price);
					}	
						invoiceNum = csv.invoiceNum; // to save the previous invoice number
					
					// if the maximum price is less than total price, change the maximum price
					if(maximumPriceInvoice < totalPrice){
						maxInvoiceNo = invoiceNum;
						maximumPriceInvoice = totalPrice;
					}
				}catch(NullPointerException e){
					System.out.println("Null Pointer Exception occured");
				}catch(ArrayIndexOutOfBoundsException e){
					System.out.println("Index is out of bounds");
				}
			}	
			pr.println("Total Price: " + totalPrice);
			pr.println("\nMinimum priced Invoice Number: " + minInvoiceNo + " with " + minimumPriceInvoice);
			pr.println("Maximum priced Invoice Number: " + maxInvoiceNo + " with " + maximumPriceInvoice);
			
			//close the reader and writer
			br.close();
			pr.flush(); //save the file
			pr.close();
		}catch(FileNotFoundException e){ //try ends and catch the FileNotFoundException
			System.out.println("Cannot Found The File");
			System.out.println("Please check the file name again");
		}catch(ArrayIndexOutOfBoundsException e){ //catch the ArrayIndexOutOfBoundsException
			System.out.println("Usage: java W03Practical <input_file> <output_file>");
		}catch(IOException e){ //catch the IOException
			e.printStackTrace();
		}
	}//main ends
}