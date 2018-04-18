import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
/**
 * 
 * @author 160021429
 * @deprecated
 */
public class W03PracticalExt1{
	/**
	 * This method uses a string as a parameter to check if the invoice number
	 * starts with C, which means cancel
	 * @param invoiceNum
	 * @return
	 */
	public static boolean cancelationCheck(String invoiceNum){ 
		boolean checker = false;
		if(invoiceNum.charAt(0) == 'C') //if the first character of the string is C
			checker = true; //set the boolean to true
		else
			checker = false; //else, set the boolean to false
		return checker;
	}
	
	//constants to initialize double type variables and integer type variables
	final static double NEGATIVE_ZERO = -0.0;
	final static double ZERO = 0.0;
	
	//static variables to use count the total number of invoice per month
	static int janInvoice;
	static int febInvoice;
	static int marInvoice;
	static int aprInvoice;
	static int mayInvoice;
	static int junInvoice;
	static int julInvoice;
	static int augInvoice;
	static int sepInvoice;
	static int octInvoice;
	static int novInvoice;
	static int decInvoice;
	
	/**
	 * This method use and integer type variable as a parameter.
	 * In this method, it uses switch statement to increase the
	 * number of invoice per month by increasing the static fields.
	 * @param invoiceMonth
	 */
	static void countMonthlyInvoice(int invoiceMonth){
		//The aim of this switch statement is to compare the invoiceMonth and all months
		switch(invoiceMonth){
		case 0 : janInvoice++;
			break;
		case 1 : febInvoice++;
			break;
		case 2 : marInvoice++;
			break;
		case 3 : aprInvoice++;
			break;
		case 4 : mayInvoice++;
			break;
		case 5 : junInvoice++;
			break;
		case 6 : julInvoice++;
			break;
		case 7 : augInvoice++;
			break;
		case 8 : sepInvoice++;
			break;
		case 9 : octInvoice++;
			break;
		case 10 : novInvoice++;
			break;
		case 11 : decInvoice++;
		}
	}
	
	//store the information about columns
	static int invoiceNoCol;
	static int stockCodeCol;
	static int descriptionCol;
	static int quantityCol;
	static int invoiceDateCol;
	static int unitPriceCol;
	static int customerIDCol;
	static int countryCol;
	
