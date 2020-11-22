

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


public class CleanMapper extends Mapper<LongWritable,Text,Text,Text>{
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line =  value.toString();
        String[] data = line.split(",");
        try {
            Integer.parseInt(data[1]);
        } catch (Exception e) {
            return ;
        }

        StringBuffer sb = new StringBuffer();
        sb.append(data[0]);
        sb.append(",");
        sb.append(data[1]);
        sb.append(",");
        sb.append(data[2]);
        sb.append(",");
        sb.append(data[3]);
        sb.append(",");
        sb.append(data[4]);
        sb.append(",");
        sb.append(data[8]);
        sb.append(",");
        sb.append(data[9]);
        sb.append(",");
        sb.append(data[12]);
        sb.append(",");
        sb.append(data[13]);
        context.write(new Text(sb.toString()),new Text(""));



    }
}