import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class CluntRecs {
    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        //init，CluntRecs
        Job job = Job.getInstance(conf);
        job.setJarByClass(CluntRecs.class);
        //mapper

        job.setMapperClass(CluntRecsMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);

        job.setNumReduceTasks(1);
        job.setReducerClass(CluntRecsReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);
//        input file
        FileInputFormat.setInputPaths(job, new Path("data//CleanResult//part-r-00000"));

        FileOutputFormat.setOutputPath(job, new Path("data//CluntRecsResult2"));
        job.waitForCompletion(true);

    }
}
