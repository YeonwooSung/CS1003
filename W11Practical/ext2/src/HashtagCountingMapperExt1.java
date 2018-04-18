import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
/**
 * The aim of this class is to use Map function of MapReduce technique. It extends Mapper class.
 * This class will process the distributed JSON data.
 * @author 160021429
 */
public class HashtagCountingMapperExt1 extends Mapper<LongWritable, Text, LongWritable, Text> {
	/**
	 * The aim of this method is to use Map function to sort the data with JsonObjectInformation instance.
	 * It throws InterruptedException and IOException to avoid potential problems.
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public void map(LongWritable key, Text text, Context output) throws IOException, InterruptedException {
		String jsonString = text.toString();
		try {
			JsonObjectInformationExt1 joi = new JsonObjectInformationExt1(jsonString);
			ArrayList<String> al = joi.getHashtag(); //get the ArrayList that contains the hash tags
			Iterator<String> iterator = al.iterator();
			//use while loop to iterate the ArrayList
			while (iterator.hasNext()) {
				String hTag = iterator.next();
				output.write(new LongWritable(1), new Text(hTag));
			}
		} catch (IndexOutOfBoundsException e) {
			e.getMessage();
		}
	}
}
