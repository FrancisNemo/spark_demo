package com.mycompany.sparkdemo.sparkjob;

import org.apache.spark.sql.SparkSession;

/**
 * Created by Franc on 2016/12/18.
 */
public interface JobProcessor {
    void execute(SparkSession sparkSession);
}
