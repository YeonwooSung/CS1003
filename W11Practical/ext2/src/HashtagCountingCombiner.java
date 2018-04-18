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
public class HashtagCountingCombiner extends Reducer<LongWritable, Text, LongWritable, Text> {
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
