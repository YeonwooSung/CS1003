import java.io.IOException;
import java.util.Scanner;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
/**
 * The aim of this class is to use Map function of MapReduce technique. It extends Mapper class.
 * This Mapper will read the file that the HashtagCountingMapperExt2a and HashtagCountingReducerExt2b
 * wrote. By using 2 MapReduce functions, I process the data and sort the data by count of the retweet.
 * The first MapReduce function was for processing the data. And the second one (Ext2b) is for sort the
 * data by retweet count.
 * This class will process the distributed JSON data.
 * @author 160021429
 */
public class HashtagCountingMapperExt2b extends Mapper<LongWritable, Text, LongWritable, Text> {
	/**
	 * The aim of this method is to use Map function to sort the data with JsonObjectInformation instance.
	 * It throws InterruptedException and IOException to avoid potential problems.
	  * @throws InterruptedException
	 * @throws IOException
	 * @param key
	 * @param text
	 * @param output 
	 */
	public void map(LongWritable key, Text text, Context output) throws IOException, InterruptedException {
		Scanner sc = new Scanner(text.toString());
		String word1 = sc.next();
		String word2 = sc.next();
		long l = Long.parseLong(word2);
		output.write(new LongWritable(l), new Text(word1));
		sc.close();
	}
}
