package com.mycompany.sparkdemo.db;

import com.mycompany.sparkdemo.Job;

import java.util.List;

/**
 * Created by Franc on 2016/12/18.
 */
public interface JobService {
    List<Job> listJobs();
    void save(Job job);
    void update(Job job);
    Job getJob();
}
