package com.mycompany.sparkdemo;

/**
 * Created by Franc on 2016/12/18.
 */
public class Job {
    private String id;
    private String name;
    private String status;
    private String start_time;
    private String end_time;
    private String jobProcessorClassName;

    public String getJobProcessorClassName() {
        return jobProcessorClassName;
    }

    public void setJobProcessorClassName(String jobProcessorClassName) {
        this.jobProcessorClassName = jobProcessorClassName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}

