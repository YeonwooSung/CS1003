import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
/**
 * 
 * @author 160021429
 * @deprecated
 */
public class W03PracticalExt2{
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
	
	/**
	 * @param firstLine
	 */
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
			File csvFile = new File(args[0]);
			File output = new File(args[1]);
			
			BufferedReader br = new BufferedReader(new FileReader(csvFile));
			PrintWriter pr = new PrintWriter(output);
				
			String firstLine = br.readLine(); // Just Titles of each
			
			setCol(firstLine);
			
			String secondLine = br.readLine();
			CSVFileExt2 secondLineData = new CSVFileExt2(secondLine);
			
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
				CSVFileExt2 csv = new CSVFileExt2(line);
				
				if (csv.price < 0){
					System.out.println("The unit price cannot be negative number!");
					continue;
				}
				
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
					
				if(maximumPriceInvoice < totalPrice){
					maxInvoiceNo = invoiceNum;
					maximumPriceInvoice = totalPrice;
				}
			}	
			pr.println("Total Price: " + totalPrice);
			pr.println("\nMinimum priced Invoice Number: " + minInvoiceNo + " with " + minimumPriceInvoice);
			pr.println("Maximum priced Invoice Number: " + maxInvoiceNo + " with " + maximumPriceInvoice);
			
			br.close();
			pr.flush();
			pr.close();
		}catch(FileNotFoundException e){ //try ends and catch the File Not Found Exception
			e.printStackTrace();
		}catch(ArrayIndexOutOfBoundsException e){ //catch the Array Index Out Of Bounds Exception
			System.out.println("Usage: java W03PracticalExt2 <input_file> <output_file>");
		}catch(IOException e){ //catch the IO Exception
			e.printStackTrace();
		}
	}//main ends
}