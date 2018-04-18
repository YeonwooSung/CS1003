import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.LocalJobRunner;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
/**
 * The aim of this class is to using MapReduce function with using Job instance.
 * @author 160021429
 */
public class JobProcessingExt1 {
	private final int TEN = 10;
	/**
	 * The aim of this constructor is to create the Job and use the MapReduce function.
	 * It will follow the command that the user gave. If the user gives option as a freq,
	 * it will use the HashtagCountingMapperExt1 and HashtagCountingReducerExt1 to sort the
	 * output with the frequency of hash tag. If the user gives option as a word, it will sort
	 * the output with the word, which was the aim of the W11Practical.
	 * @param input_path
	 * @param output_path
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 */
	JobProcessingExt1(String input_path, String output_path, String option) throws IOException, ClassNotFoundException, InterruptedException {
		//create the Configuration object
		Configuration conf = new Configuration();
		//create the Job instance with the Configuration object and the given name, which is "Hashtag Count".
		Job job = Job.getInstance(conf, "W11Practical");
		//specify the input path and the output path to process the data and create the output file.
		FileInputFormat.setInputPaths(job, new Path(input_path));
		FileOutputFormat.setOutputPath(job, new Path(output_path));
		switch (option) {
			case "freq" : {
				job.setMapperClass(HashtagCountingMapperExt1.class); //set the mapper
				//set the output types that will produce by mapper
				job.setMapOutputKeyClass(LongWritable.class);
				job.setMapOutputValueClass(Text.class);
				//set the combiner
				job.setCombinerClass(HashtagCountingCombiner.class);
				//set the reducer
				job.setReducerClass(HashtagCountingReducerExt1.class);
				// Specify the output types produced by reducer (words with total counts)
				job.setOutputKeyClass(Text.class);
				job.setOutputValueClass(LongWritable.class);
			}
			break;
			case "word" : {
				job.setMapperClass(HashtagCountingMapper.class); //set the mapper
				//set the output types that will produce by mapper
				job.setMapOutputKeyClass(Text.class);
				job.setMapOutputValueClass(LongWritable.class);
				//set the reducer
				job.setReducerClass(HashtagCountingReducer.class);
				// Specify the output types produced by reducer (words with total counts)
				job.setOutputKeyClass(Text.class);
				job.setOutputValueClass(LongWritable.class);
			}
		}
		LocalJobRunner.setLocalMaxRunningMaps(job, TEN);
		job.waitForCompletion(true);
	}
}
