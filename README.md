# pbdaa-health-vs-covid

Charlie Sun Final Project Steps:
IMPORTANT: In addition to my dataset, my analytics will also require a secondary dataset that is cleaned by Yuejia (Stephen) Tong. Please follow his data ingest and ETL code instructions to obtain the national covid-19 dataset. Finally, my dataset, which you can obtain by following the steps below, will also be used in Stephen’s analytics.
1.	Follow the data ingest instructions in “/data_ingest/happiness_report/happiness_report_ingest.txt” to download the primary dataset “2019.csv”. For consistency, when putting the dataset into HDFS, place it within a folder called “FinalProject”.
2.	Clean the dataset 
    a.	Navigate to “/etl_code/happiness_report/”. This folder contains the MapReduce code for cleaning the data.
    b.	Compile and run MapReduce program with the following lines of code.
        i.	javac -classpath `yarn classpath` -d . CleanMapper.java
        ii.	javac -classpath `yarn classpath` -d . CleanReducer.java
        iii.	javac -classpath `yarn classpath`:. -d . Clean.java
        iv.	jar -cvf clean.jar *.class
        v.	hadoop jar clean.jar Clean /user/NetID/FinalProject/2019.csv /user/NetID/FinalProject/CleanOutput
    c.	Create a folder in the FinalProject directory named “hiveInput1”. Rename “/FinalProject/CleanOutput/part-r-00000” to “2019-cleaned.csv” and place the file in “/FinalProject/hiveInput1”.
3.	Profiling the dataset. This is optional only for those curious how many lines the dataset has. Navigate to “/profiling_code/happiness_report/”, which contains the MapReduce code for counting the lines of the dataset. Run the same commands as Step 2b except replacing the filenames to get the result.
4.	This is when you should follow Stephen’s instruction and obtain the national covid-19 dataset. I have renamed the dataset “covid_infection_rate.csv”. Create a folder in the FinalProject directory named “hiveInput2”. Place “covid_infection_rate.csv” within “/FinalProject/hiveInput2” in HDFS.
5.	Follow the code in “/ana_code/analytics_CharlieSun.docx” to obtain our analytics. We end with 6 plots.
