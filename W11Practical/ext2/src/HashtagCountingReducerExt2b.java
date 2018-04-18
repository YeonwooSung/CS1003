import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
/**
 * The aim of this class is to sort the output as a count of retweet of each text.
 * Then it will print the id of user with the highest count as an output.
 * @author 160021429
 */
public class HashtagCountingReducerExt2b extends Reducer<LongWritable, Text, Text, LongWritable> {
	/**
	 * The aim of this class is to sort the output as a count of retweet of each text.
	 * Then it will print the id of user with the highest count as an output.
	 * @param key
	 * @param values
	 * @param output
	 * @throws IOException
	 * @throws InturruptedException
	 */
	public void reduce(LongWritable key , Iterable<Text> values, Context output) throws IOException, InterruptedException {
		for (Text value : values) {
			output.write(value, key);
		}
	}
}
