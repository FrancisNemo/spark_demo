package com.mycompany.sparkdemo.db;

import com.mycompany.sparkdemo.Job;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Franc on 2016/12/18.
 */
public class SpringJDBCtest {

    public static void main(String[] args) {
        //初始化spring
        ApplicationContext act = new ClassPathXmlApplicationContext("bean.xml");
        //读取配置文件中的bean
        JobService js = (JobService) act.getBean("jobService");
        Job job = new Job();
        job.setId("2");
        job.setName("testjob");
        job.setStatus("new");
        job.setStart_time("00:00:00");
        job.setEnd_time("10:00:00");
        js.save(job);

        List<Job> list = js.listJobs();
        for (Job j: list) {
            System.out.println(j.getId());
        }
    }
}
