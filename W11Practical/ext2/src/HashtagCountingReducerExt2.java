import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
/**
 * The aim of this class is to use the Reduce function to calculate the total count of the retweet text.
 * Then it will print out the each text and the total count as an output.
 * @author 160021429
 */
public class HashtagCountingReducerExt2 extends Reducer<LongWritable, Text, Text, LongWritable> {
	/**
	 * The aim of this method is to use the Reduce function to calculate the total count of the retweet text.
	 * It will store all texts that have same frequency in the StringBuilder sb by using the for each loop.
	 * After storing every thing, it will print out the output.
	 * @param key
	 * @param values
	 * @param output
	 * @throws IOException
	 * @throws InturruptedException
	 */
	public void reduce(LongWritable key , Iterable<Text> values, Context output) throws IOException, InterruptedException {
		int i = (int) key.get();
		StringBuilder sb = new StringBuilder("count: ");
		sb.append(i);
		sb.append("\n");
		for (Text value : values) {
			sb.append(value.toString());
			sb.append("\n");
		}
		sb.append("--------------------------");
		output.write(new Text(sb.toString()), key);
	}
}
