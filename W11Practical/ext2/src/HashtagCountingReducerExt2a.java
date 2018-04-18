import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
/**
 * The objective of this class is to print out the output with the id of each tweet with the count of retweet.
 * @author 160021429
 */
public class HashtagCountingReducerExt2a extends Reducer<Text, LongWritable, Text, LongWritable> {
	/**
	 * It will get the every count of retweets with the id of users.
	 * By comparing the count, it will get the highest count of each user
	 * and print out it with the user id.
	 * @param key
	 * @param values
	 * @param output
	 * @throws IOException
	 * @throws InturruptedException
	 */
	public void reduce(Text key , Iterable<LongWritable> values, Context output) throws IOException, InterruptedException {
		long l = 0;
		for (LongWritable value : values) {
			long lValue = value.get();
			if (l < lValue) {
				l = lValue;
			}
		}
		output.write(key, new LongWritable(l));
	}
}
