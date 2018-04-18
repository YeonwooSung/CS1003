/**
 * This class is the main class of W11Practical.
 * @author 160021429
 */
public class W11PracticalExt3 {
	/**
	 * This main method uses 2 command line arguments. The first one is the name of the input path.
	 * The second command line argument means the name of the output path.
	 * By using those strings, the JobProcessing instance will read files and make a output
	 * directory with output files. It has a try-catch statement to catch potential exceptions.
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length < 2){
			System.out.println("Usage: java -cp \"lib/*:bin\" W11Practical <input_path> <output_path>");
			return;
		}
		try{
			new JobProcessingExt3(args[0],args[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
