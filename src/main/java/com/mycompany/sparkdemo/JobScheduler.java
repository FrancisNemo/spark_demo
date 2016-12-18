package com.mycompany.sparkdemo;

import com.mycompany.sparkdemo.db.JobService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Franc on 2016/12/18.
 */
public class JobScheduler {

    public static final List<Future<Job>> jobStatus = new ArrayList<Future<Job>>();

    //TODO
    public static final String SPARKHOME="/opt/spark";


    public static void main(String[] args) {

        /*读取配置文件中的bean,初始化spring, 从mysql中获取任务*/
        ApplicationContext act = new ClassPathXmlApplicationContext("bean.xml");
        JobService jobService = (JobService) act.getBean("jobService");

        //串行提交任务
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Job> future = null;

        while(true){
            try {
                //get runnable job
                List<Job> list = jobService.listJobs();

                for (Job job: list) {
                    if("streaming".equals(job.getName())){
                        future = executorService.submit(new Callable<Job>() {
                            @Override
                            public Job call() throws Exception {
                                //TODO 根据spark-submit格式补全
/*                                $ ./bin/spark-submit --class org.apache.spark.examples.SparkPi \
                                --master yarn \
                                --deploy-mode cluster \
                                --driver-memory 4g \
                                --executor-memory 2g \
                                --executor-cores 1 \
                                --queue thequeue \
                                lib/spark-examples*.jar \
                                10*/
                                Runtime.getRuntime().exec(new String[] {"sh", "-c", String.format("%s/bin/spark-submit " +
                                    " --class com.mycompany.sparkdemo.sparkjob.SparkJob " +
                                    " --master yarn " +
                                    " --deploy-mode cluster " +
                                    " --driver-memory 4g " +
                                    " --executor-memory 2g " +
                                    " --executor-cores 1 " +
                                    " --queue thequeue " +
                                    " example/jars/spark_demo.jar " +
                                        " 10 ",SPARKHOME)});
                                return null;
                            }
                        });
                    }else if("sql".equals(job.getName())){
                        future = executorService.submit(new Callable<Job>(){

                            @Override
                            public Job call() throws Exception {
                                //TODO 根据spark-submit格式补全
                                Runtime.getRuntime().exec(new String[] {"sh", "-c", String.format("%s/bin/spark-sql ", SPARKHOME)});
                                return null;
                            }
                        });
                    }
                    jobStatus.add(future);
                }

                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
