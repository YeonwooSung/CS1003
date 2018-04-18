public class InvalidNumberOfDoubleQuotesException extends MyException{
	InvalidNumberOfDoubleQuotesException(){}
	InvalidNumberOfDoubleQuotesException(Exception possibleException){super(possibleException);}
	InvalidNumberOfDoubleQuotesException(String message){super(message);}
}
