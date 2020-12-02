# pbdaa-health-vs-covid

**#### Charlie Sun Final Project Steps:**   
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


**#### Yuejia Tong Final Project Steps:**
IMPORTANT: In addition to my dataset, my analytics will also require a secondary dataset that is cleaned by Charlie Sun. Please follow his data ingest and ETL code instructions to obtain the national world-happiness-2019 dataset. Finally, my dataset, which you can obtain by following the steps below, will also be used in Charlie’s analytics.

1. Data ingestion.  
Follow the steps described in the pdf which is located at "/data_ingest/covid-19/steps_to_ingest.pdf" to get access the data, and put it on HDFS under the directory "user/NetID". !!!: Note that my original data ingestion steps put the data to the directory "user/NetID/pbdaa_project" instead, but later when I simplify and reexecute the program I mistakenly put it directly under my NetID directory so I will continue with this location "user/NetID" from now on.  

2. Data Cleaning  
    a. Use SCP to transfer the source codes (located at "/etl_code/covid-19/") from local laptop to NYU dumbo.  
    b. Now compile the source code to run the MapReduce programs by typing following commands:  
        i. java -version  
        ii. yarn classpath  
        iii. javac -classpath `yarn classpath` -d . CleanMapper.java  
        iv. javac -classpath `yarn classpath` -d . CleanReducer.java  
        v. javac -classpath `yarn classpath`:. -d . Clean.java  
        vi. jar -cvf Clean.jar *.class  
        vii. hadoop jar Clean.jar Clean /user/NetID/country_wise_latest.csv /user/NetID/CleanResult  
	c. The result data is just the "part-r-00000" file which will be used later, and it is located under the directory "CleanResult".    

3. Profiling the dataset. The steps are similar to Data cleaning, and the source codes reside in the location "/profiling_code/covid-19".  

4. To get the cleaned version of World Happiness data, you can follow Charlie's Data Cleaning Step to get it. I personally get the data from him via email, but eventually this data is renamed by me to "happiness2019.csv" and put to HDFS under the directory "user/NetID/pbdaa_project/Input1".  

5. Follow the steps described in "/ana_code/analytics_YuejiaTong.pdf" to conduct the analytics. We end with 8 plots.  
