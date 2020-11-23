import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.Configuration;

public class WorldFoodConsumption {

    // Mapper only!
    public static void main(String[] args) throws Exception {
        if (args.length > 2) {
            System.out.println("Please specify <input_path> <output_path> when running program.");
            System.exit(0);
        };

        // Get job configuration
        Configuration conf = new Configuration();
        conf.set("mapred.textoutputformat.separator", ",");

        // Create new job
        Job job = new Job(conf);

        // Set job metadata
        job.setNumReduceTasks(0);
        job.setJarByClass(WorldFoodConsumption.class);
        job.setJobName("World Food Consumption Mapping");

        // Set job input & output paths
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // Set mapper count
        job.setMapperClass(WorldFoodConsumptionMapper.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        // Exit after waiting for job completion
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}