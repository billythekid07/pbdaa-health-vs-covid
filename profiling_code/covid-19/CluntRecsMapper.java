
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class CluntRecsMapper extends Mapper<LongWritable, Text,Text,LongWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line =  value.toString();
        String[] data = line.split(",");
        try {
            Integer.parseInt(data[1]);
        } catch (Exception e) {
            return ;
        }


        context.write(new Text("counts"),new LongWritable(1));
    }
}