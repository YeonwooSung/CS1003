import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
/**
 * The aim of this class is to use Map function of MapReduce technique. It extends Mapper class.
 * This class will process the distributed JSON data.
 * @author 160021429
 */
public class HashtagCountingMapperExt2a extends Mapper<LongWritable, Text, Text, LongWritable> {
	/**
	 * The aim of this method is to use Map function to sort the data with JsonObjectInformationExt2a instance.
	 * It gets id and count from the JSON file and use it as an output. Then, it will send that output to
	 * the Reducer.
	 * It throws InterruptedException and IOException to avoid potential problems.
	  * @throws InterruptedException
	 * @throws IOException
	 * @param key
	 * @param text
	 * @param output
	 */
	public void map(LongWritable key, Text text, Context output) throws IOException, InterruptedException {
		String jsonString = text.toString();
		JsonObjectInformationExt2a joi = new JsonObjectInformationExt2a(jsonString);
		long count = joi.getCount();
		String id = joi.getID();
		if(id != null) {
			output.write(new Text(id), new LongWritable(count));
		}
	}
}
