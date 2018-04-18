import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
/**
 * The aim of this class is to use the Reduce function of MapReduce to combine the data that the Mapper sent.
 * This class extends the Reducer class.
 * @author 160021429
 */
public class HashtagCountingReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
	/**
	 * This method will calculate the total sum of the hash tag count.
	 * Then it will print out the output with the content of hash tag and the sum.
	 * @param key
	 * @param values
	 * @param output
	 * @throws IOException
	 * @throws InturruptedException
	 */
	public void reduce(Text key, Iterable<LongWritable> values, Context output) throws IOException, InterruptedException {
		/*
		 * The key is the word, which stands for hash tag.
		 * The value is the integer, which stands for the count of the hash tag.
		 */
		int sum = 0;
		for (LongWritable value : values) {
			long i = value.get();
			sum += i;
		}
		output.write(key, new LongWritable(sum));
	}
}
