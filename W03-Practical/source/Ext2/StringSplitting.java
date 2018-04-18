import java.util.ArrayList;

public class StringSplitting {
	/**
	 * This constructor uses the string as a parameter to split the string.
	 * @param line
	 */
	StringSplitting(String line){
		int numberOfDoubleQuote = countDoubleQuotes(line);
		
		splitTheString(line, numberOfDoubleQuote);
	}
	
	String[] s;
	String string;
	
	public int countDoubleQuotes(String line){
		int counter = 0;
		
		for(int i=0; i<line.length(); i++){
			if(line.charAt(i) == '\"')
				counter++;
		}
		return counter;
	}
	
	public void splitTheString(String line, int numberOfDoubleQuote){
		try{
			if(numberOfDoubleQuote == 0) //if the numberOfDoubleQuote is 0,
				s = line.split(","); //split it by comma
			//else if the numberOfDoubleQuote is the even number, which is greater than 0,
			else if(numberOfDoubleQuote%2 == 0){
				try{
				ArrayList<String> sal = new ArrayList<>();
				ArrayList<Integer> al = new ArrayList<>();
				for(int i=0; i<line.length(); i++)
					if(line.charAt(i) == '"')
						al.add(i);
				String subString = null;
				if(al.get(0) == 0){
					for(int i=0; i<numberOfDoubleQuote; i++){
						subString = line.substring(al.get(i), al.get(i)+1);
						sal.add(subString);
					} //the for loop ends
				}else{ //the if statements ends and else block starts
					//use the subString method to subtract some parts of the string
					subString = line.substring(0,al.get(0));
					sal.add(subString); //add the subString to the array list
					for(int i=0; i<numberOfDoubleQuote; i++){
						subString = line.substring(al.get(i), al.get(i)+1);
						sal.add(subString);
					} //the for loop ends
				} //the else block ends
				s = (String[]) sal.toArray(); //cast the type from object array to string array
				StringBuffer sb = new StringBuffer("");
				for(int i=0; i<s.length; i++){
					sb.append(s[i]); //use for loop to append strings to the string buffer
				}
				//change the string buffer to the string and set the value of string type attribute
				string = sb.toString();
				s = string.split(",");
				}catch(NullPointerException e){
					System.out.println(e.getMessage());
				}
			}else{//else if the numberOfDoubleQuote is the odd number, which is greater than 0
				throw new InvalidNumberOfDoubleQuotesException("Invalid Number of Double Quotes");
			}//the else block ends
		}catch(InvalidNumberOfDoubleQuotesException e){}
		catch(NullPointerException e){
			System.out.println(e.getMessage());
		}
	}
	
}
