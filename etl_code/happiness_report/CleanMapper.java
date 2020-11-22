import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
public class CleanMapper extends Mapper<LongWritable, Text, Text, Text> {

   @Override
   public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
      if (value.toString().startsWith("Overall rank")){
         // Skip header line (first line) of CSV
         return;
      }
      
      String[] data = value.toString().split(",");
      // clean bad data
      if (data.length != 9) return;
      String s = "";
      // staring from 1 because we don't need the first column
      for (int i = 1; i < data.length; i++){
	  // remove countries with null values
          if (data[i] == null) return;
          
          s = s + data[i] + ",";
      }
      // truncate final ","
      s = s.substring(0,s.length()-1);
      context.write(new Text(s), new Text(""));
   }
}
