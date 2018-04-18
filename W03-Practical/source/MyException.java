public class MyException extends Exception{
	MyException(){}
	MyException(Exception possibleException){
		System.out.println(possibleException.getMessage());
	}
	MyException(String message){
		System.out.println(message);
	}
}
