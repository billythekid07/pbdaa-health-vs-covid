

import hadoop_test.Utils_hadoop;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class Clean {
    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        //init，Clean class
        Job job = Job.getInstance(conf);
        job.setJarByClass(Clean.class);
        // mapper

        job.setMapperClass(CleanMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        job.setReducerClass(CleanReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
//        input file
        FileInputFormat.setInputPaths(job, new Path("data//country_wise_latest.csv"));

        if( Utils_hadoop.testExist(conf,"data//CleanResult")){
            Utils_hadoop.rmDir(conf,"data//CleanResult");}
        FileOutputFormat.setOutputPath(job, new Path("data//CleanResult"));
        job.waitForCompletion(true);


    }
}
