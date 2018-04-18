import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
/**
 * The aim of this class is to combine the count of hash tag of each texts. * This class extends the Reducer class.
 * @author 160021429
 */
public class HashtagCountingCombiner extends Reducer<LongWritable, Text, LongWritable, Text> {
	/**
	 * The aim of this class is to combine the count of hash tag of each texts.
	 * It uses for each loop to get all texts that has same count. Then, it will sort the text
	 * with the HashMap hm of the JsonObjectInformationExt1 class. By using that HashMap, it will
	 * change the total count of each text.
	 * To sort the output by count, It will use entrySet method to make the HashMap as a Set that
	 * uses entry<k,v> as a parameter. Then, it will make an List with that Set. Next, it will sort the
	 * output with that List. After it finishes sorting outputs, it will send the output to the Reducer.
	 * It throws the IOException and InterruptedException to avoid potential problems.
	 * @param key
	 * @param values
	 * @param output
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void reduce(LongWritable key, Iterable<Text> values, Context output) throws IOException, InterruptedException {
		for (Text val : values) {
			String string = val.toString();
			if (JsonObjectInformationExt1.hm.containsKey(string)) {
				int i = JsonObjectInformationExt1.hm.get(string);
				i++;
				JsonObjectInformationExt1.hm.remove(string);
				JsonObjectInformationExt1.hm.put(string, i);
				continue;
			}
			JsonObjectInformationExt1.hm.put(string, 1);
		}
		Set<Entry<String,Integer>> mapEntries = JsonObjectInformationExt1.hm.entrySet();
		List<Entry<String,Integer>> entryList = new LinkedList<Entry<String,Integer>>(mapEntries);
		//use the Collections.sort method with a method that I created to sort the entryList with it's values
		Collections.sort(entryList, new Comparator<Entry<String,Integer>>() {
            public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
            	return (-1 * e1.getValue().compareTo(e2.getValue()));
            }
        });
		//use the for each loop to iterate the sorted entryList
		for (Entry<String, Integer> entry : entryList) {
			output.write(new LongWritable(entry.getValue()), new Text(entry.getKey()));
		}
	}
	
}
