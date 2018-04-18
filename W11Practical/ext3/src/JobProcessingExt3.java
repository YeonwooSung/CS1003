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
public class JobProcessingExt3 {
	private final int MAP_NUM = 10;
	/**
	 * The aim of this constructor is to create the Job and use the MapReduce function.
	 * It will use HashtagCountingMapper class as a Mapper and HashtagCountingReducer as
	 * a Reducer. By using the Job instance, it will read files from the directory that
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
	JobProcessingExt3(String input_path, String output_path) throws IOException, ClassNotFoundException, InterruptedException {
		long t1 = System.currentTimeMillis();
		long t2 = 0;
		//create the Configuration object
		Configuration conf = new Configuration();
		//create the Job instance with the Configuration object and the given name, which is "Hashtag Count".
		Job job = Job.getInstance(conf, "W11PracticalExt3");
		//specify the input path and the output path to process the data and create the output file.
		FileInputFormat.setInputPaths(job, new Path(input_path));
		FileOutputFormat.setOutputPath(job, new Path(output_path));
		job.setMapperClass(HashtagCountingMapperExt3.class); //set the mapper
		//set the output types that will produce by mapper
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		//set the reducer
		job.setReducerClass(HashtagCountingReducerExt3.class);
		// Specify the output types produced by reducer (words with total counts)
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);
		LocalJobRunner.setLocalMaxRunningMaps(job, MAP_NUM);
		job.setNumReduceTasks(1);
		job.waitForCompletion(true);
		if (job.isComplete()) {
			t2 = System.currentTimeMillis();
		}
		System.out.println(t2-t1);
	}
}
