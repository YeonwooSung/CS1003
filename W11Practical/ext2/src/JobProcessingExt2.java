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
public class JobProcessingExt2 {
	private final int TEN = 10;
	/**
	 * The aim of this constructor is to create the Job and use the MapReduce function.
	 * It will follow the command that the user gave. If the user gives option as a freq,
	 * it will use the HashtagCountingMapperExt1 and HashtagCountingReducerExt1 to sort the
	 * output with the frequency of hash tag. If the user gives option as a word, it will sort
	 * the output with the word, which was the aim of the W11Practical.
	 * If the user gives the option as a retweet, the output will establish the most frequently
	 * re-tweeted Tweets. And if the user gives the option as a user, it will establish the
	 * id of users associated with the most frequently re-tweeted Tweets.
	 * By using the Job instance, it will read files from the directory that
	 * input_path indicates and write an output file with creating the directory by the path
	 * output_path indicates. It also throws several possible exceptions such as IOException,
	 * ClassNotFoundException, and InterruptedException to avoid potential problems that they
	 * may occur.
	 * @param input_path
	 * @param output_path
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws InterruptedException
	 */
	JobProcessingExt2(String input_path, String output_path, String option) throws IOException, ClassNotFoundException, InterruptedException {
		//create the Configuration object
		Configuration conf = new Configuration();
		//create the Job instance with the Configuration object and the given name, which is "Hashtag Count".
		Job job = Job.getInstance(conf, "W11PracticalExt2");
		//specify the input path and the output path to process the data and create the output file.
		FileInputFormat.setInputPaths(job, new Path(input_path));
		FileOutputFormat.setOutputPath(job, new Path(output_path));
		//use switch to make the program could follow the user's command
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
				processJob(job);
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
				processJob(job);
			}
			break;
			case "retweet" : {
				job.setMapperClass(HashtagCountingMapperExt2.class);
				//set the output types that will produce by mapper
				job.setMapOutputKeyClass(LongWritable.class);
				job.setMapOutputValueClass(Text.class);
				//set the reducer
				job.setReducerClass(HashtagCountingReducerExt2.class);
				// Specify the output types produced by reducer (words with total counts)
				job.setOutputKeyClass(Text.class);
				job.setOutputValueClass(LongWritable.class);
				processJob(job);
			}
			break;
			case "user" : {
				job.setMapperClass(HashtagCountingMapperExt2a.class);
				//set the output types that will produce by mapper
				job.setMapOutputKeyClass(Text.class);
				job.setMapOutputValueClass(LongWritable.class);
				//set the reducer
				job.setReducerClass(HashtagCountingReducerExt2a.class);
				// Specify the output types produced by reducer (words with total counts)
				job.setOutputKeyClass(Text.class);
				job.setOutputValueClass(LongWritable.class);
				processJob(job);
				//make new configuration instance to use one more MapReduce function
				Configuration configuration = new Configuration();
				Job job1 = Job.getInstance(configuration);
				//specify the input path and the output path to process the data and create the output file.
				FileInputFormat.setInputPaths(job1, new Path(output_path));
				FileOutputFormat.setOutputPath(job1, new Path(output_path+"_1"));
				job1.setMapperClass(HashtagCountingMapperExt2b.class);
				//set the output types that will produce by mapper
				job1.setMapOutputKeyClass(LongWritable.class);
				job1.setMapOutputValueClass(Text.class);
				//set the reducer
				job1.setReducerClass(HashtagCountingReducerExt2b.class);
				// Specify the output types produced by reducer (words with total counts)
				job1.setOutputKeyClass(Text.class);
				job1.setOutputValueClass(LongWritable.class);
				processJob(job1);
			}
		}
	}
	/**
	 * The aim of this method is to use the MapReduce function.
	 * It takes the Job object as a parameter.
	 * It also throws ClassNotFoundException, IOException and InterruptedException to avoid
	 * the possible problems.
	 * @param job
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	void processJob (Job job) throws ClassNotFoundException, IOException, InterruptedException {
		LocalJobRunner.setLocalMaxRunningMaps(job, TEN);
		job.setNumReduceTasks(1);
		job.waitForCompletion(true);
	}
}
