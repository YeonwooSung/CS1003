/**
 * This class is the main class of W11Practical.
 * @author 160021429
 */
public class W11PracticalExt2 {
	/**
	 * This main method uses 3 command line arguments. The first one is the name of the input path.
	 * The second command line argument means the name of the output path. he third one allows the user
	 * to select the way to sort the data. By using those strings, the JobProcessing instance will read
	 * files and make a output directory with output files. It has a try-catch statement to catch
	 * potential exceptions.
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			new JobProcessingExt2(args[0],args[1],args[2]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Usage: java -cp \"lib/*:bin\" W11PracticalExt2 <input_path> <output_path> <option>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
