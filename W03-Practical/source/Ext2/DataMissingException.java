public class DataMissingException extends MyException{
	DataMissingException(){}
	DataMissingException(Exception possibleException){super(possibleException);}
	DataMissingException(String message){super(message);}
}
