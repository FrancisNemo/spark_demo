package com.mycompany.sparkdemo.sparkjob;

import com.mycompany.sparkdemo.Job;
import com.mycompany.sparkdemo.db.JobService;
import org.apache.spark.sql.SparkSession;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Franc on 2016/12/18.
 */
public class SparkJob {
    private static JobService jobService;
    private static Job init(){
        ApplicationContext act = new ClassPathXmlApplicationContext("bean.xml");
        jobService = (JobService) act.getBean("jobService");
        return jobService.getJob();
    }

    public static void main(String[] args) {

        if (args.length < 1) {
            System.err.println("Usage: SparkJob <file>");
            System.exit(1);
        }
        String file = args[0];
        Job job = init();
        String jobProcessorClassName = job.getJobProcessorClassName();

        SparkSession sparkSession = SparkSession.builder().appName("SparkWordCount").getOrCreate();
        try {
            JobProcessor jobProcessor = (JobProcessor) Class.forName(jobProcessorClassName).newInstance();
            jobProcessor.execute(sparkSession);
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
