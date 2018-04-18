import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
/**
 * The aim of this class is to use Map function of MapReduce technique. It extends Mapper class.
 * This class will process the distributed JSON data.
 * @author 160021429
 */
public class HashtagCountingMapperExt2 extends Mapper<LongWritable, Text, LongWritable, Text> {
	/**
	 * The aim of this method is to use Map function to sort the data with JsonObjectInformationExt2 instance.
	 * It throws InterruptedException and IOException to avoid potential problems.
	 * @throws InterruptedException
	 * @throws IOException
	 * @param key
	 * @param text
	 * @param output 
	 */
	public void map(LongWritable key, Text text, Context output) throws IOException, InterruptedException {
		String jsonString = text.toString();
		JsonObjectInformationExt2 joi = new JsonObjectInformationExt2(jsonString);
		long count = joi.getCount();
		String texture = joi.getText();
		if(texture != null) {
			output.write(new LongWritable(count), new Text(texture));
		}
	}
}
