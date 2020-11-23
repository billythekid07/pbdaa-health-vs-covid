import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CountRecsMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private int partialSum = 0;

    @Override
    public void map(LongWritable key, Text value, Context context)
        throws IOException, InterruptedException {
        partialSum++;
    }

    @Override
    public void cleanup(Context context)
        throws IOException, InterruptedException {
        context.write(new Text("Total Lines"), new IntWritable(partialSum));
    }
}
