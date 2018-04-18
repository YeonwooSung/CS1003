import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
/**
 * 
 * @author 160021429
 */
public class HashtagCountingReducerExt1 extends Reducer<LongWritable, Text, Text, LongWritable> {
	/**
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
		sb.delete(sb.length() - 2, sb.length() - 1);
		output.write(new Text(sb.toString()), key);
	}
}
