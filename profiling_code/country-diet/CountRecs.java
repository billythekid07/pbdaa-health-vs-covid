import org.apache.hadoop.fs.shell.Count;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.fs.Path;

public class CountRecs {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Please specify <input_path> <output_path> when running program.");
            System.exit(0);
        }

        Job job = new Job();
        job.setNumReduceTasks(1);

        // Set Job metadata
        job.setJarByClass(CountRecs.class);
        job.setJobName("Airbnb MapReduce Application");

        // Set job input & output paths
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // Set mapper count
        job.setMapperClass(CountRecsMapper.class);
        job.setReducerClass(CountRecsReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // Exit after waiting for job completion
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
