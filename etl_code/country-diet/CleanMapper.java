import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.text.DecimalFormat;
import java.io.IOException;

public class WorldFoodConsumptionMapper extends Mapper<LongWritable, Text, Text, Text> {
    private static final DecimalFormat df = new DecimalFormat("###.##");

    @Override
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        // Convert value to string
        String line = value.toString().trim();

        // Get Values from CSV line
        String[] row = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

        // Clean Results to remove quotes
        for (int i = 0; i < row.length; i++) {
            String temp = row[i].replaceAll("^\"+|\"+$", "");
            row[i] = temp;
        }

        // Calculate respective values for classes
        String contextVal = "";

        // Calculate Alcoholic Beverages
        contextVal += turnToStrPercent(row[1]) + ",";

        // Calculate Protein
        contextVal += turnToStrPercent(
                    Double.parseDouble(row[6]) + Double.parseDouble(row[7]) +
                       Double.parseDouble(row[9]) + Double.parseDouble(row[12])
                    ) + ",";

        // Calculate Grains
        contextVal += turnToStrPercent(row[5]) + ",";

        // Calculate Fruits
        contextVal += turnToStrPercent(row[8]) + ",";

        // Calculate Vegetables
        contextVal += turnToStrPercent(Double.parseDouble(row[14]) + Double.parseDouble(row[16]) + Double.parseDouble(row[23])) + ",";

        // Calculate Diary
        contextVal += turnToStrPercent(row[10]) + ",";

        // Calculate Fats
        double animalFats = Double.parseDouble(row[3]);
        double vegetalFats = Double.parseDouble(row[13]) + Double.parseDouble(row[20]) + Double.parseDouble(row[22]);
        contextVal += turnToStrPercent(animalFats + vegetalFats) + ",";

        // Calculate Sugars
        contextVal += turnToStrPercent(Double.parseDouble(row[18]) + Double.parseDouble(row[19])) + ",";

        // Calculate Other
        contextVal += turnToStrPercent(
                Double.parseDouble(row[4]) + Double.parseDouble(row[11]) +
                   Double.parseDouble(row[15]) + Double.parseDouble(row[17])
        ) + ",";

        // Calculate Animal Products to Vegetal Product Ratio
        double apToVpRatio = Double.parseDouble(row[2]) / Double.parseDouble(row[21]);
        contextVal += turnToStrPercent(apToVpRatio) + ",";

        // Calculate Animal Fats to Vegetal Fats Ratio
        double afToVfRatio = animalFats / vegetalFats;
        contextVal += turnToStrPercent(afToVfRatio) + ",";

        // Add Obesity Rates
        String obesityRate = row[24].trim();
        if (obesityRate.equalsIgnoreCase("NA") || obesityRate.length() == 0) {
            contextVal += "-1,";
        }
        else if (obesityRate.equalsIgnoreCase("<2.5")) {
            contextVal += "2.5,";
        }
        else {
            contextVal += turnToStrPercent(obesityRate) + ",";
        }

        // Add Undernourished Rates
        String underNRate = row[25].trim();
        if (underNRate.equalsIgnoreCase("NA") || underNRate.length() == 0) {
            contextVal += "-1";
        }
        else if (underNRate.equalsIgnoreCase("<2.5")) {
            contextVal += "2.5";
        }
        else {
            contextVal += turnToStrPercent(underNRate);
        }

        // Write final value into CSV format
        context.write(new Text(row[0]), new Text(contextVal));
    }

    // Helper Functions
    private static String turnToStrPercent(String s) {
        return df.format(Double.parseDouble(s));
    }

    private static String turnToStrPercent(double d) {
        return df.format(d);
    }
}
