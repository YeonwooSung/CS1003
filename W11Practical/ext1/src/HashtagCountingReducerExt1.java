import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
/**
 * The aim of this class is to sort the output as a frequency of hash tag.
 * It will receive data from the combiner and sort it by a frequency of hash tag.
 * Then it will print out the content of hash tag and a frequency of hash tag.
 * This class extends the Reducer class.
 * @author 160021429
 */
public class HashtagCountingReducerExt1 extends Reducer<LongWritable, Text, Text, LongWritable> {
	/**
	 * The aim of this method is to sort the output as a frequency of hash tag.
	 * It will print out the content of hash tag and a frequency of hash tag.
	 * @param key
	 * @param values
	 * @param output
	 * @throws IOException
	 * @throws InturruptedException
	 */
	public void reduce(LongWritable key, Iterable<Text> values, Context output) throws IOException, InterruptedException {
		StringBuilder sb = new StringBuilder("");
		for (Text value : values) {
			sb.append(value.toString());
			sb.append(", ");
		}
		// to delete the comma and blank that is behind the last word.
		sb.delete(sb.length() - 2, sb.length() - 1);		
		output.write(new Text(sb.toString()), key);
	}
}
