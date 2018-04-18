/**
 * This class is my own exception that I will throw in the suitable situations.
 * @author 160021429
 */
public class MyException extends Exception {
	/**
	 * This constructor is a default one
	 */
	MyException() {}
	/**
	 * This constructor takes the Exception as a parameter and get the message from
	 * that Exception, and print it out.
	 * @param possibleException
	 */
	MyException(Exception possibleException) {
		System.out.println(possibleException.getMessage());
	}
	/**
	 * This constructor takes the string message as a parameter and prints that message.
	 * @param message
	 */
	MyException(String message) {
		System.out.println(message);
	}
}
