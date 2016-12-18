package com.mycompany.sparkdemo.app;

import com.mycompany.sparkdemo.db.JobService;
import com.mycompany.sparkdemo.sparkjob.JobProcessor;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Franc on 2016/12/18.
 */
public class WordCount implements JobProcessor, Serializable{
    private static final Pattern SPACE = Pattern.compile(" ");
    private static final String FILE_PATH="/tmp/test";

    @Override
    public void execute(SparkSession sparkSession) {
        List<Tuple2<String, Integer>> list = sparkSession
                .read()
                .textFile(FILE_PATH)
                .javaRDD()
                .flatMap(s -> Arrays.asList(SPACE.split(s)).iterator())
                .mapToPair(w -> new Tuple2<String, Integer>(w, 1))
                .reduceByKey((a, b) -> a + b)
                .collect();

        sparkSession.stop();
    }
}
