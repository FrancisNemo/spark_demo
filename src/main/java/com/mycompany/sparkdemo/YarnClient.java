package com.mycompany.sparkdemo;

import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.yarn.client.cli.ApplicationCLI;

/**
 * Created by Franc on 2016/12/18.
 */
public class YarnClient {

    public Job queryJobStatus(String jobId) throws Exception {

        ApplicationCLI cli = new ApplicationCLI();
        cli.setSysOutPrintStream(System.out);
        cli.setSysErrPrintStream(System.err);

        String[] args = {"application", "-status", jobId};
        int res = ToolRunner.run(cli, args);
        cli.stop();

        //FIXME 是否要重新生成一个job?
        Job job = new Job();

        //TODO set job各个属性

        return job;
    }

    public void killJob(String jobId) throws Exception {
        ApplicationCLI cli = new ApplicationCLI();
        cli.setSysOutPrintStream(System.out);
        cli.setSysErrPrintStream(System.err);

        String[] args = {"application", "-kill", jobId};
        int res = ToolRunner.run(cli, args);
        cli.stop();
    }
}
