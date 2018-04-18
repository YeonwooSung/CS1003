import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
/**
 * 
 * @author 160021429
 */
public class HashtagCountingReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
	/**
	 * @param key
	 * @param values
	 * @param output
	 * @throws IOException
	 * @throws InturruptedException
	 */
	public void reduce(Text key, Iterable<LongWritable> values, Context output) throws IOException, InterruptedException {
		/*
		 * The key is the word, which stands for hash tag.
		 * The value is the integer, which stands for the count of the word.
		 */
		int sum = 0;
		for (LongWritable value : values) {
			long i = value.get();
			sum += i;
		}
		output.write(key, new LongWritable(sum));
	}
}
