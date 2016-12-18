package com.mycompany.sparkdemo;

import scala.Tuple2;

import org.apache.spark.sql.SparkSession;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Hello world!
 */
public class App {
    private static final Pattern SPACE = Pattern.compile(" ");

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: App <file>");
            System.exit(1);
        }

        /*SparkSession在spark2中统一入口*/
        SparkSession sparkSession = SparkSession.builder().appName("SparkWordCount").getOrCreate();
//        JavaRDD<String> lines = spark.read().textFile(args[0]).javaRDD();
//        JavaRDD<String> words = lines.flatMap(s -> Arrays.asList(SPACE.split(s)).iterator());
//        JavaPairRDD<String, Integer> ones = words.mapToPair(w -> new Tuple2<String, Integer>(w,1));
//        JavaPairRDD<String, Integer> counts = ones.reduceByKey((a,b) -> a+b);
//        List<Tuple2<String, Integer>> list = counts.collect();

        List<Tuple2<String, Integer>> list = sparkSession
                .read()
                .textFile(args[0])
                .javaRDD()
                .flatMap(s -> Arrays.asList(SPACE.split(s)).iterator())
                .mapToPair(w -> new Tuple2<String, Integer>(w, 1))
                .reduceByKey((a, b) -> a + b)
                .collect();

        for (Tuple2 t : list) {
            System.out.println(t._1() + ":" + t._2());
        }

        sparkSession.stop();
    }
}