	/**
	 * The main purpose of this method is to set the value to
	 * all static attributes by splitting the string that we used as a parameter
	 * to string array by commas and check them by using the for loop and 
	 * switch statement.
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
			
			//use command-line arguments to create files
			File csvFile = new File(args[0]); //input
			File output = new File(args[1]); //output
			File ext1 = new File(args[2]); //total prices, invoices, etc
			String graphicName = args[3]; //store this string to set the title of content pane
			File htmlFile = new File(args[4]); //output within web browser
			
			int numOfInvoice = (int) ZERO; //the number of invoices
			double sumOfTotalPrices = ZERO; //the sum of total prices	
			BufferedReader br = new BufferedReader(new FileReader(csvFile));
			PrintWriter pr = new PrintWriter(output);
			PrintWriter pr1 = new PrintWriter(ext1);
			PrintWriter pr2 = new PrintWriter(htmlFile);
			
			String firstLine = br.readLine(); // Just Titles of each
			
			setCol(firstLine);
			
			String secondLine = br.readLine();
			CSVFileExt1 secondLineData = new CSVFileExt1(secondLine);
			
			//hash map to store total stock and stock code
			HashMap<String, Integer> stockItems = new HashMap<String, Integer>();
			//array list to store stock code
			ArrayList<String> key = new ArrayList<>();
			
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
			
			int numOfCancelledInvoice = (int) ZERO;
			
			//check if the invoice is cancelled, and if so, increase the numOfCancelledInvoice
			if(cancelationCheck(invoiceNum))
				numOfCancelledInvoice++;
			
			numOfInvoice++; //increase the number of invoice numbers
			
			//set the value and the key to the hash map
			stockItems.put(secondLineData.stockCode, secondLineData.quantity);
			//add the stockCode to the array list
			key.add(secondLineData.stockCode);
			
			//initialize all static attributes
			janInvoice = 0;
			febInvoice = 0;
			marInvoice = 0;
			aprInvoice = 0;
			mayInvoice = 0;
			junInvoice = 0;
			julInvoice = 0;
			sepInvoice = 0;
			octInvoice = 0;
			novInvoice = 0;
			decInvoice = 0;
			
			int monthInvoice = secondLineData.date.getMonth(); //get the month of invoice
			countMonthlyInvoice(monthInvoice); //count the number of invoice per month
			
			while(br.ready()){
				String line = br.readLine();
				CSVFileExt1 csv = new CSVFileExt1(line);
				
				if (csv.price < ZERO){
					System.out.println("The unit price cannot be negative number!");
					continue;
				}
				
				monthInvoice = csv.date.getMonth(); //get the month of invoice
				countMonthlyInvoice(monthInvoice); //count the number of invoice per month
				
				if(invoiceNum.equals(csv.invoiceNum)){ //check if the invoice number changed
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
					sumOfTotalPrices += totalPrice; //add the previous invoice's total price
					
					totalPrice = csv.price * csv.quantity;
					
					if(totalPrice == NEGATIVE_ZERO)
						totalPrice = ZERO;
					
					pr.println("\nInvoice Number: " + csv.invoiceNum);
					pr.println("Stock Code: " + csv.stockCode);
					pr.println("Description: " + csv.description);
					pr.println("Quantity: " + csv.quantity);
					pr.println("Unit Price: " + csv.price);
					
					numOfInvoice++; //increase the number of invoice numbers
					
				}	
					invoiceNum = csv.invoiceNum; // to save the previous invoice number
					
					if(cancelationCheck(invoiceNum))
						numOfCancelledInvoice++;
					
				if(maximumPriceInvoice < totalPrice){
					maxInvoiceNo = invoiceNum;
					maximumPriceInvoice = totalPrice;
				}
				
				//create the iterator to iterate the array list
				Iterator<String> iterator = key.iterator();
				
				boolean checker = false; //boolean to check if there is a same stock code
				
				for(int i = 0; iterator.hasNext(); i++){ //for loop starts
					//check if there is a same stock code
					if(iterator.next().equals(csv.stockCode)){
						//get the value of total stock number
						int j = stockItems.get(key.get(i));
						//remove the hashmap's element to replace
						stockItems.remove(key.get(i));
						//put the new element and new key
						stockItems.put(key.get(i),++j);
						checker = true; //if there is a same stock code, change the value to true
						break;
					}//end of the if statement
				}//end of the for loop
				
				if(!checker){
					stockItems.put(csv.stockCode, csv.quantity);
					key.add(csv.stockCode);
				}
				
			} //while ends
			
			sumOfTotalPrices += totalPrice;
			
			pr1.println("Total number of invoice in January: "+janInvoice);
			pr1.println("Total number of invoice in February: "+febInvoice);
			pr1.println("Total number of invoice in March: "+marInvoice);
			pr1.println("Total number of invoice in April: "+aprInvoice);
			pr1.println("Total number of invoice in May: "+mayInvoice);
			pr1.println("Total number of invoice in Jun: "+junInvoice);
			pr1.println("Total number of invoice in July: "+julInvoice);
			pr1.println("Total number of invoice in August: "+augInvoice);
			pr1.println("Total number of invoice in September: "+sepInvoice);
			pr1.println("Total number of invoice in October: "+octInvoice);
			pr1.println("Total number of invoice in November: "+novInvoice);
			pr1.println("Total number of invoice in December: "+decInvoice);
			
			pr1.println("\nSum of Total Prices: "+sumOfTotalPrices);
			pr1.println("Total number of Invoices: "+numOfInvoice);
			
			//indentify that this file is html file
			pr2.println("<!DOCTYPE html>");
			//use print writer to write a html file
			pr2.println("<html>");
			pr2.println("<head>");
			pr2.println("<title> W03PracticalExt1 </title>");
			pr2.println("</head>");
			//open the body and table
			pr2.println("<body>");
			pr2.println("<table>");
			
			pr1.println("Stock Code: Quantity");
			
			/*
			 * Create the iterator to iterate the array list and hash map
			 * We cannot create iterator by hash map, therefore we use array
			 * list to create the iterator
			 * I will use this iterator to run the while loop
			 */
			Iterator<String> iterator = key.iterator();
			
			pr2.println("<tr>");
			pr2.println("<th>Stock Code</th>");
			pr2.println("<th>Stock Number</th>");
			pr2.println("</tr>");
			
			int numOfStock = (int) ZERO; //to count the number of total stock
			
			while(iterator.hasNext()){
				String stockCode = iterator.next();
				int stockNum = stockItems.get(stockCode);
				pr2.println("<tr>"); //open the tr
				pr2.print("<td>"+stockCode+"</td>"); //open td, print the stock code and close td
				pr2.println("<td>"+stockNum+"</td>"); //open td, print the stock number and close td
				pr2.println("</tr>");
				
				numOfStock++;
			}
			
			pr1.println("Total number of stock: "+numOfStock); //print out the total number of stock
			
			//print informations in the output file
			pr.println("Total Price: " + totalPrice);
			pr.println("\nMinimum priced Invoice Number: " + minInvoiceNo + " with " + minimumPriceInvoice);
			pr.println("Maximum priced Invoice Number: " + maxInvoiceNo + " with " + maximumPriceInvoice);
			
			br.close(); //close the buffered reader
			
			//close the table and body
			pr2.println("</table>");
			pr2.println("</body>");
			pr2.println("</html>"); //close the html
			
			//flush
			pr.flush();
			pr1.flush();
			pr2.flush();
			
			//close print writers
			pr.close();
			pr1.close();
			pr2.close();
			
			//create the invoice graphic object for graphical output
			new InvoiceGraphic(numOfCancelledInvoice, numOfInvoice,graphicName);
			
		}catch(IOException e){ //try ends and catch starts
			e.printStackTrace();
		}//catch ends
		catch(ArrayIndexOutOfBoundsException e){ //try ends and catch starts
			System.out.print("Usage: java W03PracticalExt1 ");
			System.out.print("<input_file> <output_file> ");
			System.out.print("<extension_file> ");
			System.out.print("<name of graphical format> ");
			System.out.println("<html_file>");
		} //catch ends
	}//main ends
}