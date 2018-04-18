/**
 * This class is the main class of W11PracticalExt1.
 * @author 160021429
 */
public class W11PracticalExt1 {
	/**
	 * This main method uses 3 command line arguments. The first one is the name of the input path.
	 * The second command line argument means the name of the output path. The third one allows the user
	 * to select the way to sort the data. By using those strings, the JobProcessing instance will read
	 * files and make a output directory with output files. It has a try-catch statement to catch
	 * potential exceptions.
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length < 3){
			System.out.println("Usage: java -cp \"lib/*:bin\" W11Practical <input_path> <output_path> <ordering_option>");
			return;
		}
		try{
			new JobProcessingExt1(args[0],args[1],args[2]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
